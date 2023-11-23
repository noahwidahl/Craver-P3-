/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

/**
 *
 * @author sorennygaardjensen
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateFoodItem"})
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
            // Get the form data and set up a FoodItem object
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            String name = request.getParameter("FoodItemName");
            BigDecimal price = new BigDecimal(request.getParameter("Price"));
            
            FoodItem FoodItem = new FoodItem(); 
            FoodItem.setFoodItemID(foodItemID);
            FoodItem.setFoodItemName(name);
            FoodItem.setPrice(price);
            
            // Create an UpdateQuery object and use it to update the FoodItem
            UpdateQuery uq = new UpdateQuery();
            uq.doUpdate(FoodItem);
            
            // Pass control to the ReadServlet
            String url = "/read";
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            // Handle the exception (e.g., show an error message or log it)
            // Possibly redirect to an error page or log the error
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

} // This is the closing brace for the UpdateServlet class