/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Bokaj
 */

import dbHelpers.CreateQuery;
import dbHelpers.ReadQuery;
import dbHelpers.DeleteQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rating {
    //Attributes    
    int ratingID;
    int userId;
    int ratingValue;
    String comment;
    int foodItemID;
            
    //Constructor 
    public Rating(int id)  {
        try {
            //Getting the rating information using the dbHelpers 
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.Rating where RatingID = "+id+";";
            ResultSet resultSet = readInstance.readTableData(query);     
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                this.ratingID = Integer.parseInt(resultSet.getString("RatingID"));     //Parsing string to Int
                this.userId =  Integer.parseInt(resultSet.getString("UserId"));     //Parsing string to Int
                this.ratingValue = Integer.parseInt(resultSet.getString("RatingValue"));     //Parsing string to Int
                this.comment = resultSet.getString("Comment");     //Parsing string to Int
                this.foodItemID =  Integer.parseInt(resultSet.getString("FoodItemID"));     //Parsing string to Int
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Methods
    //Method to handle the deleting of ratings in the system.
    public boolean deleteRating(){
        try {
         // Define your SQL INSERT statement
            String sql = "delete from craveconnect.Rating where RatingID = "+this.ratingID+";"; 
            DeleteQuery deleteInstance = new DeleteQuery();   //Creating ReadQuery object    
            // Use executeInsert for INSERT statements
        int rowsAffected = deleteInstance.executeDelete(sql);
        // Check rowsAffected to ensure the insertion was successful
        if (rowsAffected > 0) {
            System.out.println("Rating deleted with successful!");
            return true;
        } else {
            System.out.println("Rating deleted with failed!");
            return false;
        }  

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fejl i Class deleteRating");
            return false;
        }
    }
        
    //Static method to create ratings of fooditems in the system. 
    public static boolean createRating(int userID, int scoreValue, String userComment, int foodItem) {
        try {
            String sql = "insert into  craveconnect.Rating (UserId, RatingValue, Comment, FoodItemID) values("+userID+","+scoreValue+",'"+userComment+"',"+foodItem+");";
            CreateQuery createInstance = new CreateQuery();   //Creating ReadQuery object    
            // Use executeInsert for INSERT statements
            int rowsAffected = createInstance.executeInsert(sql);

            // Check rowsAffected to ensure the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Rating successful!");
                return true;
            } else {
                System.out.println("Rating failed!");
                return false;
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fejl i Class createRating");
            return false;
        }
    }
    
    //main is solely used to test the the current file.
    public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        
       Rating test = new Rating(5);
       //Rating.createRating(5,3,"decent",10);
       test.deleteRating();
    }
}
