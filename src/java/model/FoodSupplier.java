/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.CreateQuery;
import dbHelpers.DeleteQuery;
import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import model.FoodItem;
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
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.Foodsupplier where FoodsupplierID = "+FoodsupplierID+";";
            ResultSet resultSet = readInstance.readTableData(query);
            System.out.println(query);
            System.out.println(resultSet);       
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
                //Dennis, lav password tjekker, send password med i constructor, hvis de ikke er ens, smid exception
                //System.out.println(this.FoodsupplierID);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Methods
    public void createFoodItem(String name, double price, String link, int category){
        System.out.println("Running: CreateFoodItem"); //id 
        //FoodItem test = new FoodItem();
        CreateQuery createInstance;
        try {
            createInstance = new CreateQuery();
            String query = "insert into craveconnect.FoodItem (FoodItemName,Price,LinkToFoodImage,FoodsupplierID,FoodItemCategoryID) values ('"+name+"',"+price+",'"+link+"',"+this.foodsupplierID+","+category+");";
            createInstance.executeInsert(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // registerFoodSupplier method
    public static boolean registerFoodSupplier(String FoodSupplierUsername, String FoodsupplierPassword, String FoodsupplierEmail, String FoodsupplierName, String FoodsupplierAddress, String FoodsupplierPostNr,String FoodsupplierCity, String FoodsupplierPhoneNumber, String FoodsuFoodsupplierExternalLinkpplierAddress, String FoodSupplierCategoryID) {
        try {
            // Define your SQL INSERT statement
            String sql = "INSERT INTO craveconnect.User (username, password, Email, UserRoleID) VALUES ('"+FoodSupplierUsername+"', '"+FoodsupplierPassword+"','"+FoodsupplierEmail+"', 4);";
            //String sql = "INSERT INTO craveconnect.User (fullName, username, password, email) VALUES ('"+fullName+"', '"+userName+"', '"+password+"', '"+email+"');";
             System.out.println(sql);   
            CreateQuery createInstanceUserRegisting = new CreateQuery();   //Creating ReadQuery object    
            // Use executeInsert for INSERT statements
        int rowsAffected = createInstanceUserRegisting.executeInsert(sql);
        
        // Check rowsAffected to ensure the insertion was successful
        if (rowsAffected > 0) {
            System.out.println("Registration successful!");

            sql = "insert into craveconnect.Foodsupplier (FoodsupplierName, Address, PostNr, City, PhoneNumber, ExternalLink, StateID, FoodSupplierCategoryID) values('"+FoodsupplierName+"','"+FoodsupplierAddress+"','"+FoodsupplierPostNr+"','"+FoodsupplierCity+"','"+FoodsupplierPhoneNumber+"','"+FoodsuFoodsupplierExternalLinkpplierAddress+"',3,"+FoodSupplierCategoryID+");";
            System.out.println(sql);   
            CreateQuery createInstanceFoodsupplierData = new CreateQuery();   //Creating ReadQuery object  
            rowsAffected = createInstanceFoodsupplierData.executeInsert(sql);
            if(rowsAffected > 0){
                System.out.println("Foodsupplier data is successful!");
                return true;
            }else {
                System.out.println("Food supplier data failed!");
                return false;
            }   
        } else {
            System.out.println("Registration failed!");
            return false;
        }  

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("fejl i Class registerUser");
            return false;
        }
    }


    
    
    public List<Double> getCoordinates(){
        // List of double-precision floating-point numbers
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(this.latitude);
        doubleList.add(this.longitude);
        return doubleList;
    }
      // MEthod to get FoodsupplierNames
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
     // Method to get FoodSupplier ID
    public static List<Integer> getAllFoodSupplierIDs() {
        List<Integer> foodSupplierIDs = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT FoodsupplierID FROM craveconnect.Foodsupplier;";
            ResultSet resultSet = readInstance.readTableData(query);

        while (resultSet.next()) {
            foodSupplierIDs.add(resultSet.getInt("FoodsupplierID"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodSupplierIDs;
}
    // Method to fetch all FoodSupplier names and their IDs
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
    
    public void updateFoodItem(int foodItemID,double price){
        System.out.println("Running: updateFoodItem"); //id 
        
        
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.FoodItem set Price = "+price+" where FoodItemID = "+foodItemID+";";
            updateInstance.executeInsertUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
    
     public void deleteFoodItem(int foodItemID){
        System.out.println("Running: deleteFoodItem"); //id 
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodItem("+foodItemID+");";
            deleteInstance.executeDelete(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     
    public void deleteOwnFoodSupplier(){
        System.out.println("Running: deleteFoodItem"); //id 
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodsupplier("+this.foodsupplierID+");";
            deleteInstance.executeDelete(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void test(){
        System.out.println("i am a test");
    }
    
    
    
    
    //Getters
    public String getFoodsupplierName(){
        System.out.println("getFoodsupplierName: "+this.foodsupplierName);
        return this.foodsupplierName;
    }
    
    //Setters

    
    // Testing example
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
