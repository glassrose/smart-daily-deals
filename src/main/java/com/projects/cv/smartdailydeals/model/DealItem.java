package com.projects.cv.smartdailydeals.model;

import javax.persistence.*;

@Entity
//@Table(name = "dealItems")
@Table(name = "test_dealItems")
public class DealItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name = "item_id")
    private long itemId;
    @Column(name = "category_id")
    private long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "top_level_category_name")
    private String topLevelCategoryName;

    public DealItem() {
    }

    public DealItem(long itemId, long categoryId, String categoryName, String topLevelCategoryName) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.topLevelCategoryName = topLevelCategoryName;
    }

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
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
}
