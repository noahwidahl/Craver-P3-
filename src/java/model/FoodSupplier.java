/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.ReadQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public FoodSupplier(int FoodsupplierID){
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
    
    
    
    
    
    public String getFoodsupplierName(){
        System.out.println("getFoodsupplierName: "+this.FoodsupplierName);
        return this.FoodsupplierName;
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
