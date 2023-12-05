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

/**
 *
 * @author Bokaj
 */
public abstract class CRUD {
    protected Connection connection;
    private String url;
    private String user;
    private String password;
    
    //Constructor
    public CRUD() throws SQLException {
        //Reading dbConn.properties for database credentials
        Properties props = new Properties(); //MWC
        InputStream instr = getClass().getResourceAsStream("dbConn.properties");
        //Reading the file
        try {
            props.load(instr);
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Closing the file
        try {
            instr.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Setting file content to values
        url = props.getProperty("server.name");
        user = props.getProperty("user.name");
        password = props.getProperty("user.password");

        // Establish the connection to the database
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  //Loading driver before this.connection - Removes the error Cannot invoke "java.sql.Connection.prepareStatement(String)" because "this.connection" is null
            this.connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Connected to MySQL server");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        } 
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            //Closing the MySQL server
            connection.close();
        }
    }
}


