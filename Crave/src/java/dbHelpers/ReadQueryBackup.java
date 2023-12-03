/*

/*
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Crave;


public class ReadQuery {
    private String url;
    private String user;
    private String password;
    private Connection conn;
    private ResultSet results;
    
     // Making a contructor defining database credentials
    public ReadQuery(){
        //System.out.println("ReadQuery");
        //Reading dbConn.properties for database credentials
        Properties props = new Properties(); //MWC
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        url = props.getProperty("server.name");
        user = props.getProperty("user.name");
        password = props.getProperty("user.password");

        // Establish the connection to the database
        try {  
            this.conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connected to Azure MySQL server");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } 
    }
    
    public void doRead(String query){
        //System.out.println("doRead()");
        //Executing SQL statement
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
            System.out.println(this.results);


        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public String getHTMLTable() {
    // Generating SQL datatable as an HTML table
    String table = "<table border=1>";

    try {
        // Get the ResultSetMetaData to obtain column names
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Create table headers based on column names
        table += "<tr>";
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            table += "<th>" + columnName + "</th>";
        }
        table += "</tr>";

        // Loop through the result set and generate data rows
        while (this.results.next()) {
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = this.results.getString(i);
                table += "<td>" + columnValue + "</td>";
            }
            table += "</tr>";
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    table += "</table>";
    return table;
}
    
}

package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Crave;


public class ReadQuery {
    private String url;
    private String user;
    private String password;
    private Connection conn;
    private ResultSet results;
    
     // Making a contructor defining database credentials
    public ReadQuery(){
        //System.out.println("ReadQuery");
        //Reading dbConn.properties for database credentials
        Properties props = new Properties(); //MWC
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        url = props.getProperty("server.name");
        user = props.getProperty("user.name");
        password = props.getProperty("user.password");

        // Establish the connection to the database
        try {  
            this.conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connected to Azure MySQL server");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } 
    }
    
    public void doRead(){
        //System.out.println("doRead()");
        //Executing SQL statement
        try {
            String query = "SELECT * FROM craveconnect.roles;";
            PreparedStatement ps = conn.prepareStatement(query);
            this.results = ps.executeQuery();
            System.out.println(this.results);


        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public String getHTMLTable() {
    // Generating SQL datatable as an HTML table
    String table = "<table border=1>";

    try {
        // Get the ResultSetMetaData to obtain column names
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Create table headers based on column names
        table += "<tr>";
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            table += "<th>" + columnName + "</th>";
        }
        table += "</tr>";

        // Loop through the result set and generate data rows
        while (this.results.next()) {
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = this.results.getString(i);
                table += "<td>" + columnValue + "</td>";
            }
            table += "</tr>";
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    table += "</table>";
    return table;
}
    
}

/* 
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

*/