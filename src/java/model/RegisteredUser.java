/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Profile;
import dbHelpers.ReadQuery;
import dbHelpers.CreateQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public class RegisteredUser extends User {
    //Assigning results used in multiple methods to handle db data.
    private ResultSet results;
    
    //Constructor to inherent attributes from the User superclass
    public RegisteredUser(String UserName) {
        super(UserName);
    }
    //Methods
    //Static method to handle the creating of a RegisteredUser in the system. 
    //returning a boolean, which can be used to control if the change happened or not.
    public static boolean createOwnUser(String userName, String password, String email) {
        try {
            String sql = "INSERT INTO craveconnect.User (username, password, Email, UserRoleID) VALUES ('"+userName+"', '"+password+"','"+email+"',2);"; 
            CreateQuery createInstance = new CreateQuery();    
            //Use executeInsert for INSERT statements
            int rowsAffected = createInstance.executeInsert(sql);

            // Check rowsAffected to ensure the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Registration successful!");
                return true;
            } else {
                System.out.println("Registration failed!");
                return false;
            }  
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("fejl i Class registerUser");
            return false;
        }
    }
    
    //Method to handle the showcase of ratings of the specific user logged in. 
    public String getOwnRatings(){
        try { 
            ReadQuery readQuery = new ReadQuery();
                String query = "SELECT * FROM craveconnect.Rating where UserId = (SELECT UserId FROM craveconnect.User where UserID = "+this.userID+");";
                results = readQuery.readTableData(query);
                
                //Using a HashMap to dyniamicly make buttons, key = button name, value = button text
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("BtnDelete", "Delete Rating");
                
                String table = readQuery.outputResultAsHtmlTableWithButtons(results,hashMap,"profile");
                return table;

            } catch (SQLException ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                return "fejl";
            }
    }
    
    //main is solely used to test the the current file.
    public static void main(String[] args) {
        RegisteredUser test = new RegisteredUser("Jakob");
        test.setLastLogin();
    }
    
}

