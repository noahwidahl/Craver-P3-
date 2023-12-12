
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;
import java.sql.*;
/**
 *
 * @author Bokaj
 */

public class DeleteQuery extends CRUD {
    public DeleteQuery() throws SQLException {
        super(); // Calls the constructor of the CRUD class to establish a database connection
    }
    
        //Generic method to delete data row
    public int executeDelete(String query) throws SQLException {
        //check if query contains delete, otherwise throw exception
        if (query.toLowerCase().contains("delete")) {
            System.out.println("String contains delete statement");
        } else {
            System.out.println("String does not contain delete statement");
            throw new IllegalArgumentException("String does not contain delete statement");
        }
    
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }
    
    
    
}

