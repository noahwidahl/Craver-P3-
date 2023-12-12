/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Profile;
import dbHelpers.ReadQuery;
import dbHelpers.CreateQuery;
import jakarta.servlet.RequestDispatcher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public class RegisteredUser extends User {
    private ResultSet results;
    
    public RegisteredUser(String UserName) {
        super(UserName);
    }
 
    // registerUser method
    public static boolean createOwnUser(String userName, String password, String email) {
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
    
    public String getOwnRatings(){
        try {
                
            ReadQuery readQuery = new ReadQuery();
                String query = "SELECT * FROM craveconnect.Rating where UserId = (SELECT UserId FROM craveconnect.User where UserID = "+this.userID+");";
                //System.out.println(query);
                results = readQuery.readTableData(query);
                //System.out.println(results);
                
                //Using a HashMap to dyniamicly make buttons, key = button name, value = button text
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("BtnDelete", "Delete Rating");
                
                String table = readQuery.outputResultAsHtmlTableWithButtons(results,hashMap,"profile");
                return table;
                //System.out.println(table);
                //Pass execution control to read.jsp along with the table.

            } catch (SQLException ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                return "fejl";
            }
    }
    
/*
   // Testing example
   public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       System.out.println(test.UserID); //id 
       
       
       
       
       
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
}*/
  // Testing example
   
    
    public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        

           RegisteredUser test = new RegisteredUser("Jakob");
           test.setLastLogin();

}
    
}

