/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.CreateQuery;
import dbHelpers.DeleteQuery;
import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bokaj
 */
public class FoodSupplier {
    //Attributes
    protected int foodsupplierID;
    protected String foodsupplierName;
    protected String address;
    protected String postNr;
    protected String city;
    protected String externalLink;
    protected int stateID;
    protected double latitude;
    protected double longitude;
    protected int foodSupplierCategoryID;

    //Constructoren
    public FoodSupplier(int FoodsupplierID){
        try {
            //Getting the FoodSupplier information using the dbHelpers 
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.Foodsupplier where FoodsupplierID = "+FoodsupplierID+";";
            ResultSet resultSet = readInstance.readTableData(query);     
            //Controlling if statement, did the query return a result
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                this.foodsupplierID = Integer.parseInt(resultSet.getString("FoodsupplierID"));     //Parsing string to Int
                this.foodsupplierName = resultSet.getString("FoodsupplierName");
                this.address = resultSet.getString("Address");
                this.postNr = resultSet.getString("PostNr");    
                this.city = resultSet.getString("City");
                this.externalLink = resultSet.getString("ExternalLink");
                this.stateID = Integer.parseInt(resultSet.getString("StateID"));     //Parsing string to Int
                this.latitude = Double.parseDouble(resultSet.getString("Latitude"));     //Parsing string to Int
                this.longitude = Double.parseDouble(resultSet.getString("Longitude"));   //Parsing string to Int
                this.foodSupplierCategoryID = Integer.parseInt(resultSet.getString("FoodSupplierCategoryID"));     //Parsing string to Int
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Methods
    
    //Method to handle the creating of a foodItem, inputs are price, link and category
    public void createFoodItem(String name, double price, String link, int category){
        try {
            CreateQuery createInstance;
            createInstance = new CreateQuery();
            String query = "insert into craveconnect.FoodItem (FoodItemName,Price,LinkToFoodImage,FoodsupplierID,FoodItemCategoryID) values ('"+name+"',"+price+",'"+link+"',"+this.foodsupplierID+","+category+");";
            createInstance.executeInsert(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Method to handle the registration of Foodsuppliers. 
    //First the user of the foodsupplier is created, 
    //then the creating of the foodsupplier master data.
    public static boolean registerFoodSupplier(
            //inputs
            String FoodSupplierUsername, 
            String FoodsupplierPassword, 
            String FoodsupplierEmail, 
            String FoodsupplierName, 
            String FoodsupplierAddress, 
            String FoodsupplierPostNr,
            String FoodsupplierCity, 
            String FoodsupplierPhoneNumber, 
            String FoodsuFoodsupplierExternalLinkpplierAddress, 
            String FoodSupplierCategoryID) {
        try {
            // First is a user object created and stored in the DB table craveconnect.User
            String sql = "INSERT INTO craveconnect.User (username, password, Email, UserRoleID) VALUES ('"+FoodSupplierUsername+"', '"+FoodsupplierPassword+"','"+FoodsupplierEmail+"', 4);";  
            System.out.print((sql));
            CreateQuery createInstanceUserRegisting = new CreateQuery();   //Creating ReadQuery object    
            // Use executeInsert for INSERT statements
            int rowsAffected = createInstanceUserRegisting.executeInsert(sql);    
            // Check rowsAffected to ensure the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Registration successful!");
                //Now a foodsupplier object is created and stored in the DB table craveconnect.Foodsupplier
                sql = "insert into craveconnect.Foodsupplier (FoodsupplierName, Address, PostNr, City, PhoneNumber, ExternalLink, StateID, FoodSupplierCategoryID) values('"+FoodsupplierName+"','"+FoodsupplierAddress+"','"+FoodsupplierPostNr+"','"+FoodsupplierCity+"','"+FoodsupplierPhoneNumber+"','"+FoodsuFoodsupplierExternalLinkpplierAddress+"',3,"+FoodSupplierCategoryID+");";
                System.out.println(sql);   
                CreateQuery createInstanceFoodsupplierData = new CreateQuery();   //Creating ReadQuery object  
                rowsAffected = createInstanceFoodsupplierData.executeInsert(sql);
                if(rowsAffected > 0){
                    System.out.println("Foodsupplier data is successful!");
                    return true;
                }else {
                    System.out.println("Foodsupplier data failed!");
                    return false;
                }   
            } else {
                System.out.println("Registration failed!");
                return false;
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail in Class registerUser");
            return false;
        }
    }

    //Methods to get the coordinates from the foodsupplier table and return them as a list.
    public List<Double> getCoordinates(){
        // List of double-precision floating-point numbers
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(this.latitude);
        doubleList.add(this.longitude);
        return doubleList;
    }
    
    //Static method to get FoodsupplierNames
    public static List<String> getAllFoodSupplierNames() {
        List<String> foodSupplierNames = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT FoodsupplierName FROM craveconnect.Foodsupplier;";
            ResultSet resultSet = readInstance.readTableData(query);

            while (resultSet.next()) {
                foodSupplierNames.add(resultSet.getString("FoodsupplierName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodSupplierNames; 
    }
    
    //Static method to get FoodSupplier ID, the method returns a List of integers (the IDs)
    public static List<Integer> getAllFoodSupplierIDs() {
        List<Integer> foodSupplierIDs = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT FoodsupplierID FROM craveconnect.Foodsupplier;";
            ResultSet resultSet = readInstance.readTableData(query);
            //While loop to go over all the IDs and add them to the list of integers
            while (resultSet.next()) {
                foodSupplierIDs.add(resultSet.getInt("FoodsupplierID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodSupplierIDs;
}
    //Static method to fetch all FoodSupplier names and their IDs, returning a List of Strings.
    public static List<String> getAllFoodSupplierNamesWithIDs() {
        List<String> supplierList = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT FoodsupplierID, FoodsupplierName FROM craveconnect.Foodsupplier;";
            ResultSet resultSet = readInstance.readTableData(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("FoodsupplierID");
                String name = resultSet.getString("FoodsupplierName");
                String combinedInfo = name + " (" + id + ")";
                supplierList.add(combinedInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplierList;
    }
    
    //Method to update a food item.
    public boolean updateFoodItem(int foodItemID,double price){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.FoodItem set Price = "+price+" where FoodItemID = "+foodItemID+";";
            updateInstance.executeInsertUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    //Method to delete a foodItem
    public boolean deleteFoodItem(int foodItemID){
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodItem("+foodItemID+");";
            deleteInstance.executeDelete(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    //Method to enable the deleting of a current foodsupplier
    public boolean deleteOwnFoodSupplier(){
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodsupplier("+this.foodsupplierID+");";
            deleteInstance.executeDelete(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    //Getters
    public String getFoodsupplierName(){
        //System.out.println("getFoodsupplierName: "+this.foodsupplierName);
        return this.foodsupplierName;
    }
    
    //Setters

    
   //main is solely used to test the the current file.
   public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        /*
       FoodSupplier test = new FoodSupplier(1);
       List<Double> doubleList = test.getCoordinates();
       double Latitude = doubleList.get(0);
       double Longitude = doubleList.get(1);
       System.out.println(Latitude); 
       System.out.println(Longitude); 
       */
        
        FoodSupplier test = new FoodSupplier(1);
        System.out.println("main"); //id 
        //test.createFoodItem("tarte", 5, "www.jakob.dk", 5);
        //test.updateFoodItem(1,99);
        //test.deleteFoodItem(1);
        //test.deleteOwnFoodSupplier();
        //test.test();
    }
}
