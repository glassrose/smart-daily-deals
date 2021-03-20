package com.projects.cv.smartdailydeals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "items")
@Table(name = "test_items")
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "image_url")
	private String imageUrl;
	@Column(name = "description")
	private String description;
	@Column(name = "cost")
	private double cost;
	@Column(name = "reviews")
	private String[] reviews;
	@Column(name = "category_id")
	private long categoryId;
	@Column(name = "sku")
	private String stockKeepingUnit;
	@Column(name = "upc")
	private String universalProductCode;
	@Column(name = "deal_applied")
	private boolean dealApplied;

	public Item() {
	}

	public Item(String name, String imageUrl, String description, double cost, String[] reviews, long categoryId, String stockKeepingUnit, String universalProductCode, boolean dealApplied) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.description = description;
		this.cost = cost;
		this.reviews = reviews;
		this.categoryId = categoryId;
		this.stockKeepingUnit = stockKeepingUnit;
		this.universalProductCode = universalProductCode;
		this.dealApplied = dealApplied;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String[] getReviews() {
		return reviews;
	}
	public void setReviews(String[] reviews) {
		this.reviews = reviews;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getStockKeepingUnit() {
		return stockKeepingUnit;
	}

	public void setStockKeepingUnit(String stockKeepingUnit) {
		this.stockKeepingUnit = stockKeepingUnit;
	}

	public String getUniversalProductCode() {
		return universalProductCode;
	}

	public void setUniversalProductCode(String universalProductCode) {
		this.universalProductCode = universalProductCode;
	}

	public boolean isDealApplied() {
		return dealApplied;
	}

	public void setDealApplied(boolean dealApplied) {
		this.dealApplied = dealApplied;
	}
}
