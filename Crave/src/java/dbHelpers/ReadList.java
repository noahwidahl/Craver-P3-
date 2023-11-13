/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.FoodItem;


/**
 *
 * @author sorennygaardjensen
 */
public class ReadList {
    
    private Connection conn;
    private ResultSet results;
    
    public ReadList (){
    
    Properties props = new Properties();
    InputStream instr = getClass() .getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    String driver = props.getProperty("driver.name");
    String url = props.getProperty("server.name");
    String username = props.getProperty("user.name");
    String passwd = props.getProperty("user.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = (Connection) DriverManager.getConnection(url,username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   public void doRead(){
        try {
            String query = "SELECT * FROM FoodItem"; 
            PreparedStatement ps = conn.prepareStatement(query); // Correct PreparedStatement call
            this.results = ps.executeQuery(); // Corrected the ResultSet assignment
        } catch (SQLException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String getHTMLTable(){
    
        String table = "";
        
        table += "<table border=1>";
        
        try {
            while(this.results.next()){
                
                FoodItem FoodItem = new FoodItem();
                FoodItem.setFoodItemId(this.results.getInt("FoodItemID"));
                FoodItem.setFoodItemName(this.results.getString("FoodItemName"));
                
                table += "<tr>";
                table += "<td>";
                table += FoodItem.getFoodItemId();
                table += "</td>";
                
                table += "<td>";
                table += FoodItem.getFoodItemName();
                table += "</td>";
                  
                table += "<tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        table += "</table>";
        
                return table;
        
    
    }
    
    
  }
