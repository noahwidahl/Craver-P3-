package controller;

import dbHelpers.FoodSupplierReadQuery;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "FoodSuppliersServlet", urlPatterns = {"/foodsuppliersservlet"})
public class FoodSuppliersServlet extends HttpServlet {

    // Override the doGet method to handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Creating an instance of FoodSupplierReadQuery
        FoodSupplierReadQuery rq = new FoodSupplierReadQuery();
        rq.doRead(); // Executing the read operation to fetch food suppliers

        // Getting the HTML table with food supplier data
        String table = rq.getHTMLTable();

        // Setting the HTML table as a request attribute
        request.setAttribute("table", table);

        // URL of the JSP file for displaying food suppliers
        String url = "/foodSupplier.jsp"; // Ensure this matches the JSP file name

        // Dispatching the request to the specified JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
