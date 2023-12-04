package controller;

import dbHelpers.ReadFoodItem;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "UpdateFormServlet", urlPatterns = {"/update"})
public class UpdateFormServlet extends HttpServlet {

    // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Parsing the FoodItem ID from the request
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));

            // Using ReadRecord to fetch the specific FoodItem from the database
            ReadFoodItem rr = new ReadFoodItem(foodItemID);
            rr.doRead();
            FoodItem foodItem = rr.getFooditem();

            // Setting the FoodItem as a request attribute for access in the JSP
            request.setAttribute("FoodItem", foodItem);

            // URL of the JSP to display the update form
            String url = "/updateForm.jsp"; // Ensure this is the correct path to your update form JSP

            // Dispatching the request to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Handling NumberFormatException and sending a bad request error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid FoodItem ID");
        } catch (Exception e) {
            // Handling any other exceptions and sending an internal server error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred");
        }
    }

    // Method to return information about the servlet
    @Override
    public String getServletInfo() {
        return "Servlet for displaying the update form for FoodItem records";
    }
}
