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

@WebServlet(name = "FoodSupplier", urlPatterns = {"/foodsupplier"})
public class FoodSupplier extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ReadQuery readQuery = new ReadQuery();

            // Check if supplierId parameter is present
            String supplierIdParam = request.getParameter("supplierId");
            if (supplierIdParam != null && !supplierIdParam.isEmpty()) {
                int supplierId = Integer.parseInt(supplierIdParam);
                List<FoodItem> foodItems = readQuery.getFoodItemsBySupplierID(supplierId);
                request.setAttribute("foodItems", foodItems); // Set food items for the JSP

                String url = "/FoodItems.jsp"; // JSP page to display FoodItems
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } else {
                // If supplierId is not present, continue with existing logic
                List<String> foodSupplierInfo = readQuery.getAllFoodSupplierNames();
                if (foodSupplierInfo != null && !foodSupplierInfo.isEmpty()) {
                    request.setAttribute("foodSupplierInfo", foodSupplierInfo);
                } else {
                    System.out.println("No food suppliers found in the database.");
                }

                String url = "/FoodSupplier.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException | SQLException ex) {
            Logger.getLogger(FoodSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Implement any POST method logic if needed
    }

    @Override
    public String getServletInfo() {
        return "FoodSupplier Servlet";
    }
}
