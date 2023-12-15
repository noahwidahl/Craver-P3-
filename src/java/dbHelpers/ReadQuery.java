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
import java.util.List;
import java.util.HashMap;
import model.FoodSupplier;


public class ReadQuery extends CRUD {
    //Constructor to inherent from the CRUD superclass
    public ReadQuery() throws SQLException {
        super(); // Calls the constructor of the CRUD class to establish a database connection
    }
    //Generic method to get all table data
    //Returning a ResultSet object to store the queried data.
    public ResultSet readTableData(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    
    //Method to get specific users data
    //Returning a ResultSet object to store the queried data.
    public ResultSet readUser(List<String> parameters, String baseQuery) throws SQLException {
        // StringBuilder to build the query dynamically
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        // Add WHERE clause if there are conditions
        if (!parameters.isEmpty()) {
            queryBuilder.append(" WHERE username = ? AND password = ?");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
        // Set parameters based on the values in the ArrayList
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        return preparedStatement.executeQuery();
    }
    
    //Method to ouput ResultSets as a html table
    //Returning the html table used broadly on the webpages
    public String outputResultAsHtmlTable(ResultSet results) {
    // Generating SQL datatable as an HTML table
    String table = "<table border=1>";                                                                          //Start of html table
    try {
        // Get the ResultSetMetaData to obtain column names
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Create table headers based on column names
        table += "<tr>";                                                                                        //Header table row start
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            table += "<th>" + columnName + "</th>";
        }
        table += "</tr>";                                                                                       //Header table row end

        // Loop through the result set and generate table rows adn table data
        while (results.next()) {
            table += "<tr>";                                                                                    //table row start
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = results.getString(i);
                table += "<td>" + columnValue + "</td>";                                                        //adding table data for each column
            }
            table += "</tr>";                                                                                   //table row end
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }

    table += "</table>";                                                                                        //End of html table
    return table;
    }
    
    //Method to ouput ResultSets as a html table and adding interactive buttons
    //Returning the html table with added buttons
    public String outputResultAsHtmlTableWithButtons(ResultSet results, HashMap<String, String> hashMap, String action) {
        // Generating SQL datatable as an HTML table
        StringBuilder tableBuilder = new StringBuilder("<table border=1>");

        try {
            // Get the ResultSetMetaData to obtain column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create table headers based on column names
            tableBuilder.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                tableBuilder.append("<th>").append(columnName).append("</th>");
            }
            tableBuilder.append("</tr>");

            // Loop through the result set and generate data rows
            while (results.next()) {
                tableBuilder.append("<tr>");        //Table row start
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = results.getString(i);
                    tableBuilder.append("<td>").append(columnValue).append("</td>");    //Table data
                }
                
                //Adding the interactive buttons based on values from the HashMap inputted
                for (String key : hashMap.keySet()) {
                    tableBuilder.append("<td><form action='").append(action)    //Adding button start
                        .append("' method='post'><input type='hidden' name='parameterName' value='")    //adding of meta data in html
                        .append(key).append(": ").append(results.getString(1))  //adding the HashMap data
                        .append("'><button type='submit' onclick='reloadPage()'>")      //adding last of meta data.
                        .append(hashMap.get(key)).append("</button></form></td>");  //Adding button end
                }
                tableBuilder.append("</tr>");       //table row end
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableBuilder.append("</table>");
        return tableBuilder.toString();
    }
    
    
    //Test method to output Resultsset as console log.
    //Returning a string with the stored data.
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
                
    //Method to handle queries with paramters (used in FoodItem and Ingredients seaching)
    //Returning a ResultSet object 
    public ResultSet readTableDataWithParameters(String query, List<Object> params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //Setting the parameters for the preparestatement
        for (int i = 0; i < params.size(); i++) {
            preparedStatement.setObject(i + 1, params.get(i));
        }
        return preparedStatement.executeQuery();
    }    
    
    //main is solely used to test the the current file.
    public static void main(String[] args) {
        try {
            ReadQuery readInstance = new ReadQuery();
            // Fetch data from the model and display as HTML table
            List<String> supplierList = FoodSupplier.getAllFoodSupplierNamesWithIDs();


            // Other example usage of ReadQuery methods
            ResultSet result = readInstance.readTableData("SELECT * FROM craveconnect.Roles;");
            //System.out.println(readInstance.outputResultAsHtmlTable(result));
            ResultSet resultText = readInstance.readTableData("SELECT * FROM craveconnect.Foodsupplier;");
            //System.out.println(readInstance.outputResultAsText(resultText));

            // Disconnect from the database
            readInstance.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}