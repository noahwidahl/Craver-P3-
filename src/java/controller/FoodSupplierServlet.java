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
import model.FoodSupplier;

@WebServlet(name = "FoodSupplierServlet", urlPatterns = {"/foodsupplier"})
public class FoodSupplierServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Fetching supplier names and IDs from the model
            List<String> foodSupplierInfo = FoodSupplier.getAllFoodSupplierNamesWithIDs();
            request.setAttribute("foodSupplierNames", foodSupplierInfo);

            String url = "/FoodSupplier.jsp"; // JSP page to display Food Suppliers
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FoodSupplierServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Implement any POST method logic if needed
    }

    @Override
    public String getServletInfo() {
        return "FoodSupplier Servlet";
    }
}
