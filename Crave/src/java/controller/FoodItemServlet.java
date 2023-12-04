package controller;

import dbHelpers.FoodItemReadQuery;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "FoodItemServlet", urlPatterns = {"/foodSuppliersFoodItems"})
public class FoodItemServlet extends HttpServlet {

    // Override the doGet method to handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieving the supplierId parameter from the request
        String supplierIdParam = request.getParameter("supplierId");
        System.out.println("Supplier ID in ReadServlet: " + supplierIdParam); // Debug line for logging

        String table; // Variable to hold the HTML table
        String url; // Variable to hold the URL of the JSP page

        // Check if supplierId parameter is provided and not empty
        if (supplierIdParam != null && !supplierIdParam.isEmpty()) {
            // Parsing the supplierId parameter to an integer
            int supplierId = Integer.parseInt(supplierIdParam);

            // Creating an instance of ReadQuery with the supplierId
            FoodItemReadQuery rq = new FoodItemReadQuery(supplierId);
            rq.doRead(); // Executing the read operation
            table = rq.getHTMLTable(); // Getting the HTML table with data
            url = "foodItemList.jsp"; // JSP file for displaying food items of a specific supplier
        } else {
            // Creating an instance of ReadQuery without a supplierId (for all items)
            FoodItemReadQuery rq = new FoodItemReadQuery();
            rq.doRead(); // Executing the read operation
            table = rq.getHTMLTable(); // Getting the HTML table with data
            url = "/foodSuppliersFoodItems.jsp"; // JSP file for displaying all food items
        }

        // Setting attributes for the request
        request.setAttribute("table", table); // The HTML table
        request.setAttribute("supplierId", supplierIdParam); // The supplierId, if provided

        // Dispatching the request to the specified JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
