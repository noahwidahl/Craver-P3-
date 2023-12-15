/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.DeleteQuery;
import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bokaj
 */
public abstract class User {
    //Attributes
    protected int userID;
    protected String userName;
    protected String password;
    protected int userRoleID;
    protected String roleDescription;
    protected String lastSeen;  //Could be a datetime variable? but havent found one
    protected String address;
    protected String postNr;
    protected String postBy;
 
    //Constructor
    public User(String userName){
        try {
            //Getting the user information using the dbHelpers 
            //ReadQuery and the view form the DB v_UserAndRoles
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.v_UserAndRoles where UserName = '"+userName+"';";
            ResultSet resultSet = readInstance.readTableData(query);
            
            //Controlling if statement, did the query return a result
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                //Assigning all the attributes with values from the DB
                this.userID = Integer.parseInt(resultSet.getString("UserID"));     //Parsing string to Int
                this.userName = resultSet.getString("UserName");
                this.password = resultSet.getString("Password");
                this.userRoleID = Integer.parseInt(resultSet.getString("UserRoleID"));     //Parsing string to Int
                this.roleDescription = resultSet.getString("RoleDescription");
                this.lastSeen = resultSet.getString("LastSeen");
                
                //Assigning specific RegisteredUser Values
                if(this.userRoleID == 1 || this.userRoleID == 2){
                    this.address = resultSet.getString("Address");
                    if (this.address == null) {
                        // Handle the case where Address is null, for example, assign a default value
                        this.address = "Unknown";
                    }
                    this.postNr = resultSet.getString("PostNr");
                    if (this.postNr == null) {
                        // Handle the case where Address is null, for example, assign a default value
                        this.postNr = "Unknown";
                    }
                    this.postBy = resultSet.getString("PostBy");
                    if (this.postBy == null) {
                        // Handle the case where Address is null, for example, assign a default value
                        this.postBy = "Unknown";
                    }
                }
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Methods
    
    //Method to update the LastSeen field in the DB table craveconnect.User
    public boolean setLastLogin(){
        //Getting the current local time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "Update craveconnect.User set LastSeen = '"+formattedDateTime+"' where UserID = '+"+this.userID+"';";
            updateInstance.executeInsertUpdate(query); 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }   
    
    // Helper method to check if a ResultSet has exactly one row
    private static boolean hasSingleRow(ResultSet resultSet) throws SQLException {
        return resultSet.next() && !resultSet.next();
    } 
    
    //Method handling the login validating to the system.
    public boolean checkLogin(String userName, String password) { 
        try {
            ReadQuery readInstance = new ReadQuery();   //Creating ReadQuery object
            List<String> stringList = new ArrayList<>();    //Creating an ArrayList to store data
            //adding values to stringList
            stringList.add(userName);
            stringList.add(password);

            // Getting the result from the ReadQuery object, using the ReadUser method
            ResultSet result = readInstance.readUser(stringList, "SELECT * FROM craveconnect.User");
            //System.out.println("testing");

            // Check amount of rows
            if (hasSingleRow(result)) {
                return true;    // User can login
            } else {
                return false;   //User is denied
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Method to update the current users information, such as address, zipcode and city
    //returning a boolean, which can be used to control if the change happened or not.
    public boolean updateOwnUser(String address, String zipcode, String city, int userID){
        try {
            UpdateQuery updateInsteance = new UpdateQuery();
            String query = "UPDATE craveconnect.User " +
            "SET Address = '"+address+"', " +
            "    PostNr = '"+zipcode+"', " +
            "    PostBy = '"+city+"' " +
            "WHERE UserID = "+userID+";";
            updateInsteance.executeInsertUpdate(query); 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Method to delete the current users information.
    //returning a boolean, which can be used to control if the change happened or not.
    public boolean deleteOwnUser(){
        boolean control = true;
        try {
            DeleteQuery deleteInsteance = new DeleteQuery();
            //Using a stored precedure on the mysql database to handle the 
            //logic of deleting multiple tables, first rating table, then the user table
            String query = "CALL craveconnect.sp_DeleteUserProcedure("+this.userID+");";
            deleteInsteance.executeDelete(query);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            control = false;
        }
        return control;
    }
    
    //Getters
    public int getUserID(){
        return this.userID; 
    }
    
    public int getUserRole(){
        return this.userRoleID; 
    }
    
     public String getUserRoleDescription(){
        return this.roleDescription; 
    }
            
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserRoleID() {
        return userRoleID;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public String getAddress() {
        return address;
    }

    public String getPostNr() {
        return postNr;
    }

    public String getPostBy() {
        return postBy;
    }    
    
    //Setters
    public void setUserID(int UserID) {
        this.userID = UserID;
    }

    public void setUserName(String UserName) {
        this.userName = UserName;
    }

    public void setPassword(String Password) {
        this.password = Password;
    }

    public void setUserRoleID(int UserRoleID) {
        this.userRoleID = UserRoleID;
    }

    public void setRoleDescription(String RoleDescription) {
        this.roleDescription = RoleDescription;
    }

    public void setLastSeen(String LastSeen) {
        this.lastSeen = LastSeen;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    public void setPostNr(String PostNr) {
        this.postNr = PostNr;
    }

    public void setPostBy(String PostBy) {
        this.postBy = PostBy;
    }    
        
}

