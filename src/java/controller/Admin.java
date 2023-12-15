/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.HashMap;
import model.AdministratorUser;
import model.RegisteredUser;

/**
 *
 * @author Bokaj
 */

// Name of Servlet and URL to be called from the webpage
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class Admin extends HttpServlet {
    private ResultSet results;
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
            out.println("<title>Servlet admin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin at " + request.getContextPath() + "</h1>");
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
        //Using a HashMap to dyniamicly make buttons, key = button name, value = button text
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BtnDeny", "Deny");
        hashMap.put("BtnApprove", "Approve");
        //hashMap.put("test", "test");
            
        //Calling the static method showFoodsuppliersToBeValidated
        String table = AdministratorUser.showFoodsuppliersToBeValidated(hashMap);
            
        //Passing table to webpage
        request.setAttribute("table", table);
        String url ="/admin.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        processRequest(request, response);

        //Reading the hidden first column value in a table, the parameter "parameterName" exists here ReadQuery.outputResultAsHtmlTableWithButtons
        String parameterValue = request.getParameter("parameterName");
        
        //Reading the session attribute of the RegisteredUser object, this enable the user information all around the system.
        RegisteredUser userLoggedIn = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");    
        //Creating an insteace of the Administrator object, input is RegisteredUser objects UserName 
        AdministratorUser admin = new AdministratorUser(userLoggedIn.getUserName());
            
        //If-statement to handle which action to take, based on which button was pressed.     
        if(parameterValue == null || "".equals(parameterValue)){
            //Nothing here
        }else if(parameterValue.contains("BtnDeny")){
            //Handling the BtnDeny button action
            //Splitting the parameterValue (which is a hashMap) into 2 component, a key and a value
            String[] parts = parameterValue.split(":");
            admin.validateFoodSupplier(1, Integer.parseInt(parts[1].trim()));
        }else if(parameterValue.contains("BtnApprove")){
             //Handling the BtnApporve button action
            //Splitting the parameterValue (which is a hashMap) into 2 component, a key and a value
            String[] parts = parameterValue.split(":");
            admin.validateFoodSupplier(2, Integer.parseInt(parts[1].trim()));
        }else{
            //what happens if another value is passed...
            //System.out.println(parameterValue); 
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
