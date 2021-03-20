package com.projects.cv.smartdailydeals.model;

import javax.persistence.*;

@Entity
//@Table(name = "categories")
@Table(name = "test_categories")
public class Category {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@Column(name = "parent_category_id")
	private long parentCategoryId;
	@Column(name = "name")
	private String name;
	@Column(name = "items_count")
	private long itemsCount;
	@Column(name = "deal_items_count")
	private long dealItemsCount;
	@Column(name = "always_on_deal")
	private boolean alwaysOnDeal;
	/**
	 * For finer control:
	 */
	@Column(name = "deal_off_to_on_rate")
	private double dealOffToOnRate;
	@Column(name = "time_based_deal_on")
	private boolean timeBasedDealOn;
	@Column(name = "ceil_percent_of_daily_deals")
	private double ceilPercentOfDailyDeals;
	@Column(name = "hard_numeric_limit_of_items_on_daily_deals")
	private long hardNumericLimitOfItemsOnDailyDeals;


	public Category() {
	}

	public Category(long parentCategoryId, String name, long itemsCount, long dealItemsCount, boolean alwaysOnDeal, double dealOffToOnRate, boolean timeBasedDealOn, double ceilPercentOfDailyDeals, long hardNumericLimitOfItemsOnDailyDeals) {
		this.parentCategoryId = parentCategoryId;
		this.name = name;
		this.itemsCount = itemsCount;
		this.dealItemsCount = dealItemsCount;
		this.alwaysOnDeal = alwaysOnDeal;
		this.dealOffToOnRate = dealOffToOnRate;
		this.timeBasedDealOn = timeBasedDealOn;
		this.ceilPercentOfDailyDeals = ceilPercentOfDailyDeals;
		this.hardNumericLimitOfItemsOnDailyDeals = hardNumericLimitOfItemsOnDailyDeals;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getItemsCount() {
		return itemsCount;
	}

	public void setItemsCount(long itemsCount) {
		this.itemsCount = itemsCount;
	}

	public long getDealItemsCount() {
		return dealItemsCount;
	}

	public void setDealItemsCount(long dealItemsCount) {
		this.dealItemsCount = dealItemsCount;
	}

	public boolean isAlwaysOnDeal() {
		return alwaysOnDeal;
	}

	public void setAlwaysOnDeal(boolean alwaysOnDeal) {
		this.alwaysOnDeal = alwaysOnDeal;
	}

	public double getDealOffToOnRate() {
		return dealOffToOnRate;
	}

	public void setDealOffToOnRate(double dealOffToOnRate) {
		this.dealOffToOnRate = dealOffToOnRate;
	}

	public boolean isTimeBasedDealOn() {
		return timeBasedDealOn;
	}

	public void setTimeBasedDealOn(boolean timeBasedDealOn) {
		this.timeBasedDealOn = timeBasedDealOn;
	}

	public double getCeilPercentOfDailyDeals() {
		return ceilPercentOfDailyDeals;
	}

	public void setCeilPercentOfDailyDeals(double ceilPercentOfDailyDeals) {
		this.ceilPercentOfDailyDeals = ceilPercentOfDailyDeals;
	}

	public long getHardNumericLimitOfItemsOnDailyDeals() {
		return hardNumericLimitOfItemsOnDailyDeals;
	}

	public void setHardNumericLimitOfItemsOnDailyDeals(long hardNumericLimitOfItemsOnDailyDeals) {
		this.hardNumericLimitOfItemsOnDailyDeals = hardNumericLimitOfItemsOnDailyDeals;
	}
}
