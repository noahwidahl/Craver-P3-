package dbHelpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import model.FoodItem;
import model.FoodSupplier;

public class ReadQuery extends CRUD {
    
    public ReadQuery() throws SQLException {
        super(); // Calls the constructor of the CRUD class to establish a database connection
    }

    // Generic method to get all table data
    public ResultSet ReadTableData(String query) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    // Get specific user
    public ResultSet ReadUser(List<String> parameters, String baseQuery) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        if (!parameters.isEmpty()) {
            queryBuilder.append(" WHERE username = ? AND password = ?");
        }
        PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
        for (int i = 0; i < parameters.size(); i++) {
            preparedStatement.setString(i + 1, parameters.get(i));
        }
        return preparedStatement.executeQuery();
    }

    // Output result as HTML table
    public String outputResultAsHtmlTable(ResultSet results) {
        String table = "<table border=1>";
        try {
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                table += "<th>" + columnName + "</th>";
            }
            table += "</tr>";
            while (results.next()) {
                table += "<tr>";
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = results.getString(i);
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

    // Output result as HTML table with buttons
    public String outputResultAsHtmlTableWithButtons(ResultSet results, HashMap<String, String> hashMap) {
        String table = "<table border=1>";
        try {
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            table += "<tr>";
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                table += "<th>" + columnName + "</th>";
            }
            table += "</tr>";
            while (results.next()) {
                table += "<tr>";
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = results.getString(i);
                    table += "<td>" + columnValue + "</td>";
                }
                table += "<td value='"+results.getString(1)+"'><button> Approved</button></td>";
                table += "<td><button> Deny</button></td>";
                table += "</tr>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        table += "</table>";
        return table;
    }

    // Output result as text
    public String outputResultAsText(ResultSet results) {
        String outputPlaceholder = "";
        try {
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                outputPlaceholder += columnName + ",";
            }
            outputPlaceholder += "\n";
            while (results.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = results.getString(i);
                    outputPlaceholder += columnValue + ",";
                }
                outputPlaceholder += "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return outputPlaceholder;
   // Method to fetch all FoodSupplier names
   
 
    }
      public List<FoodItem> getFoodItemsBySupplierID(int supplierID) {
        List<FoodItem> foodItems = new ArrayList<>();
        String query = "SELECT * FROM craveconnect.FoodItem WHERE FoodsupplierID = ?;";
    
    try {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, supplierID);  // Set the FoodsupplierID in the query
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            FoodItem foodItem = new FoodItem(
                resultSet.getInt("FoodItemID"),
                resultSet.getString("FoodItemName"),
                resultSet.getDouble("Price"),
                resultSet.getString("LinkToFoodImage"),
                resultSet.getInt("FoodsupplierID"),
                resultSet.getDate("AddDate"),
                resultSet.getDate("ModifiedDate"),
                resultSet.getInt("FoodItemCategoryID")
            );
            foodItems.add(foodItem);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodItems;
}


    // Method to fetch supplier names and their IDs, concatenating them as a single string
    public List<String> getAllFoodSupplierNames() {
        List<String> supplierList = new ArrayList<>();
        String query = "SELECT FoodsupplierID, FoodsupplierName FROM craveconnect.Foodsupplier;";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("FoodsupplierID");
                String name = resultSet.getString("FoodsupplierName");
                String combinedInfo = name + " (" + id + ")";
                supplierList.add(combinedInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supplierList;
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