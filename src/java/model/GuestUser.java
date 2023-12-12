/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.CreateQuery;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.http.Cookie;
import java.util.UUID;

/**
 *
 * @author Bokaj
 */
public class GuestUser extends User {
    int guestID;
    String token;
    
    public GuestUser(String UserName) {
        super(UserName);
        this.userID = 0;     //Parsing string to Int
        this.userName = "Guest";
        this.userRoleID = 3;     //Parsing string to Int
        this.roleDescription = "Guest";
        //Getting the current local time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Format the current date and time using a DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.lastSeen = currentDateTime.format(formatter);
        this.guestID = 0;   //Value from table UserGuestLog if needed
        // Creating a new session cookie with a auto generated random unique session ID
            Cookie sessionCookie = new Cookie("UUIDsessionID", UUID.randomUUID().toString());
        this.token = sessionCookie.getValue();
    }
 
    
    public void setGuestLogin() throws SQLException{
           //insert into craveconnect.UserGuestLog;
           CreateQuery insertInstance = new CreateQuery();
           insertInstance.executeInsert("insert into craveconnect.UserGuestLog (Token,AddDate) values('"+this.token+"','"+this.lastSeen+"');");
    }


    public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        

        //RegisteredUser userLoggedIn = null;

      

       

}



}


