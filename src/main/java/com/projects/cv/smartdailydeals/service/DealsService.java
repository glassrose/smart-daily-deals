package com.projects.cv.smartdailydeals.service;

import com.projects.cv.smartdailydeals.model.Deal;

import java.util.Map;

public interface DealsService {
    public static final int ACTIVE_DEALS_LIMIT = 10;
    public static final int ACTIVE_DEALS_EXPIRY_MINUTES = 10;
    public static final int DAILY_REFRESH_COUNT = (int)Math.floor(60*24/ACTIVE_DEALS_EXPIRY_MINUTES);
    public static final int DAILY_DEAL_ITEMS_LIMIT = ACTIVE_DEALS_LIMIT * DAILY_REFRESH_COUNT;
    public static final int DISCOUNT_RANGE_BEGIN = 10;
    public static final int DISCOUNT_RANGE_END = 20;


    /**
     *     Exposed by a REST API Called everyday at 23:55 hrs.
     *     Gets all items from each category. Then entire list is shuffled.
     *     Picking some items from each category is done with heuristic limits based on %ofActiveDeals or Hard Limit.
     *     Updates DealItems table.
     *
     * @return total number of deal-items prepared
     */
    long populateDealItemsOfTheDay();

    /**
     * Hit every 10 minutes starting 23:59 hrs each day
     * Pushes existing Deals present in redis to Kafka
     * Populates active deals for the upcoming time-interval and saves them in redis.
     *
     * @return total number of deals prepared
     */
    long updateActiveDeals();

    /**
     * Get deals active right now.
     *
     * @return Mapping of dealIds to their corresponding deals
     */
    Map<String, Deal> getActiveDeals();

    /**
     * Self-descriptive
     *
     * @return
     */
    long getSecondsLeftBeforeExpiryOfActiveDeals();

}
