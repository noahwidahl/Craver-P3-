/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItem {
    private int foodItemID;
    private String foodItemName;
    private BigDecimal price;
    private String linkToFoodImage;
    private int foodSupplierID;
    private Date addDate;
    private Date modifiedDate;

    // Constructors
    public FoodItem() {
    }

    public FoodItem(int foodItemID, String foodItemName, BigDecimal price, String linkToFoodImage, 
                    String ingredients, int foodSupplierID, Date addDate, Date modifiedDate) {
        this.foodItemID = foodItemID;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.foodSupplierID = foodSupplierID;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
        
    }

    // Getters
    public int getFoodItemID() {
        return foodItemID;
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

    public int getFoodSupplierID() {
        return foodSupplierID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    // Setters
    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
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

    public void setFoodSupplierID(int foodSupplierID) {
        this.foodSupplierID = foodSupplierID;
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
                "foodItemID=" + foodItemID +
                ", foodItemName='" + foodItemName + '\'' +
                ", price=" + price +
                ", linkToFoodImage='" + linkToFoodImage + '\'' +
                ", foodSupplierID=" + foodSupplierID +
                ", addDate=" + addDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }


  

}
