/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.DeleteQuery;
import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public class AdministratorUser extends User {
    //Constructor to inherent from the superclass User
    public AdministratorUser(String UserName) {
        super(UserName);
    }

    //Methods
    
    //Method to update the state of a foodsupplier (Approved or denied), used in admin.jsp
    //Taking the inputs of which state to change to and which foodsupplierID to chanhge
    public boolean validateFoodSupplier(int stateID, int foodSupplierID){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "Update craveconnect.Foodsupplier set StateID = "+stateID+" where FoodsupplierID = "+foodSupplierID+";";
            updateInstance.executeInsertUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Static method to show foodsupplier who is ready to be validated, used in admin.jsp
    //Returning a HTML table with the information + buttons to press based on actions (Approve and Deny)
    public static String showFoodsuppliersToBeValidated(HashMap<String, String> hashMap){
        try {
            //Create a ReadQuery helper object
            ReadQuery readQuery = new ReadQuery();
            String query = "select * from craveconnect.v_FoodsuppliersPending;";
            String table = readQuery.outputResultAsHtmlTableWithButtons(readQuery.readTableData(query),hashMap,"admin");
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return "fejl";
    } 
    
    //Method to update another users role in the system
    public boolean changeUserStatus(int userID, int userRole){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.User set UserRoleID = "+userRole+" where UserID = "+userID+";";
            updateInstance.executeInsertUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Method to update another users credentials in the system
    public boolean updateOtherUser(int userID, String password, String Email){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.User set Password = '"+password+"',Email = '"+Email+"' where UserID = "+userID+";";
            updateInstance.executeInsertUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Method to delete another users in the system.
    public boolean deleteOtherUser(int userID){
        try {
            DeleteQuery deleteInsteance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteUserProcedure("+userID+");";
            deleteInsteance.executeDelete(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Method to update foodsuppliers master data
    public boolean updateFoodSupplier(int supplierID, String address, String zipcode, String city, String phoneNumber, String link){
        try {
            UpdateQuery updateInstance = new UpdateQuery();
            String query = "update craveconnect.Foodsupplier set Address = '"+address+"', PostNr = '"+zipcode+"', City = '"+city+"', PhoneNumber = '"+phoneNumber+"', ExternalLink = '"+link+"' where FoodsupplierID = "+supplierID+";";
            updateInstance.executeInsertUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorUser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //Methods to delete a foodsupplier from the system.
    public boolean deleteFoodSupplier(int supplierID){
        try {
            DeleteQuery deleteInstance = new DeleteQuery();
            String query = "CALL craveconnect.sp_DeleteFoodsupplier("+supplierID+");";
            deleteInstance.executeDelete(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
    
    //main is solely used to test the the current file.
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



