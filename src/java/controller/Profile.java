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
import java.sql.ResultSet;
import model.Rating;
import model.RegisteredUser;

/**
 *
 * @author Bokaj
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {
    
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
            out.println("<title>Servlet Profile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getContextPath() + "</h1>");
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
        //Retrieve the session User Object, assigned at login
        RegisteredUser userLoggedIn = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");
        //Creating a table showcasing the users own ratings and turning them into a table    
        String table = userLoggedIn.getOwnRatings();
        request.setAttribute("table", table);
        //Forwarding the table result to the .jsp file
        String url ="/profile.jsp";
        // Forward the request to the profile.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
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
        //Reading the hidden first column value in a table parsed as a hashtable value
        String parameterValue = request.getParameter("parameterName");
          
        if(parameterValue == null || "".equals(parameterValue)){
            //what to do when is doesnt have data.
        }else if(parameterValue.contains("BtnDelete")){
            //splitting the hash table
            String[] parts = parameterValue.split(":");
            //creating rating obj and running the method to delete rating.
            Rating deleteRating = new Rating(Integer.parseInt(parts[1].trim()));
            deleteRating.deleteRating();
                    
            String url ="/profile.jsp";
            // Forward the request to the profile.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response); 
        }else{
            System.out.println(parameterValue); 
        }
        //Handling the action when the BtnDeleteUseraction is pressed.
        parameterValue = request.getParameter("BtnDeleteUseraction");
        if(parameterValue == null || "".equals(parameterValue)){
                //what to do when is doesnt have data.
        }else if(parameterValue.contains("DeleteUser")){
            //Getting the current user obj
            RegisteredUser deleteUser = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");
            //Deleting the user obj
            deleteUser.deleteOwnUser();
            //Cleanup of seesion variable
            request.getSession().setAttribute("sessionUserObject", null);
            //Redirecting to login page
            response.sendRedirect("login.jsp");  
        }
                
        //Retrieve the session User Object, assigned at login
        RegisteredUser userLoggedIn = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");
        //handling what happens when the BtnUpdateaction button is pressed
        String action = request.getParameter("BtnUpdateaction");
        
        if ("updateInfo".equals(action)) {
            // Getting the parameters from the input fields in profile.jsp
            String address = request.getParameter("Addresse");
            String postNr = request.getParameter("PostNr");
            String postBy = request.getParameter("PostBy");

            // Extract the first four letters of postNr, PostNr is max 4 chars in DB table
            String truncatedPostNr = postNr.substring(0, Math.min(postNr.length(), 4));
            
            //Making an user object to update
            RegisteredUser updateUser = new RegisteredUser(userLoggedIn.getUserName());
            //Running the update method
            boolean control = updateUser.updateOwnUser(address, truncatedPostNr, postBy,userLoggedIn.getUserID());
            //Check if the update action happend
            if (control) {
            // if true - Update the session object with the new information
            userLoggedIn.setAddress(address);
            userLoggedIn.setPostNr(postNr);
            userLoggedIn.setPostBy(postBy);
            request.getSession().setAttribute("sessionUserObject", userLoggedIn);
            System.out.println("User information updated successfully.");
            } else {
                System.out.println("Failed to update user information.");
                // Handle failure scenario, e.g., display an error message
            }
          String url ="/profile.jsp";
        // Forward the request to the profile.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
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
    }// </editor-fold>

}
