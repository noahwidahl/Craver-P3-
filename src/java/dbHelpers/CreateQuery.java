

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

public class CreateQuery extends CRUD {
    //Constructor to inherent from the CRUD superclass
    public CreateQuery() throws SQLException {
        super(); // Calls the constructor of the CRUD class to establish a database connection
    }

    //Generic method to insert data row
    public int executeInsert(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeUpdate();
    }

}
