package model;

import dbHelpers.ReadQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class FoodItem {
    // Attributes
    private int foodItemID;
    private String foodItemName;
    private double price;
    private String linkToFoodImage;
    private int foodSupplierID;
    private Date addDate;
    private Date modifiedDate;
    private int foodItemCategoryID;

    // Constructor
    public FoodItem(int foodItemID, String foodItemName, double price, String linkToFoodImage, 
                    int foodSupplierID, Date addDate, Date modifiedDate, int foodItemCategoryID) {
        this.foodItemID = foodItemID;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.foodSupplierID = foodSupplierID;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
        this.foodItemCategoryID = foodItemCategoryID;
    }

    // Method to fetch all FoodItems from the database
    public static List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.FoodItem;";
            ResultSet resultSet = readInstance.ReadTableData(query);

            while (resultSet.next()) {
                FoodItem foodItem = new FoodItem(
                    resultSet.getInt("FoodItemID"),
                    resultSet.getString("FoodItemName"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("LinkToFoodImage"),
                    resultSet.getInt("FoodsupplierID"),
                    resultSet.getDate("AddDate"),
                    resultSet.getDate("ModifiedDate"),
                    resultSet.getInt("FoodItemCategoryID")
                );
                foodItems.add(foodItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodItems;
    }
    
public static List<FoodItem> getFoodItemsBySupplierID(int supplierID) {
    List<FoodItem> foodItems = new ArrayList<>();
    try {
        ReadQuery readInstance = new ReadQuery();
        String query = "SELECT * FROM craveconnect.FoodItem WHERE FoodsupplierID = ?;";
        ResultSet resultSet = readInstance.ReadTableData(query, supplierID);

        while (resultSet.next()) {
            FoodItem foodItem = new FoodItem(
                resultSet.getInt("FoodItemID"),
                resultSet.getString("FoodItemName"),
                resultSet.getDouble("Price"),
                resultSet.getString("LinkToFoodImage"),
                resultSet.getInt("FoodsupplierID"),
                resultSet.getDate("AddDate"),
                resultSet.getDate("ModifiedDate"),
                resultSet.getInt("FoodItemCategoryID")
            );
            foodItems.add(foodItem);
        }
        
        
    } catch (SQLException ex) {
        Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodItems;
}

 // Getters
    public int getFoodItemID() {
        return foodItemID;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public double getPrice() {
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

    public int getFoodItemCategoryID() {
        return foodItemCategoryID;
    }

    // Setters
    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPrice(double price) {
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

    public void setFoodItemCategoryID(int foodItemCategoryID) {
        this.foodItemCategoryID = foodItemCategoryID;
    }
}