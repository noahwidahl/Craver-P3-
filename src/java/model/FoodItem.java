package model;

import dbHelpers.ReadQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class FoodItem {
    // Attributes
    private int foodItemID;
    private String foodItemName;
    private double price;
    private String linkToFoodImage;
    private int foodSupplierID;
    private Date addDate;
    private Date modifiedDate;
    private int foodItemCategoryID;
    private List<String> ingredients;

    // Constructor
    public FoodItem(int foodItemID, String foodItemName, double price, String linkToFoodImage, 
                    int foodSupplierID, Date addDate, Date modifiedDate, int foodItemCategoryID) {
        this.foodItemID = foodItemID;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.foodSupplierID = foodSupplierID;
        this.addDate = addDate;
        this.modifiedDate = modifiedDate;
        this.foodItemCategoryID = foodItemCategoryID;
    }
    
    // Constructor
    public FoodItem(String foodItemName, double price, String linkToFoodImage) {
        this.foodItemName = foodItemName;   // Setting the food item's name.
        this.price = price;         // Setting the food item's price.
        this.linkToFoodImage = linkToFoodImage; // Setting the URL for the food item's image.
    }
    

    // Method to fetch all FoodItems from the database
    public static List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.FoodItem;";
            ResultSet resultSet = readInstance.ReadTableData(query);

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
            Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodItems;
    }
    
public static List<FoodItem> getFoodItemsBySupplierID(int supplierID) {
    List<FoodItem> foodItems = new ArrayList<>();
    try {
        ReadQuery readInstance = new ReadQuery();
        String query = "SELECT * FROM craveconnect.FoodItem WHERE FoodsupplierID = ?;";
        ResultSet resultSet = readInstance.ReadTableData(query, supplierID);

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
        Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
    }
    return foodItems;
}


        // Method with a List of foodItems as input, returning af HTML table to show on the webpage
        public static String getAllFoodItems(List<FoodItem> foodItems) {
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
        
        
        
    public static  Map<Integer, String> getAllCategories() throws SQLException {
        Map<Integer, String> categories = new HashMap<>();
        // Creating an instance of ReadQuery to facilitate database interaction.
        ReadQuery readQuery = new ReadQuery();
        String query = "SELECT * FROM FoodCategory";

        try (ResultSet results = readQuery.readCategories()) {
            // Looping through the ResultSet to extract category data.
            while (results.next()) {
                // Extracting category ID and description from the current row.
                int categoryId = results.getInt("FoodItemCategoryID");
                String categoryDescription = results.getString("FoodItemDescription");

                // Adding values to the HashMap
                categories.put(categoryId, categoryDescription);
            }
        } catch (SQLException e) {
            // Handling any SQL related exceptions.
        } finally {
            // Closing database connection.
            readQuery.disconnect();
        }

        return categories;
    }   
        
        
        
      // Method to search dishes based on a search term.
    public static List<FoodItem> searchDishes(String searchTerm) throws SQLException {
        List<FoodItem> dishes = new ArrayList<>();
        // SQL query to fetch dishes based on a search term.
        String query = "SELECT FoodItemID, FoodItemName, Price, LinkToFoodImage FROM craveconnect.FoodItem WHERE FoodItemName LIKE ?";
        List<Object> params = new ArrayList<>();
        params.add("%" + searchTerm + "%");
        
        ReadQuery ReadInstance = new ReadQuery();
        
        // Executing the query and processing the result set.
        try (ResultSet results = ReadInstance.readTableDataWithParameters(query, params)) {
            while (results.next()) {
                 // Extracting FoodItem attributes from the result set.
                int foodItemId = results.getInt("FoodItemID");
                String name = results.getString("FoodItemName");
                double price = results.getDouble("Price");
                String imageUrl = results.getString("LinkToFoodImage");

                // Creating a FoodItem object and setting its ingredients.
                FoodItem item = new FoodItem(name, price, imageUrl);
                List<String> ingredients = getIngredientsForFoodItem(foodItemId);
                item.setIngredients(ingredients);
                dishes.add(item);
            }
        } catch (SQLException e) {
            // Handling SQL errors.
        } finally {
            //Closing the database connection.
            ReadInstance.disconnect();
        }

        return dishes;
    }
    
    // Method to search dishes based on category ID.
    public static List<FoodItem> searchDishesByCategory(String categoryId) throws SQLException {
        List<FoodItem> dishes = new ArrayList<>();
        // SQL query to fetch dishes based on category ID.
        String query = "SELECT FoodItemID, FoodItemName, Price, LinkToFoodImage FROM craveconnect.FoodItem WHERE FoodItemCategoryID = ?";
        List<Object> params = new ArrayList<>();
        params.add(categoryId);
        
        ReadQuery ReadInstance = new ReadQuery();
        
        // Executing the query and processing the result set.
        try (ResultSet results = ReadInstance.readTableDataWithParameters(query, params)) {
            while (results.next()) {
                 // Similar extraction and object creation as in searchDishes method.
                int foodItemId = results.getInt("FoodItemID");
                String name = results.getString("FoodItemName");
                double price = results.getDouble("Price");
                String imageUrl = results.getString("LinkToFoodImage");

                FoodItem item = new FoodItem(name, price, imageUrl);
                List<String> ingredients = getIngredientsForFoodItem(foodItemId);
                item.setIngredients(ingredients);
                dishes.add(item);
            }
        } catch (SQLException e) {
            // Handling SQL errors.
        } finally {
            // Closing the database connection.
            ReadInstance.disconnect();
        }

        return dishes;
    }

     // Method to get ingredients for a specific dish.
    private static List<String> getIngredientsForFoodItem(int foodItemId) throws SQLException {
        List<String> ingredients = new ArrayList<>();
        // INNER JOIN between Ingredients and FoodItemIngredients tables to fetch names of ingredients associated with a specific food item.
        String query = "SELECT i.IngredientName " +
                       "FROM Ingredients i " +
                       "JOIN FoodItemIngredients fi ON i.IngredientID = fi.IngredientID " +
                       "WHERE fi.FoodItemID = ?";
        List<Object> params = new ArrayList<>();
        params.add(foodItemId);
        
        ReadQuery ReadInstance = new ReadQuery();
        
        // Executing the query and adding ingredients to the list.
        try (ResultSet results = ReadInstance.readTableDataWithParameters(query, params)) {
            while (results.next()) {
                ingredients.add(results.getString("IngredientName"));
            }
        }

        return ingredients;
    }   
          

 // Getters
    public int getFoodItemID() {
        return foodItemID;
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public double getPrice() {
        return price;
    }

    public String getLinkToFoodImage() {
        return linkToFoodImage;
    }

    public int getFoodSupplierID() {
        return foodSupplierID;
    }

    public Date getAddDate() {
        return addDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public int getFoodItemCategoryID() {
        return foodItemCategoryID;
    }

    // Setters
    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLinkToFoodImage(String linkToFoodImage) {
        this.linkToFoodImage = linkToFoodImage;
    }

    public void setFoodSupplierID(int foodSupplierID) {
        this.foodSupplierID = foodSupplierID;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setFoodItemCategoryID(int foodItemCategoryID) {
        this.foodItemCategoryID = foodItemCategoryID;
    }
    
     // Getter and setter for ingredients
    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

  // Testing example
   public static void main(String[] args) throws SQLException {
       //RegisteredUser test = new RegisteredUser();
       //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
       //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
       //System.out.println(test.UserID); //id 
        
       /*
           Map<Integer, String> categories = FoodItem.getAllCategories();
           // Splitting the string based on '='
            for (Map.Entry<Integer, String> entry : categories.entrySet()) {
             int id = entry.getKey();
             String value = entry.getValue();

             System.out.println("ID: " + id + ", Value: " + value);
         }

        */
       List<FoodItem> dishes = new ArrayList<>();
       dishes= FoodItem.searchDishesByCategory("4");
       System.out.println(dishes.get(0).getIngredients());
        

}

}