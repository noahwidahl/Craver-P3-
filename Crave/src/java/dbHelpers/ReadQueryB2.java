/*
package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Crave;


public class ReadQueryB2 {
    private String url;
    private String user;
    private String password;
    private Connection conn;
    private ResultSet results;
    
    // Making a contructor defining database credentials
    public ReadQueryB2(){
        this.url = "jdbc:mysql://projectdatabase3.mysql.database.azure.com:3306/craveconnect";
        this.user = "Jakob";
        this.password = "1234";
        this.conn = null;
        
        try {
            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("Connected to Azure MySQL server");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing the connection: " + e.getMessage());
            }
        }
        
    }
    
    public void doRead(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM craveconnect.roles;");
            this.results = ps.executeQuery();
            conn.close();
            System.out.println(this.results);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
    
    public String getHTMLTable(){
        
        String table ="";
        table += "<table border=1>";
        
        try {
            while(this.results.next()){
                Crave crave = new Crave();
                crave.setRoleID(this.results.getInt("RoleID"));
                crave.setRoleDescription(this.results.getString("RoleDescription"));
                
                table += "<tr>";
                table += "<td>";
                table += crave.getRoleID();
                table += "</td>";
                
                table += "<td>";
                table += crave.getRoleDescription();
                table += "</td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        table += "</table>";
        return table;
        
    }
    
}
*/