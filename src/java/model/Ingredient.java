/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dbHelpers.ReadQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bokaj
 */
public class Ingredient {
    int ingredientID;
    String ingredientName;
    
    //Constructor
    public Ingredient(){
        try {
            //Getting the ingredient information using the dbHelpers 
            ReadQuery readInstance = new ReadQuery();
            String query = "SELECT * FROM craveconnect.Ingredients;";
            ResultSet resultSet = readInstance.readTableData(query);
            
            //Controlling if statement, did the query return a result
            boolean hasFirstRow = resultSet.next();
            if(hasFirstRow){
                //Assigning all the attributes with values from the DB
                this.ingredientID = Integer.parseInt(resultSet.getString("ingredientID"));     //Parsing string to Int
                this.ingredientName = resultSet.getString("ingredientName");
            }        
        } catch (SQLException ex) {
            Logger.getLogger(RegisteredUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Methods
    //Static method to handle the showcase of all ingrediens.
    /* Not used anymore i believe.
    public static void getAllIngredients(String ingredientsQuery) {
        try {
            ReadQuery readInstance = new ReadQuery();   //Creating ReadQuery object
            ResultSet result = readInstance.readTableData(ingredientsQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fejl");
        }
    }
    */
    
    //Static method to 
    /*
    public static void getSpecificIngredients(String ingredientsQuery) {
        try {
            System.out.println(ingredientsQuery);
            ReadQuery readInstance = new ReadQuery();   //Creating ReadQuery object
            
            ResultSet result = readInstance.readTableData(ingredientsQuery);
            //System.out.println(readInstance.outputResultAsHtmlTable(result));
            System.out.println(readInstance.outputResultAsText(result));
 

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("fejl");
            
        }
    }
    */
}    
        
/*

        // Metode til at hente ingredienser for en given ret
        List<String> getIngredientsForFoodItem(int foodItemId) {
            List<String> ingredients = new ArrayList<>();
            try {
                // Opretter forbindelse til databasen
                Connection ingredientConn = DriverManager.getConnection(dbURL, user, pass);

                // SQL-forespørgsel for at hente ingredienser til en given ret
                String ingredientQuery = "SELECT * FROM FoodItemIngredients WHERE FoodItemID = ?";
                PreparedStatement ingredientStmt = ingredientConn.prepareStatement(ingredientQuery);
                ingredientStmt.setInt(1, foodItemId);
                ResultSet ingredientRs = ingredientStmt.executeQuery();

                // Henter ingredienserne og tilføjer dem til listen
                while (ingredientRs.next()) {
                    int ingredientId = ingredientRs.getInt("IngredientID");
                    ingredients.add(getIngredientName(ingredientId));
                }

                // Lukker ressourcer
                ingredientRs.close();
                ingredientStmt.close();
                ingredientConn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ingredients;
        }

        // Metode til at hente navnet på en ingrediens baseret på IngredientID
        String getIngredientName(int ingredientId) {
            String ingredientName = "";
            try {
                // Opretter forbindelse til databasen
                Connection ingredientNameConn = DriverManager.getConnection(dbURL, user, pass);

                // SQL-forespørgsel for at hente navnet på en ingrediens
                String ingredientNameQuery = "SELECT * FROM Ingredients WHERE IngredientID = ?";
                PreparedStatement ingredientNameStmt = ingredientNameConn.prepareStatement(ingredientNameQuery);
                ingredientNameStmt.setInt(1, ingredientId);
                ResultSet ingredientNameRs = ingredientNameStmt.executeQuery();

                // Henter navnet på ingrediensen
                if (ingredientNameRs.next()) {
                    ingredientName = ingredientNameRs.getString("IngredientName");
                }

                // Lukker ressourcer
                ingredientNameRs.close();
                ingredientNameStmt.close();
                ingredientNameConn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ingredientName;
        }*/

    



