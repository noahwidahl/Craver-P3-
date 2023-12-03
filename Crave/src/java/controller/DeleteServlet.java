/*
 * Servlet class to handle delete requests for FoodItem records.
 */
package controller;

import dbHelpers.DeleteQuery;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Annotation to define servlet name and URL pattern
@WebServlet(name = "DeleteServlet", urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet DeleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteServlet at " + request.getContextPath() + "</h1>");
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
     * Handles the HTTP POST method to delete a FoodItem.
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
            // Parsing FoodItemID and FoodSupplierID from the request
            int FoodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            int FoodSupplierID = Integer.parseInt(request.getParameter("FoodSupplierID")); // Get the supplier ID

            // Creating an instance of DeleteQuery to handle the deletion
            DeleteQuery dq = new DeleteQuery();
            dq.DoDelete(FoodItemID); // Executing the delete operation

            // Redirecting to the read servlet with the correct supplier ID after deletion
            response.sendRedirect("read?supplierId=" + FoodSupplierID);
        } catch (NumberFormatException e) {
            // Handling NumberFormatException and sending a bad request error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid FoodItem ID or FoodSupplierID");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for handling deletion of FoodItem records"; // Description of this servlet
    }
    // </editor-fold>
}
