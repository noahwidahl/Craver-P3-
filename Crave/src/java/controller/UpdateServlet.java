/*
 * Servlet class to handle updating FoodItem records.
 */
package controller;

import dbHelpers.UpdateQuery;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import model.FoodItem;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateFoodItem"})
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setting the content type of the servlet response to HTML
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Writing HTML content to the response
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP GET method by redirecting to doPost method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP POST method for updating a FoodItem.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extracting form data to update the FoodItem
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            String name = request.getParameter("FoodItemName");
            BigDecimal price = new BigDecimal(request.getParameter("Price"));
            
            // Setting up a FoodItem object with the received data
            FoodItem FoodItem = new FoodItem();
            FoodItem.setFoodItemID(foodItemID);
            FoodItem.setFoodItemName(name);
            FoodItem.setPrice(price);
            
            // Creating an UpdateQuery object to handle the database update operation
            UpdateQuery uq = new UpdateQuery();
            uq.doUpdate(FoodItem);
            
            // Redirecting to the ReadServlet to display the updated list of FoodItems
            String url = "/read";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Handling number format exception (e.g., if FoodItemID is not a valid integer)
            // This can include logging the error or redirecting to an error handling page
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description"; // Description of this servlet
    }

} // Closing brace for the UpdateServlet class
