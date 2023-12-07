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

@WebServlet(name = "FoodItemServlet", urlPatterns = {"/fooditemservlet"})
public class FoodItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int supplierId = Integer.parseInt(request.getParameter("supplierId"));
            List<FoodItem> foodItems = FoodItem.getFoodItemsBySupplierID(supplierId);
            ReadQuery readQuery = null;
            try {
                readQuery = new ReadQuery();
            } catch (SQLException ex) {
                Logger.getLogger(FoodItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String htmlTable = readQuery.outputFoodItemsAsHtmlTable(foodItems);

            request.setAttribute("foodItemsTable", htmlTable);
            String url = "/FoodItems.jsp"; // JSP page to display Food Items
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException ex) {
            Logger.getLogger(FoodItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
