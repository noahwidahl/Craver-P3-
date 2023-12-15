package model;

import dbHelpers.ReadQuery;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class FoodItem  {
    // Attributes
    private int foodItemID;
    private String foodItemName;
    private double price;
    private String linkToFoodImage;
    private int foodSupplierID;
    private int foodItemCategoryID;
    private List<String> ingredients;    //Should had been an object of ingrediens

    // Constructor 1, used in method getAllFoodItems
    public FoodItem(int foodItemID, String foodItemName, double price, String linkToFoodImage, 
                    int foodSupplierID, int foodItemCategoryID) {
        this.foodItemID = foodItemID;
        this.foodItemName = foodItemName;
        this.price = price;
        this.linkToFoodImage = linkToFoodImage;
        this.foodSupplierID = foodSupplierID;
        this.foodItemCategoryID = foodItemCategoryID;
    }
    
    // Constructor 2, used in method searchDishes
    public FoodItem(String foodItemName, double price, String linkToFoodImage) {
        this.foodItemName = foodItemName;   // Setting the food item's name.
        this.price = price;         // Setting the food item's price.
        this.linkToFoodImage = linkToFoodImage; // Setting the URL for the food item's image.
    }
    
    //Constructor 3, taking FoodItemID as input to get information in DB
    public FoodItem(int FoodItemID){
        try {
            //Getting the foodItem information using the dbHelpers 
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.FoodItem where FoodItemID = "+FoodItemID+";";
            ResultSet resultSet = readInstance.readTableData(query);     
            boolean hasFirstRow = resultSet.next();
            //Controlling if statement, did the query return a result
            if(hasFirstRow){
                this.foodItemID = Integer.parseInt(resultSet.getString("foodItemID"));
                this.foodItemName = resultSet.getString("foodItemName");
                this.price = (int) Double.parseDouble(resultSet.getString("price"));
                this.linkToFoodImage = resultSet.getString("linkToFoodImage");
                this.foodSupplierID = Integer.parseInt(resultSet.getString("foodSupplierID"));
                this.foodItemCategoryID = Integer.parseInt(resultSet.getString("foodItemCategoryID"));
            }           
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    //Static method to fetch all FoodItems from the database
    //Returning a List of the FoodItem objects
    public static List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.FoodItem;";
            ResultSet resultSet = readInstance.readTableData(query);
            
            //while loop to go over each desired column from the table and creating an instance of the Fooditem using the constructor. 
            while (resultSet.next()) {
                FoodItem foodItem = new FoodItem(
                        //This is contructor 1, being used
                    resultSet.getInt("FoodItemID"),
                    resultSet.getString("FoodItemName"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("LinkToFoodImage"),
                    resultSet.getInt("FoodsupplierID"),
                    resultSet.getInt("FoodItemCategoryID")
                );
                //Adding the foodItem object to the list.
                foodItems.add(foodItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodItems;
    }
    
    //Static method to get fooditems based on suppliers ID
    //Returning a List of the fooditem objects
    public static List<FoodItem> getFoodItemsBySupplierID(int supplierID) {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.FoodItem WHERE FoodsupplierID = "+supplierID+";";
            
            ResultSet resultSet = readInstance.readTableData(query);
            
            //while loop to go over each desired column from the table and creating an instance of the Fooditem using the constructor. 
            while (resultSet.next()) {
                FoodItem foodItem = new FoodItem(
                    resultSet.getInt("FoodItemID"),
                    resultSet.getString("FoodItemName"),
                    resultSet.getDouble("Price"),
                    resultSet.getString("LinkToFoodImage"),
                    resultSet.getInt("FoodsupplierID"),
                    resultSet.getInt("FoodItemCategoryID")
                );
                //Adding the foodItem object to the list.
                foodItems.add(foodItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodItems;
    }


    //Static method with a List of foodItems as input, returning af HTML table to show on the home.jsp webpage
    public static String getAllFoodItems(List<FoodItem> foodItems) {
        //Using a stringbuilder to store the html table data.
        StringBuilder table = new StringBuilder("<table border='1'>");                                                                               //Start of table
        //Appending the headers to the html table.
        table.append("<tr><th>Food Item Name</th><th>Price</th><th>Link to Image</th><th>Supplier ID</th><th>Category ID</th></tr>");                //Headers of table
        //Appending each table data and table row based on the data from the FoodItem object. 
        for (FoodItem item : foodItems) {
            table.append("<tr>");                                                                                                                    //table row start
            //table.append("<td>").append(item.getFoodItemName()).append("</td>");
            table.append("<td><a href='foodItem?FoodItemID=").append(item.getFoodItemID()).append("'>"+item.getFoodItemName()+"</a></td>");        //Name of the food item
            table.append("<td>").append(item.getPrice()).append("</td>");                                                                       //Price of the food item
            table.append("<td><a href='").append(item.getLinkToFoodImage()).append("'>Image Link</a></td>");                                   //Link to the image
            table.append("<td>").append(item.getFoodSupplierID()).append("</td>");                                                              //Foodsuppliers ID
            table.append("<td>").append(item.getFoodItemCategoryID()).append("</td>");                                                          //food items category
            table.append("</tr>");                                                                                                                   //table row end
        }
        table.append("</table>");                                                                                                                    //End of table 
        return table.toString();
    }
    
    //Static method to render categories on home.jsp
    //returning a HashMap which stores the categoryID and item description from the database table.
    public static  Map<Integer, String> getAllCategories() throws SQLException {
        //Creating a HashMap of int and string
        Map<Integer, String> categories = new HashMap<>();
        // Creating an instance of ReadQuery to facilitate database interaction.
        ReadQuery readQuery = new ReadQuery();
        String query = "SELECT * FROM FoodCategory";
        try (ResultSet results = readQuery.readTableData(query)) {
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
        
        
        
    //Static method to search dishes based on a search term.
    //Returning a List of objects (FoodItem) 
    public static List<FoodItem> searchDishes(String searchTerm) throws SQLException {
        List<FoodItem> dishes = new ArrayList<>();
        // SQL query to fetch dishes based on a search term.
        String query = "SELECT FoodItemID, FoodItemName, Price, LinkToFoodImage FROM craveconnect.FoodItem WHERE FoodItemName LIKE ?";
        List<Object> params = new ArrayList<>();
        //Like search param for the query to the database.
        params.add("%" + searchTerm + "%");
        ReadQuery ReadInstance = new ReadQuery();
        
        // Executing the query and processing the result set.
        try (ResultSet results = ReadInstance.readTableDataWithParameters(query, params)) {
            while (results.next()) {
                //Extracting FoodItem attributes from the result set.
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
    
    //Static method to search dishes based on category ID.
    //Returning a List of objects (FoodItem) 
    public static List<FoodItem> searchDishesByCategory(String categoryId) throws SQLException {
        List<FoodItem> dishes = new ArrayList<>();
        // SQL query to fetch dishes based on category ID.
        String query = "SELECT FoodItemID, FoodItemName, Price, LinkToFoodImage FROM craveconnect.FoodItem WHERE FoodItemCategoryID = ?";
        //Making a List of objects to store the query statement paramters. 
        List<Object> params = new ArrayList<>();
        params.add(categoryId);
        
        // Executing the query and processing the result set.
        ReadQuery ReadInstance = new ReadQuery();
        try (ResultSet results = ReadInstance.readTableDataWithParameters(query, params)) {
            while (results.next()) {
                //Extracting FoodItem attributes from the result set.
                int foodItemId = results.getInt("FoodItemID");
                String name = results.getString("FoodItemName");
                double price = results.getDouble("Price");
                String imageUrl = results.getString("LinkToFoodImage");
                
                //Creating a FoodItem object and setting its ingredients.
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

    //Static method to get ingredients for a specific dish.
    //Returning a List of Strings storing the ingredients information.
    private static List<String> getIngredientsForFoodItem(int foodItemId) throws SQLException {
        List<String> ingredients = new ArrayList<>();
        // INNER JOIN between Ingredients and FoodItemIngredients tables to fetch names of ingredients associated with a specific food item. (Maybe make a vies to this)
        String query = "SELECT i.IngredientName " +
                       "FROM Ingredients i " +
                       "JOIN FoodItemIngredients fi ON i.IngredientID = fi.IngredientID " +
                       "WHERE fi.FoodItemID = ?";
        //Making a List of objects to store the query statement paramters. 
        List<Object> params = new ArrayList<>();
        params.add(foodItemId);
        
        // Executing the query and adding ingredients to the list.
        ReadQuery ReadInstance = new ReadQuery();
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
/*
    //main is solely used to test the the current file.
    public static void main(String[] args) throws SQLException {
        //RegisteredUser test = new RegisteredUser();
        //boolean var = RegisteredUser.registerUser("Dennis","Dennis123","123","D@D.dk");  //Insert into DB - Dette er det tættest på en funktion i java
        //RegisteredUser test = new RegisteredUser("Dennis123");  //id 24
        //System.out.println(test.UserID); //id 
        Map<Integer, String> categories = FoodItem.getAllCategories();
        // Splitting the string based on '='
        for (Map.Entry<Integer, String> entry : categories.entrySet()) {
            int id = entry.getKey();
            String value = entry.getValue();
            System.out.println("ID: " + id + ", Value: " + value);
        }

        
        //List<FoodItem> dishes = new ArrayList<>();
        //dishes= FoodItem.searchDishesByCategory("4");
        //System.out.println(dishes.get(0).getIngredients());
        FoodItem test = new FoodItem(1);

}
*/
}