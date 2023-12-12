package controller;

import model.FoodItem;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;

// Annotation to define this class as a servlet and map it to a URL pattern.
@WebServlet(name = "SearchDishes", urlPatterns = {"/SearchDishes"})
public class SearchDishes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the search term and category ID from the request.
        String searchTerm = request.getParameter("searchTerm");
        String categoryId = request.getParameter("category");
        
        try {
            // Fetch all categories and set them as a request attribute for access in JSP.
            
            Map<Integer, String> categories = FoodItem.getAllCategories();
            
            
            
            
            request.setAttribute("categories", categories);
            
            // Handle search based on category ID.
            if (categoryId != null && !categoryId.isEmpty()) {
                // Fetch the dishes by category and set them as a request attribute.
                List<FoodItem> dishesByCategory = FoodItem.searchDishesByCategory(categoryId);
                request.setAttribute("dishes", dishesByCategory);
            } 
            // Handle search based on search term.
            else if (searchTerm != null && !searchTerm.isEmpty()) {
                // Fetch the dishes based on the search term and set them as a request attribute.
                List<FoodItem> dishes = FoodItem.searchDishes(searchTerm);
                request.setAttribute("dishes", dishes);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and set an error message as a request attribute.
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }

        // Forward the request to home.jsp to display dishes and categories.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);
    }
}
