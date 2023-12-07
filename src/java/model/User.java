/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Bokaj
 */
public abstract class User {
    protected int UserID;
    protected String UserName;
    protected String Password;
    protected int UserRoleID;
    protected String RoleDescription;
    protected String LastSeen;  //Could be a datetime variable? but havent found one
 
    //Constructor
    public User(String userName){
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.v_UserAndRoles where UserName = '"+userName+"';";
            ResultSet resultSet = readInstance.ReadTableData(query);
            System.out.println(query);
            System.out.println(resultSet);       
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                this.UserID = Integer.parseInt(resultSet.getString("UserID"));     //Parsing string to Int
                this.UserName = resultSet.getString("UserName");
                this.Password = resultSet.getString("Password");
                this.UserRoleID = Integer.parseInt(resultSet.getString("UserRoleID"));     //Parsing string to Int
                this.RoleDescription = resultSet.getString("RoleDescription");
                this.LastSeen = resultSet.getString("LastSeen");
                
                
                //Dennis, lav password tjekker, send password med i constructor, hvis de ikke er ens, smid exception
                System.out.println(this.UserID);
                System.out.println(this.UserName);
                System.out.println(this.Password);
                System.out.println(this.UserRoleID);
                System.out.println(this.RoleDescription);
                System.out.println(this.LastSeen);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //Methods
    public void SetLastLogin(){
        //Getting the current local time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        
        // Updating LastSeen in the DB
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "Update craveconnect.User set LastSeen = '"+formattedDateTime+"' where UserID = '+"+this.UserID+"';";
            updateInstance.executeInsertUpdate(query);
            System.out.println("LastLogin running" );         
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public int getUserID(){
        return this.UserID; 
    }
    
    public int getUserRole(){
        return this.UserRoleID; 
    }
    
     public String getUserRoleDescription(){
        return this.RoleDescription; 
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

