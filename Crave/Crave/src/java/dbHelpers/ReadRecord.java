/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import model.FoodItem;

/**
 *
 * @author sorennygaardjensen
 */
public class ReadRecord {
    
    private Connection conn;
    private ResultSet results;
    
    private FoodItem FoodItem = new FoodItem();
    private int FoodItemID;
    
    public ReadRecord (int FoodItemID) {
    
    Properties props = new Properties();
        InputStream instr = getClass() .getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");     
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");
        
        this.FoodItemID = FoodItemID;
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
                
}
    
    public void doRead () {
    
        try {
            //set up a string to hold our query
            String query = "SELECT * FROM FoodItem WHERE FoodItemID =?";
            
            // create a preparedstatement
            PreparedStatement ps = conn.prepareStatement (query);
            
            //fill in the prepared statement
            ps.setInt(1,FoodItemID);
            
            //execute
            this.results = ps.executeQuery();
            
            this.results.next();
            
            FoodItem.setFoodItemID(this.results.getInt("FoodItemID"));
            FoodItem.setFoodItemName(this.results.getString("FoodItemName"));
            FoodItem.setPrice(this.results.getBigDecimal("Price"));
        } catch (SQLException ex) {
            Logger.getLogger(ReadRecord.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    }
    
    public FoodItem getFooditem(){
        
        return this.FoodItem;
    }
}
