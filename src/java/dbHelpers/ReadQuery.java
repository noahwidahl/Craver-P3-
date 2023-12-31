/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbHelpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.HashMap;
import model.FoodSupplier;

import model.FoodItem;


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
    
    public String outputResultAsHtmlTableWithInteractiveButtons(ResultSet results, int ColumnIdAsButton) {
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
                //If statement der bestemmer hvilke kolonner der skal se anderleds ud
                if(i == ColumnIdAsButton){
                    table += "<td>"+"<button class='TableButtons' type='button'>"+columnValue+"</button>" + "</td>"; 
                }else{
                    table += "<td>" + columnValue + "</td>";  
                        }
                
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
                table += "<td><form action='admin' method='post'><input type='hidden' name='parameterName' value='"+key+": "+results.getString(1)+"'><button type='submit' onclick='reloadPage()'>"+hashMap.get(key)+"</button></form></td>";
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
                // In ReadQuery class

    public String outputFoodSuppliersAsHtmlTable(List<String> suppliers) {
        String table = "<table border='1'>";
        table += "<tr><th>Supplier Name</th><th>Supplier ID</th></tr>";
        for (String supplier : suppliers) {
            String[] parts = supplier.split(" \\(");
            String name = parts[0];
            String id = parts[1].replace(")", "");
            table += "<tr><td>" + name + "</td><td>" + id + "</td></tr>";
        }
        table += "</table>";
        return table;
    }

    public String outputFoodItemsAsHtmlTable(List<FoodItem> foodItems) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    StringBuilder table = new StringBuilder("<table border='1'>");
    table.append("<tr><th>Food Item Name</th><th>Price</th><th>Link to Image</th><th>Supplier ID</th><th>Add Date</th><th>Modified Date</th><th>Category ID</th></tr>");

    for (FoodItem item : foodItems) {
        table.append("<tr>");
        table.append("<td>").append(item.getFoodItemName()).append("</td>");
        table.append("<td>").append(item.getPrice()).append("</td>");
        table.append("<td><a href='").append(item.getLinkToFoodImage()).append("'>Image Link</a></td>");
        table.append("<td>").append(item.getFoodSupplierID()).append("</td>");
        table.append("<td>").append(dateFormat.format(item.getAddDate())).append("</td>");
        table.append("<td>").append(dateFormat.format(item.getModifiedDate())).append("</td>");
        table.append("<td>").append(item.getFoodItemCategoryID()).append("</td>");
        table.append("</tr>");
    }

    table.append("</table>");
    return table.toString();
}

    public static void main(String[] args) {
        try {
            ReadQuery readInstance = new ReadQuery();
            // Fetch data from the model and display as HTML table
            List<String> supplierList = FoodSupplier.getAllFoodSupplierNamesWithIDs();
            String htmlTable = readInstance.outputFoodSuppliersAsHtmlTable(supplierList);
            System.out.println(htmlTable);

            // Other example usage of ReadQuery methods
            ResultSet result = readInstance.ReadTableData("SELECT * FROM craveconnect.Roles;");
            System.out.println(readInstance.outputResultAsHtmlTable(result));
            ResultSet resultText = readInstance.ReadTableData("SELECT * FROM craveconnect.Foodsupplier;");
            System.out.println(readInstance.outputResultAsText(resultText));

            // Disconnect from the database
            readInstance.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ResultSet ReadTableData(String query, int supplierID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}