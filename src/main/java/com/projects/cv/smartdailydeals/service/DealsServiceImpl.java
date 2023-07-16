package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Category;
import com.projects.cv.smartdailydeals.model.Deal;
import com.projects.cv.smartdailydeals.model.DealItem;
import com.projects.cv.smartdailydeals.model.Item;
import com.projects.cv.smartdailydeals.repository.jpa.CategoryRepository;
import com.projects.cv.smartdailydeals.repository.jpa.DealItemRepository;
import com.projects.cv.smartdailydeals.repository.cache.DealsRepository;
import com.projects.cv.smartdailydeals.repository.jpa.ItemRepository;
import com.projects.cv.smartdailydeals.repository.stream.KafkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DealsServiceImpl implements DealsService {


    public static long NEXT_EXPIRY_TIMESTAMP;

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    DealsRepository dealsRepository;
    @Autowired
    DealItemRepository dealItemRepository;
    @Autowired
    KafkaRepository kafkaRepository;

    private List<Item> fetchItemsForCategoryId(long categoryId) {
        List<Item> items = new ArrayList<>();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            double ceilPercentOfActiveDeals = category.getCeilPercentOfDailyDeals();
            long hardLimitOnDealItems = category.getHardNumericLimitOfItemsOnDailyDeals();
            long realLimit = Math.min((long)(ceilPercentOfActiveDeals/100*DAILY_DEAL_ITEMS_LIMIT), hardLimitOnDealItems);

            List<Item> itemsForCategory = itemRepository.findItemsByCategoryId(categoryId);
            if (realLimit >= itemsForCategory.size())
                items.addAll(itemsForCategory);
            else
                for (int itemNumber=0; itemNumber<realLimit; itemNumber++)
                    items.add(itemsForCategory.get(itemNumber));
        }

        return items;
    }

    private DealItem itemToDealItem(Item item) {
        long itemId = item.getId();
        long categoryId = item.getCategoryId();
        String categoryName = categoryRepository.findById(categoryId).get().getName();

        String topLevelCategoryName = getTopLevelCategoryName(categoryId);

        return new DealItem(itemId, categoryId, categoryName, topLevelCategoryName);
    }

    private Deal dealItemToDeal(DealItem dealItem) {
        long itemId = dealItem.getItemId();
        Item item = itemRepository.findById(itemId).get();
        int discount = DISCOUNT_RANGE_BEGIN + (int)(Math.random() * (DISCOUNT_RANGE_END - DISCOUNT_RANGE_BEGIN + 1));

        String dealDescription = "Save "+ discount + "%";
        long currentTimeMillis = new Date().getTime();
        NEXT_EXPIRY_TIMESTAMP = currentTimeMillis + ACTIVE_DEALS_EXPIRY_MINUTES*60*1000;

        return new Deal(itemId+"_"+(currentTimeMillis), dealItem.getCategoryId(), dealItem.getCategoryName(),
                dealItem.getTopLevelCategoryName(), itemId, item.getName(), dealDescription, item.getImageUrl(),
                NEXT_EXPIRY_TIMESTAMP);
    }

    /**
     * Allows multi-level categories by design.
     *
     * TODO: Check if a category update is not introducing cycle in childCategory-parentCategory relationships,
     * while validating any category Add/Update API.
     * Also, any parent-categoryId should be either 0 or already existing categoryId.
     *
     * @param categoryId
     * @return
     */
    private String getTopLevelCategoryName(long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();

            while (category.getParentCategoryId() != 0) {
                Optional<Category> parentCategoryOptional = categoryRepository.findById(category.getParentCategoryId());
                if (parentCategoryOptional.isPresent()) {
                    if (parentCategoryOptional.get().getId() == category.getId()) // cycle detected
                        return "Cycle detected in fetching top-level-category. category-id: " + category.getId();
                    category = parentCategoryOptional.get();
                } else {
                    //inconsistency.
                    return "No parent-category found for category-id: " + category.getParentCategoryId();
                }
            }
            //Found top-level category
            return category.getName();
        }
        return "Invalid categoryId";
    }

    @Override
    public long populateDealItemsOfTheDay() {
        List<Long> categoryIds = itemRepository.findDistinctCategoryIds();
        List<DealItem> dealItems = categoryIds.parallelStream()
                .map(this::fetchItemsForCategoryId)
                .flatMap(List::stream)
                .map(this::itemToDealItem)
                .collect(Collectors.toList());

        Collections.shuffle(dealItems);

        List<DealItem> dealItemsSaved = dealItemRepository.saveAll(dealItems);
        return dealItemsSaved.size();
    }

    @Override
    public long updateActiveDeals() {
        List<DealItem> dealItems = dealItemRepository.getFirstNDealItems(ACTIVE_DEALS_LIMIT);
        List<Deal> dealsForNewInterval = dealItems.parallelStream() // Stream<DealItems>
                .map(this::dealItemToDeal) // Stream<Deal>
                .collect(Collectors.toList());

        // Save existing deals to Kafka and remove from cache
        Map<String, Deal> existingDeals = dealsRepository.findAll();
        for (Map.Entry activeDealEntry: existingDeals.entrySet()) {
            kafkaRepository.publishDeal((Deal)activeDealEntry.getValue());
            dealsRepository.delete((String)activeDealEntry.getKey());
        }

        dealsRepository.saveAll(dealsForNewInterval);
        return dealsForNewInterval.size();
    }

    @Override
    public Map<String, Deal> getActiveDeals() {
        return dealsRepository.findAll();
    }

    @Override
    public long getSecondsLeftBeforeExpiryOfActiveDeals() {
        return NEXT_EXPIRY_TIMESTAMP;
    }
}
