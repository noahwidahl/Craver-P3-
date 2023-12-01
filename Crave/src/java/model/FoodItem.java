package model;

import java.math.BigDecimal;
import java.util.Date;

public class FoodItem {
    // Fields (attributes)
    private int foodItemID;
    private String foodItemName;
    private BigDecimal price;
    private String linkToFoodImage;
    private int foodSupplierID;
    private Date addDate;
    private Date modifiedDate;

    // Parametrized constructor for existing FoodItem
    public FoodItem(int foodItemID, String foodItemName, BigDecimal price, 
                    String linkToFoodImage, int foodSupplierID, Date addDate, 
                    Date modifiedDate) {
        this.foodItemID = foodItemID;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.foodSupplierID = foodSupplierID;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
    }

    // Constructor for adding a new FoodItem
    public FoodItem(String foodItemName, BigDecimal price, int foodSupplierID) {
        this.foodItemName = foodItemName;
        this.price = price;
        this.foodSupplierID = foodSupplierID;
        // Initialize other fields if needed
        this.addDate = new Date(); // setting addDate to current date
        this.modifiedDate = new Date(); // setting modifiedDate to current date
        // linkToFoodImage and foodItemID can be set based on your application logic
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

    // toString method
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