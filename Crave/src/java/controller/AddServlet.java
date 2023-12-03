package controller;

import dbHelpers.AddQuery;
import java.io.IOException;
import java.math.BigDecimal;
import model.FoodItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "AddServlet", urlPatterns = {"/addFoodItem"})
public class AddServlet extends HttpServlet {

    // Override the doPost method to handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieving form data for adding a new FoodItem
            String name = request.getParameter("FoodItemName");
            String priceStr = request.getParameter("Price");
            String supplierIdStr = request.getParameter("FoodsupplierID");

            // Validating the input data
            if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty() || supplierIdStr == null || supplierIdStr.isEmpty()) {
                throw new IllegalArgumentException("Missing required fields.");
            }

            BigDecimal price;
            int foodSupplierID;
            // Validating and parsing the price
            try {
                price = new BigDecimal(priceStr);
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price must be positive.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Price format is invalid.");
                return;
            }

            // Validating and parsing the food supplier ID
            try {
                foodSupplierID = Integer.parseInt(supplierIdStr);
                if (foodSupplierID <= 0) {
                    throw new IllegalArgumentException("Invalid FoodsupplierID.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "FoodSupplierID format is invalid.");
                return;
            }

            // Creating a new FoodItem object
            FoodItem foodItem = new FoodItem(name, price, foodSupplierID);

            // Adding the new FoodItem to the database
            AddQuery aq = new AddQuery();
            aq.doAdd(foodItem);

            // Redirecting to the read page with the specific supplierId
            response.sendRedirect("read?supplierId=" + foodSupplierID);
        } catch (IllegalArgumentException e) {
            // Handling invalid input data
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (SQLException e) {
            // Handling SQL exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        } catch (Exception e) {
            // Handling other exceptions
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred processing the request.");
        }
    }

    // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirecting to the food item list page
        response.sendRedirect(request.getContextPath() + "/foodItemlist.jsp");
    }
}
