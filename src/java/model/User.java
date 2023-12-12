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
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.v_UserAndRoles where UserName = '"+userName+"';";
            ResultSet resultSet = readInstance.readTableData(query);
            System.out.println(query);
            System.out.println(resultSet);       
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
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
                    System.out.println(this.address);
                    System.out.println(this.postNr);
                    System.out.println(this.postBy);
                }
                
                System.out.println(this.userID);
                System.out.println(this.userName);
                System.out.println(this.password);
                System.out.println(this.userRoleID);
                System.out.println(this.roleDescription);
                System.out.println(this.lastSeen);
                
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Methods
    public void setLastLogin(){
        //Getting the current local time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        // Updating LastSeen in the DB
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "Update craveconnect.User set LastSeen = '"+formattedDateTime+"' where UserID = '+"+this.userID+"';";
            updateInstance.executeInsertUpdate(query);
            System.out.println("LastLogin running" );         
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
        // Helper method to check if a ResultSet has exactly one row
    private static boolean hasSingleRow(ResultSet resultSet) throws SQLException {
        return resultSet.next() && !resultSet.next();
    } 
    
    public boolean checkLogin(String userName, String password) { 
        try {
            ReadQuery readInstance = new ReadQuery();   //Creating ReadQuery object
            List<String> stringList = new ArrayList<>();    //Creating an ArrayList to store data
            //adding values to stringList
            stringList.add(userName);
            stringList.add(password);

            // Getting the result from the ReadQuery object, using the ReadUser method
            ResultSet result = readInstance.readUser(stringList, "SELECT * FROM craveconnect.User");
            System.out.println("testing");

            // Check amount of rows
            if (hasSingleRow(result)) {
                //System.out.println("One row");
                return true;    // User can login

            } else {
                //System.out.println("Not One row");
                return false;   //User is denied
            }

        } catch (SQLException e) {

            e.printStackTrace();
            //System.out.println("fejl");
            return false;
        }

    }
    
    
    public boolean updateOwnUser(String Address, String PostNr, String PostBy){
        boolean control = true;
        try {
                UpdateQuery updateInsteance = new UpdateQuery();
                String query = "UPDATE craveconnect.User " +
                "SET Address = '"+Address+"', " +
                "    PostNr = '"+PostNr+"', " +
                "    PostBy = '"+PostBy+"' " +
                "WHERE UserID = 1;";
                updateInsteance.executeInsertUpdate(query);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                control = false;
            }
        return control;
    }
    
    public boolean deleteOwnUser(int userID){
        boolean control = true;
        try {
                DeleteQuery deleteInsteance = new DeleteQuery();
                //First we delete the ratings of the user, maybe make a stored procedured on the DB
                //Then we delete the user himself
                //String query = "Delete from craveconnect.User where UserID = "+userID+";" +
                //"Delete FROM craveconnect.Rating where UserID = "+userID+";" +
                
                //Using a stored precedure on the mysql database to handle the 
                //logic of deleting multiple tables, first rating table, then the user table
                String query = "CALL craveconnect.sp_DeleteUserProcedure("+userID+");";
                deleteInsteance.executeDelete(query);
                 
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                control = false;
            }
        return control;
    }
    
    


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
        
        
        
        
        
        
        
        
        
        
        
    //public User(int ID){
        //lææ db helper ReadQuery();
        // seæect * from db.user where = UserID = ID
        //this.UserRoleID = ting fra db UserRoleID
    //}
    
//    getUserDate(){
        
//    }
    
    
    //setter vil update database
 //   setUserPassword(int UserRoleID){
        //Update sql where id = UserRoleID
//    }
}

