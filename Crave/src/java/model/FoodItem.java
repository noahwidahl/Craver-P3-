/*
 * Class representing a Food Item in a system (e.g., a restaurant menu, inventory system).
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItem {
    // Fields (attributes) of the FoodItem class
    private int foodItemID;        // Unique identifier for a food item
    private String foodItemName;   // Name of the food item
    private BigDecimal price;      // Price of the food item using BigDecimal for precision
    private String linkToFoodImage;// URL link to an image of the food item
    private int foodSupplierID;    // Identifier for the supplier of the food item
    private Date addDate;          // Date the item was added to the system
    private Date modifiedDate;     // Date the item was last modified

    // Default constructor - initializes a new instance of FoodItem with default values
    public FoodItem() {
    }

    // Parametrized constructor - initializes a new instance of FoodItem with specified values
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

    // Getters - methods that return the value of each field
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

    // Setters - methods that set or update the value of each field
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

    // toString method - provides a string representation of the FoodItem object, useful for debugging
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
