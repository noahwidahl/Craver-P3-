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
    public GuestUser(String UserName) {
        super(UserName);
    }
    
    
    public void guestLogin() throws SQLException{
        
                    //Getting the current local time
            LocalDateTime currentDateTime = LocalDateTime.now();
            // Format the current date and time using a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            
            // Creating a new session cookie with a auto generated random unique session ID
            Cookie sessionCookie = new Cookie("UUIDsessionID", UUID.randomUUID().toString());
            
           //insert into craveconnect.UserGuestLog;
           CreateQuery insertInstance = new CreateQuery();
           insertInstance.executeInsert("insert into craveconnect.UserGuestLog (Token,AddDate) values('"+sessionCookie.getValue()+"','"+formattedDateTime+"');");
    }
}
