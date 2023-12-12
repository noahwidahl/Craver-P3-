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
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RegisteredUser;
import model.GuestUser;

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
   
}*/

    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the username and password from the request parameters
        String username = request.getParameter("uname");
        String password = request.getParameter("pword");
        String loginType = request.getParameter("loginType");
        
        //System.out.println(username);
        //System.out.println(password);
        System.out.println("Login type: " + loginType);

        if("user".equals(loginType)){
            System.out.println("step 1");
            RegisteredUser userLogginIn = new RegisteredUser(username);

            // Perform the Login check using your RegisteredUser class and checkLogin method
            boolean isValidUser = userLogginIn.checkLogin(username, password);

            if (isValidUser) {
                
                // If Login is successful, you might want to set user information in the session
                request.getSession().setAttribute("sessionUserObject", userLogginIn);
                //System.out.println("her: "+request.getSession().getAttribute("userID"));

                //Updating LastSeen column in DB
                userLogginIn.setLastLogin();
                //System.out.println("UserID is: "+userLogginIn.getUserID());
                // Redirect to a dashboard or home page
                response.sendRedirect("home.jsp");
            }  else {
                System.out.println("step 3");
                // If Login fails, display message
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                request.setAttribute("error", "Invalid username or password");
                dispatcher.forward(request, response);
            } 
            
        }else if("guest".equals(loginType)){
                System.out.println("step 2");
                // If Login is successful, you might want to set user information in the session
                GuestUser guestUserLogginIn = new GuestUser("Guest");
                System.out.println("UserID is: "+guestUserLogginIn.getUserID());
                request.getSession().setAttribute("sessionUserObject", guestUserLogginIn);
                
                //request.getSession().setAttribute("sessionUserName", "Guest");
                //request.getSession().setAttribute("sessionUserID", 0);
                //request.getSession().setAttribute("sessionUserRole", 3);
                //request.getSession().setAttribute("sessionUserRoleDescription", "Guest");

                GuestUser guest = new GuestUser("guest");
                try {
                    guest.setGuestLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

                response.sendRedirect("home.jsp");
            } 
        System.out.println("step 4");
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