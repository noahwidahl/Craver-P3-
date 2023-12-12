/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dbHelpers.ReadQuery;
import dbHelpers.UpdateQuery;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;

/**
 *
 * @author Bokaj
 */
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
        
        //Handling onload    
        try {
            //Create a ReadQuery helper object
            ReadQuery readQuery = new ReadQuery();
            //Get the HTML table from the ReadQuery object
            
            // Get the new value from the form
            String query = "select * from craveconnect.v_FoodsuppliersPending;";
            results = readQuery.readTableData(query);
            
            //Using a HashMap to dyniamicly make buttons, key = button name, value = button text
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("BtnDeny", "Deny");
            hashMap.put("BtnApprove", "Approve");
            //hashMap.put("test", "test");
            
            
            String table = readQuery.outputResultAsHtmlTableWithButtons(results,hashMap,"admin");
            
            //Pass execution control to read.jsp along with the table.
            request.setAttribute("table", table);
            String url ="/admin.jsp";
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Reading the hidden first column value in a table
        String parameterValue = request.getParameter("parameterName");
        try {    
                UpdateQuery updateInstance = new UpdateQuery();
                if(parameterValue == null || "".equals(parameterValue)){
                   
                }else if(parameterValue.contains("BtnDeny")){
                    //what to do
                    String[] parts = parameterValue.split(":");
                    //System.out.println("testing here: "+parts[1].trim());  
                    String query = "Update craveconnect.Foodsupplier set StateID = 2 where FoodsupplierID = "+parts[1].trim()+";";
                    System.out.println(query); 
                    updateInstance.executeInsertUpdate(query);
                }else if(parameterValue.contains("BtnApprove")){
                    //what to do
                    String[] parts = parameterValue.split(":");
                    String query = "Update craveconnect.Foodsupplier set StateID = 1 where FoodsupplierID = "+parts[1].trim()+";";
                    System.out.println(query); 
                    updateInstance.executeInsertUpdate(query);
                }else{
                    System.out.println(parameterValue); 
                }
                
                String url ="/admin.jsp";

                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        


        
        
        
        //processRequest(request, response);
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
