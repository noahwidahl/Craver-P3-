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

@WebServlet(name = "AddServlet", urlPatterns = {"/addFoodItem"})
public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("FoodItemName");
            String priceStr = request.getParameter("Price");
            String supplierIdStr = request.getParameter("FoodsupplierID");
            String returnSupplierId = request.getParameter("returnSupplierId"); // Retrieve the returnSupplierId

            if (name == null || name.isEmpty() || priceStr == null || priceStr.isEmpty() || supplierIdStr == null || supplierIdStr.isEmpty()) {
                throw new IllegalArgumentException("Missing required fields.");
            }

            BigDecimal price;
            int foodSupplierID;
            try {
                price = new BigDecimal(priceStr);
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price must be positive.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Price format is invalid.");
                return;
            }

            try {
                foodSupplierID = Integer.parseInt(supplierIdStr);
                if (foodSupplierID <= 0) {
                    throw new IllegalArgumentException("Invalid FoodsupplierID.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "FoodSupplierID format is invalid.");
                return;
            }

            FoodItem foodItem = new FoodItem(name, price, foodSupplierID);
            AddQuery aq = new AddQuery();
            aq.doAdd(foodItem);

            // Redirect back to the read page with the specific supplierId
             response.sendRedirect("read?supplierId=" + foodSupplierID);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred processing the request.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/foodItemlist.jsp");
    }
}
