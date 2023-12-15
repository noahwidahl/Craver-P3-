/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;

/**
 *
 * @author Bokaj
 */
@WebServlet(name = "FoodItemsServlet", urlPatterns = {"/foodItems"})
public class FoodItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            //Reading the supplierId given in the URL, eks http://localhost:8080/Crave/foodItems?supplierId=6
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            //Getting a list of fooditems related to a foodsupplier
            List<FoodItem> foodItems = FoodItem.getFoodItemsBySupplierID(supplierId);
            //Creating a HTML table, taking the input as a list of foodItems and turning it into an HTML table  
            String htmlTable = FoodItem.getAllFoodItems(foodItems);
            //Setting the genereted HTML and forwarding it to the page.
            request.setAttribute("foodItemsTable", htmlTable);
            String url = "/foodItems.jsp"; // JSP page to display Food Items
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException ex) {
            Logger.getLogger(FoodItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
