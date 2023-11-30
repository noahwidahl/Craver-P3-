/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.ReadQuery;
import dbHelpers.CreateQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bokaj
 */
public class RegisteredUser extends User {
    
    
// Helper method to check if a ResultSet has exactly one row
private static boolean hasSingleRow(ResultSet resultSet) throws SQLException {
    return resultSet.next() && !resultSet.next();
}   
    
    public static boolean checkLogin(String userName, String password) {
       
    try {
        ReadQuery readInstance = new ReadQuery();   //Creating ReadQuery object
        List<String> stringList = new ArrayList<>();    //Creating an ArrayList to store data
        stringList.add(userName);
        stringList.add(password);

        // Getting the result from the ReadQuery object, using the ReadUser method
        ResultSet result = readInstance.ReadUser(stringList, "SELECT * FROM craveconnect.User");
        System.out.println("testing");
        
        // Check amount of rows
        if (hasSingleRow(result)) {
            System.out.println("One row");
            return true;    // User can login
            
        } else {
            System.out.println("Not One row");
            return false;   //User is denied
        }

    } catch (SQLException e) {
        
        e.printStackTrace();
        System.out.println("fejl");
        return false;
    }

}

    // registerUser method
    public static boolean registerUser(String fullName, String userName, String password, String email) {
        try {
         // Define your SQL INSERT statement
            String sql = "INSERT INTO craveconnect.User (username, password) VALUES ('"+userName+"', '"+password+"');";
            //String sql = "INSERT INTO craveconnect.User (fullName, username, password, email) VALUES ('"+fullName+"', '"+userName+"', '"+password+"', '"+email+"');";
             System.out.println(sql);   
            CreateQuery createInstance = new CreateQuery();   //Creating ReadQuery object    
            // Use executeInsert for INSERT statements
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
            System.out.println("fejl i Class registerUser");
            return false;
        }
    }
/*
   // Testing example
   public static void main(String[] args) {
    try {
        ReadQuery readInstance = new ReadQuery();
        List<String> stringList = new ArrayList<>();
        stringList.add("Tim");
        stringList.add("password456");

        // Reading table data
        ResultSet result = readInstance.ReadUser(stringList, "SELECT * FROM craveconnect.User");

        // Check if there is exactly one row
        if (hasSingleRow(result)) {
            System.out.println("Exactly one row found.");
            // Process the row or perform further actions
        } else {
            System.out.println("No rows or more than one row found.");
        }

        // ... (rest of your code)
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
*/
   
}

