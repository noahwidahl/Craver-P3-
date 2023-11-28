/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author Bokaj
 */
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dbHelpers.ReadQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/TableUpdateServlet")
public class TableUpdateServlet extends HttpServlet {
    private ResultSet results;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Read</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Read at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Update the value in the database or in-memory storage
        
        try {
            //Create a ReadQuery helper object
            ReadQuery readQuery = new ReadQuery();
            //Get the HTML table from the ReadQuery object
            
            // Get the new value from the form
            String newValue = request.getParameter("newValueName");
            String query = "";
            
            if (newValue == null || newValue == "") {
                query = "SELECT * FROM craveconnect.Foodsupplier;";
            } else {
                query = "SELECT * FROM craveconnect.FoodItem where FoodsupplierID = "+newValue+";"; // Code to be executed if the boolean expression is false
            }
            
            results = readQuery.ReadTableData(query);
            
            String table = readQuery.outputResultAsHtmlTable(results);
            
            //Pass execution control to read.jsp along with the table.
            request.setAttribute("table", table);
            String url ="/read.jsp";
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        
        
        processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Redirect back to the JSP page
        response.sendRedirect("read.jsp");
    }
}

