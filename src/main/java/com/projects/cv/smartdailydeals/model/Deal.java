package com.projects.cv.smartdailydeals.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("DEAL")
public class Deal implements Serializable {

    private String id;
    private long categoryId;
    private String categoryName;
    private String topLevelCategoryName;
    private long itemId;
    private String itemName;
    private String dealDescription;
    private String imageUrl;
    private long expiryTimestamp;

    public Deal() {
    }

    public Deal(String id, long categoryId, String categoryName, String topLevelCategoryName, long itemId, String itemName, String dealDescription, String imageUrl, long expiryTimestamp) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.topLevelCategoryName = topLevelCategoryName;
        this.itemId = itemId;
        this.itemName = itemName;
        this.dealDescription = dealDescription;
        this.imageUrl = imageUrl;
        this.expiryTimestamp = expiryTimestamp;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTopLevelCategoryName() {
        return topLevelCategoryName;
    }

    public void setTopLevelCategoryName(String topLevelCategoryName) {
        this.topLevelCategoryName = topLevelCategoryName;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(long expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }
}
