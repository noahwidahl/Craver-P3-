/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import model.FoodItem;
/**
 *
 * @author sorennygaardjensen
 */
public class AddQuery {
    
    private Connection conn;
    
    public AddQuery(){
        
        
        Properties props = new Properties();
        InputStream instr = getClass() .getResourceAsStream("dbConn.properties");
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String driver = props.getProperty("driver.name");
        String url = props.getProperty("server.name");     
        String username = props.getProperty("user.name");
        String passwd = props.getProperty("user.password");    
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void doAdd (FoodItem FoodItem){
        
        try {
            String query = "INSERT INTO FoodItem (FoodItemName, Price) VALUES(?,?)";
            
            PreparedStatement ps = conn.prepareStatement(query);
            
            ps.setString(1, FoodItem.getFoodItemName());
            ps.setBigDecimal(2, FoodItem.getPrice());
            
            ps. executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AddQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
