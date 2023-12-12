/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dbHelpers.ReadQuery;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;
import model.Rating;
import model.RegisteredUser;

/**
 *
 * @author Bokaj
 */
@WebServlet(name = "FoodItemServlet", urlPatterns = {"/foodItem"})
public class FoodItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            //System.out.println("doGet: FoodItemServlet 1"); 
            int FoodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            
            FoodItem specificFoodItemServlet = new FoodItem(FoodItemID);
            request.getSession().setAttribute("sessionFoodItemID", specificFoodItemServlet);
            String url = "/foodItem.jsp"; // JSP page to display Food Items
            //System.out.println("Testing: FoodItemServlet 2"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException ex) {
            Logger.getLogger(FoodItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

                System.out.println("doPost: FoodItemServlet 2"); 
                int submittedRating = Integer.parseInt(request.getParameter("rating"));
                String submittedComment = request.getParameter("multiLineInputCommentSection");
                // Retrieve the specificFoodItemServlet from the session
                FoodItem specificFoodItemRetrieved = (FoodItem) request.getSession().getAttribute("sessionFoodItemID");
                RegisteredUser userLoggedIn = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");
                
                //newRating is based on the rating from the obj
                Rating.createRating(userLoggedIn.getUserID(), submittedRating, submittedComment, specificFoodItemRetrieved.getFoodItemID());
                String url ="/foodItem.jsp";

                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                dispatcher.forward(request, response);

        }

}
/*
RegisteredUser updateUser = new RegisteredUser(userLoggedIn.getUserName());
            boolean control = updateUser.UpdateOwnUser(address, truncatedPostNr, postBy);
            System.out.println("control: " + control);
            if (control) {
            // Update the session object with the new information
            userLoggedIn.setAddress(address);
            userLoggedIn.setPostNr(postNr);
            userLoggedIn.setPostBy(postBy);
            request.getSession().setAttribute("sessionUserObject", userLoggedIn);
*/