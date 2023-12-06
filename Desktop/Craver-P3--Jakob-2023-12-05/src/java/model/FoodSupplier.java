/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.CreateQuery;
import dbHelpers.ReadQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public abstract class FoodSupplier {
    //Attributes
    protected int FoodsupplierID;
    protected String FoodsupplierName;
    protected String Address;
    protected String PostNr;
    protected String City;
    protected String ExternalLink;
    protected int StateID;
    protected double Latitude;
    protected double Longitude;
    protected int FoodSupplierCategoryID;

    //Constructoren
    public FoodSupplier(int FoodsupplierID, String name){
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.Foodsupplier where FoodsupplierID = "+FoodsupplierID+";";
            ResultSet resultSet = readInstance.ReadTableData(query);
            System.out.println(query);
            System.out.println(resultSet);       
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                this.FoodsupplierID = Integer.parseInt(resultSet.getString("FoodsupplierID"));     //Parsing string to Int
                this.FoodsupplierName = resultSet.getString("FoodsupplierName");
                this.Address = resultSet.getString("Address");
                this.PostNr = resultSet.getString("PostNr");    
                this.City = resultSet.getString("City");
                this.ExternalLink = resultSet.getString("ExternalLink");
                this.StateID = Integer.parseInt(resultSet.getString("StateID"));     //Parsing string to Int
                this.Latitude = Double.parseDouble(resultSet.getString("Latitude"));     //Parsing string to Int
                this.Longitude = Double.parseDouble(resultSet.getString("Longitude"));   //Parsing string to Int
                this.FoodSupplierCategoryID = Integer.parseInt(resultSet.getString("FoodSupplierCategoryID"));     //Parsing string to Int
                //Dennis, lav password tjekker, send password med i constructor, hvis de ikke er ens, smid exception
                //System.out.println(this.FoodsupplierID);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    //Methods
    
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

    public String getFoodsupplierName(){
        System.out.println("getFoodsupplierName: "+this.FoodsupplierName);
        return this.FoodsupplierName;
    }
    
    // MEthod to get FoodsupplierNames
   public static List<String> getAllFoodSupplierNames() {
        List<String> foodSupplierNames = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT FoodsupplierName FROM craveconnect.Foodsupplier;";
            ResultSet resultSet = readInstance.ReadTableData(query);

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
            ResultSet resultSet = readInstance.ReadTableData(query);

        while (resultSet.next()) {
            foodSupplierIDs.add(resultSet.getInt("FoodsupplierID"));
        }
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodSupplierIDs;
}


    // Testing example
   public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        

           RestaurantFoodSupplier id1 = new RestaurantFoodSupplier(1);
           id1.getFoodsupplierName();

    
   
    }
}
