/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItem {
    private int foodItemId;
    private String foodItemName;
    private BigDecimal price;
    private String linkToFoodImage;
    private String ingredients; // This might be a list or a single string depending on your design.
    private int foodSupplierId;
    private Date addDate;
    private Date modifiedDate;

    // Constructors
    public FoodItem() {
    }

    public FoodItem(int foodItemId, String foodItemName, BigDecimal price, String linkToFoodImage, 
                    String ingredients, int foodSupplierId, Date addDate, Date modifiedDate) {
        this.foodItemId = foodItemId;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.ingredients = ingredients;
        this.foodSupplierId = foodSupplierId;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
    }

    // Getters
    public int getFoodItemId() {
        return foodItemId;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getLinkToFoodImage() {
        return linkToFoodImage;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getFoodSupplierId() {
        return foodSupplierId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    // Setters
    public void setFoodItemId(int foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setLinkToFoodImage(String linkToFoodImage) {
        this.linkToFoodImage = linkToFoodImage;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public void setFoodSupplierId(int foodSupplierId) {
        this.foodSupplierId = foodSupplierId;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // You might want to override the toString() method for debugging purposes
    @Override
    public String toString() {
        return "FoodItem{" +
                "foodItemId=" + foodItemId +
                ", foodItemName='" + foodItemName + '\'' +
                ", price=" + price +
                ", linkToFoodImage='" + linkToFoodImage + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", foodSupplierId=" + foodSupplierId +
                ", addDate=" + addDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    // Additional methods like an equals() and hashCode() might be needed depending on your application requirements

}

