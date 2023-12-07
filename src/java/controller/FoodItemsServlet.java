/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dbHelpers.ReadQuery;
import java.io.IOException;
import java.sql.SQLException;
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
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            List<FoodItem> foodItems = FoodItem.getFoodItemsBySupplierID(supplierId);
            String htmlTable = FoodItem.getAllFoodItems(foodItems);

            request.setAttribute("foodItemsTable", htmlTable);
            String url = "/foodItems.jsp"; // JSP page to display Food Items
            System.out.println("FoodSupplierServlet tester"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException ex) {
            Logger.getLogger(FoodItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
