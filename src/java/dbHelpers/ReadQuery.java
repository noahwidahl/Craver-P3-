/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.HashMap;

public class ReadQuery extends CRUD {
    
    public ReadQuery() throws SQLException {
        super(); // Calls the constructor of the CRUD class to establish a database connection
    }
    //Generic method to get all table data
    public ResultSet ReadTableData(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    //Get specific user
    public ResultSet ReadUser(List<String> parameters, String baseQuery) throws SQLException {
        
        // StringBuilder to build the query dynamically
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        
        // Add WHERE clause if there are conditions
        if (!parameters.isEmpty()) {
            queryBuilder.append(" WHERE username = ? AND password = ?");
        }
        
        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
        //Validating to be developed down here
        
        // Set parameters based on the values in the ArrayList
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        
        return preparedStatement.executeQuery();
    }
     
    public String outputResultAsHtmlTable(ResultSet results) {
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
        while (results.next()) {
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = results.getString(i);
                table += "<td>" + columnValue + "</td>"; 
            }
            //System.out.println(results.getString(1));
            table += "</tr>";
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    table += "</table>";
    return table;
    }
    
    
    
    public String outputResultAsHtmlTableWithButtons(ResultSet results, HashMap<String, String> hashMap) {
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
        while (results.next()) {
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = results.getString(i);
                table += "<td>" + columnValue + "</td>";
                
                
            }
            //System.out.println(results.getString(1));
            for (String key : hashMap.keySet()) {
                //System.out.println("Key: " + key + ", Value: " + hashMap.get(key));
                //<form action="admin" method="post"><input type="hidden" name="parameterName" value="parameterValue"><button type="submit">Søg</button></form>   
                //table += "<td><button type='submit' class='"+key+"' id='BtnDeny' value='"+results.getString(1)+"'>"+hashMap.get(key)+"</button></td>";
                table += "<td><form action='admin' method='post'><input type='hidden' name='parameterName' value='"+key+": "+results.getString(1)+"'><button type='submit'>"+hashMap.get(key)+"</button></form></td>";
                //System.out.println(table);
            }
            table += "</tr>";
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    table += "</table>";
    return table;
    }
    
    
    
    public String outputResultAsText(ResultSet results) {
        // Generating SQL datatable as an HTML table
        String outputPlaceholder = "";
        try {
            // Get the ResultSetMetaData to obtain column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create table headers based on column names
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                outputPlaceholder = outputPlaceholder + columnName + ",";
                
            }
            outputPlaceholder = outputPlaceholder + "\n";

            // Loop through the result set and generate data rows
            while (results.next()) {
                
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = results.getString(i);
                    outputPlaceholder = outputPlaceholder + columnValue + ",";
                }
                outputPlaceholder = outputPlaceholder + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputPlaceholder;
    }
        
    
    // Testing example
    public static void main(String[] args) {
        try {
            ReadQuery readInstance = new ReadQuery();

            // Reading table data
            ResultSet result = readInstance.ReadTableData("SELECT * FROM craveconnect.Roles;");
            //Using the result from previous line, and uses in the the method getHTMLTable
            System.out.println(readInstance.outputResultAsHtmlTable(result));
            //making a new object to hold the result from the query
            ResultSet resultText = readInstance.ReadTableData("SELECT * FROM craveconnect.Foodsupplier;");
            //Using the method outputResult to show the result as text.
            System.out.println(readInstance.outputResultAsText(resultText));
           


            // Disconnecting from the database - disconnect() is a method from the abstract class CRUD
            readInstance.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}