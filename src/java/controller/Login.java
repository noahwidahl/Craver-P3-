/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RegisteredUser;

/**
 *
 * @author timmadsen
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
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
    /*@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Retrieve the username and password from the request parameters
    String username = request.getParameter("uname");
    String password = request.getParameter("pword");

    // Perform the Login check using your LoginQuery class
    UserBean loggedInUser = loginquery.checkLogin(username, password);

    if (loggedInUser != null) {
        // If Login is successful, set user information in the session
        request.getSession().setAttribute("loggedInUser", loggedInUser);

        // Redirect based on the role or to a general page
        if (loggedInUser.getRole() == 3) {
            // Admin user, redirect to admin page
            response.sendRedirect("adminhomepage.jsp");
        } else {
            // Regular user, redirect to a dashboard or home page
            response.sendRedirect("index.jsp");
        }
    } else {
        // If Login fails, display message
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        request.setAttribute("error", "Invalid username or password");
        dispatcher.forward(request, response);
    }
}*/

    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username and password from the request parameters
        String username = request.getParameter("uname");
        String password = request.getParameter("pword");
        
        System.out.println(username);
        System.out.println(password);
        
        // Perform the Login check using your RegisteredUser class and checkLogin method
        boolean isValidUser = RegisteredUser.checkLogin(username, password);

        if (isValidUser) {
            // If Login is successful, you might want to set user information in the session
            request.getSession().setAttribute("username", username);

            // Redirect to a dashboard or home page
            response.sendRedirect("home.jsp");
        } else {
            // If Login fails, display message
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("error", "Invalid username or password");
            dispatcher.forward(request, response);
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
    } 
// </editor-fold>
    
}

/*
UserBean loggedInUser = loginquery.checkLogin(username, password);
*/