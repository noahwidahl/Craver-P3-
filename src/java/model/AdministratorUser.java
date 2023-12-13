/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Admin;
import controller.Read;
import dbHelpers.DeleteQuery;
import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import jakarta.servlet.RequestDispatcher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public class AdministratorUser extends User {
    private ResultSet results;
    
    public AdministratorUser(String UserName) {
        super(UserName);
    }

    
    public void validateFoodSupplier(String stateID, String foodSupplierID){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "Update craveconnect.Foodsupplier set StateID = "+stateID+" where FoodsupplierID = "+foodSupplierID+";";
            System.out.println(query);
            updateInstance.executeInsertUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String showFoodsuppliersToBeValidated(HashMap<String, String> hashMap){
        try {
            //Create a ReadQuery helper object
            ReadQuery readQuery = new ReadQuery();
            //Get the HTML table from the ReadQuery object
            
            // Get the new value from the form
            String query = "select * from craveconnect.v_FoodsuppliersPending;";
            
            String table = readQuery.outputResultAsHtmlTableWithButtons(readQuery.readTableData(query),hashMap,"admin");
            return table;

        } catch (SQLException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return "fejl";
    } 
    
    public void changeUserStatus(int userID, int userRole){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.User set UserRoleID = "+userRole+" where UserID = "+userID+";";
            System.out.println(query);
            updateInstance.executeInsertUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateOtherUser(int userID, String password, String Email){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.User set Password = '"+password+"',Email = '"+Email+"' where UserID = "+userID+";";
            System.out.println(query);
            updateInstance.executeInsertUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public boolean deleteOtherUser(int userID){
        boolean control = true;
        try {
                DeleteQuery deleteInsteance = new DeleteQuery();
                String query = "CALL craveconnect.sp_DeleteUserProcedure("+userID+");";
                deleteInsteance.executeDelete(query);
                 
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                control = false;
            }
        return control;
    }
    
    public void updateFoodSupplier(int supplierID, String address, String postNr, String city, String phoneNumber, String link){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.Foodsupplier set Address = '"+address+"', PostNr = '"+postNr+"', City = '"+city+"', PhoneNumber = '"+phoneNumber+"', ExternalLink = '"+link+"' where FoodsupplierID = "+supplierID+";";
            System.out.println(query);
            updateInstance.executeInsertUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void deleteFoodSupplier(int supplierID){
        System.out.println("Running: deleteFoodItem"); //id 
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodsupplier("+supplierID+");";
            deleteInstance.executeDelete(query);
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
     // Testing example
   public static void main(String[] args) {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        /*
       FoodSupplier test = new FoodSupplier(1);
       List<Double> doubleList = test.getCoordinates();
       double Latitude = doubleList.get(0);
       double Longitude = doubleList.get(1);
       System.out.println(Latitude); 
       System.out.println(Longitude); 
       */
        
        AdministratorUser test = new AdministratorUser("Jakob");
        System.out.println("main"); //id 
        //test.changeUserStatus(2,3);
        //test.updateOtherUser(2, "test", "nymail@mail.dk");
        //test.deleteOtherUser(2);
        //test.updateFoodSupplier(2, "den addresse", "1111", "bronx", "+45 11 22 33 44", "www.test.dk");
        //test.deleteFoodSupplier(2);
   }
}



