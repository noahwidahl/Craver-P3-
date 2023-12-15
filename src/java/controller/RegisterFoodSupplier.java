/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodSupplier;

/**
 *
 * @author Bokaj
 */
@WebServlet(name = "RegisterFoodSupplier", urlPatterns = {"/registerFoodSupplier"})
public class RegisterFoodSupplier extends HttpServlet {

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
            out.println("<title>Servlet registerFoodSupplier</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet registerFoodSupplier at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Retrieve form parameters
        String FoodSupplierUsername = request.getParameter("FoodSupplierUsername");
        String FoodsupplierPassword = request.getParameter("FoodsupplierPassword");
        String FoodsupplierEmail = request.getParameter("FoodsupplierEmail");
        String FoodsupplierName = request.getParameter("FoodsupplierName");
        String FoodsupplierAddress = request.getParameter("FoodsupplierAddress");
        String FoodsupplierPostNr = request.getParameter("FoodsupplierPostNr");
        String FoodsupplierCity = request.getParameter("FoodsupplierCity");
        String FoodsupplierPhoneNumber = request.getParameter("FoodsupplierPhoneNumber");
        String FoodsupplierExternalLink = request.getParameter("FoodsupplierExternalLink");
        String FoodSupplierCategoryID = request.getParameter("FoodSupplierCategoryID");
    
        // Check if any of the required fields is empty
        if ( FoodSupplierUsername.isEmpty() || FoodsupplierPassword.isEmpty() || FoodsupplierEmail.isEmpty() || FoodsupplierName.isEmpty() || FoodsupplierAddress.isEmpty() || FoodsupplierPostNr.isEmpty() || FoodsupplierCity.isEmpty() || FoodsupplierPhoneNumber.isEmpty() || FoodsupplierExternalLink.isEmpty() || FoodSupplierCategoryID.isEmpty()) {
            // If any required field is empty, redirect back to the registration page with an error message
            response.sendRedirect("registerFoodSupplier.jsp?error=1");
            return;
        }
        // Call method in registerquery to insert data into the database
        boolean nextStep = FoodSupplier.registerFoodSupplier(FoodSupplierUsername,FoodsupplierPassword,FoodsupplierEmail,FoodsupplierName,FoodsupplierAddress,FoodsupplierPostNr,FoodsupplierCity,FoodsupplierPhoneNumber,FoodsupplierExternalLink,FoodSupplierCategoryID);
        //If statement to control what happens based on succes or not
        if(nextStep==true){
            //Logic for success redirecting the user accordingly
            response.sendRedirect("login.jsp");
        }else{
            //Logic for failure, is handled by the .jsp file in this context
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
