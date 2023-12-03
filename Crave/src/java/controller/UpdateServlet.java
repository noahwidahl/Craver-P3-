package controller;

import dbHelpers.UpdateQuery;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateFoodItem"})
public class UpdateServlet extends HttpServlet {

    // Override the doPost method to handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Parsing and retrieving form data to update a FoodItem
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            int foodSupplierID = Integer.parseInt(request.getParameter("FoodSupplierID")); // Retrieve the supplier ID
            String name = request.getParameter("FoodItemName");
            BigDecimal price = new BigDecimal(request.getParameter("Price"));
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            // Creating a FoodItem object with the updated data
            FoodItem foodItemToUpdate = new FoodItem(foodItemID, name, price, null, foodSupplierID, currentTimestamp, currentTimestamp);

            // Creating an instance of UpdateQuery to handle the update operation
            UpdateQuery uq = new UpdateQuery();
            uq.doUpdate(foodItemToUpdate);

            // Redirecting to the read servlet with the correct supplier ID after update
            String url = "read?supplierId=" + foodSupplierID;
            response.sendRedirect(url);
        } catch (NumberFormatException e) {
            // Handling potential NumberFormatException and sending an error response
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Invalid input. Please check the data.");
        }
    }

    // Method to return information about the servlet
    @Override
    public String getServletInfo() {
        return "Servlet for updating FoodItem records";
    }
}
