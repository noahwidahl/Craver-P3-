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

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateFoodItem"})
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            int foodSupplierID = Integer.parseInt(request.getParameter("FoodSupplierID")); // Retrieve the supplier ID
            String name = request.getParameter("FoodItemName");
            BigDecimal price = new BigDecimal(request.getParameter("Price"));
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

            FoodItem foodItemToUpdate = new FoodItem(foodItemID, name, price, null, foodSupplierID, currentTimestamp, currentTimestamp);

            UpdateQuery uq = new UpdateQuery();
            uq.doUpdate(foodItemToUpdate);

            // Redirect to the read servlet with the correct supplier ID
            String url = "read?supplierId=" + foodSupplierID;
            response.sendRedirect(url);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: Invalid input. Please check the data.");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for updating FoodItem records";
    }
}
