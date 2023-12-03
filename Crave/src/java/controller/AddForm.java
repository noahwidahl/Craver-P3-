package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "AddForm", urlPatterns = {"/add"})
public class AddForm extends HttpServlet {

    // Override the doGet method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieving the foodSupplierID parameter from the request
        String foodSupplierID = request.getParameter("supplierId");

        // Check if the foodSupplierID parameter is provided and not empty
        if (foodSupplierID != null && !foodSupplierID.trim().isEmpty()) {
            // Setting the FoodSupplierID as a request attribute
            request.setAttribute("FoodSupplierID", foodSupplierID);

            // URL of the JSP file for adding a new food item
            String url = "/add.jsp";

            // Dispatching the request to the add.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            // Redirecting to the foodSupplier.jsp page if foodSupplierID is not provided
            response.sendRedirect("foodSupplier.jsp");
        }
    }

    // Method to return information about the servlet
    @Override
    public String getServletInfo() {
        return "AddForm servlet handles forwarding to the add item form.";
    }
}
