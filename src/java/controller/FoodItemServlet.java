/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
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
    //Handling the onload page stuff.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            //Getting the paramter of a specific foodItem in the table in foodItems.jsp 
            int FoodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            //Creating an instance of the FoodItem object
            FoodItem specificFoodItemServlet = new FoodItem(FoodItemID);
            //Setting a session variable holding the fooditem object
            request.getSession().setAttribute("sessionFoodItemID", specificFoodItemServlet);
            //Forwarding the result to the foodItem.jsp page to display the specific Food item
            String url = "/foodItem.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException ex) {
            Logger.getLogger(FoodItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    //Handling the onclick page stuff.
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Getting the hidden paramter to handle the Submit rating buttons action 
        int submittedRating = Integer.parseInt(request.getParameter("rating"));
        //Getting the Comment information
        String submittedComment = request.getParameter("multiLineInputCommentSection");
        //Retrieve the specificFoodItemServlet from the session
        FoodItem specificFoodItemRetrieved = (FoodItem) request.getSession().getAttribute("sessionFoodItemID");
        //Retrieve the current logged in users information
        RegisteredUser userLoggedIn = (RegisteredUser) request.getSession().getAttribute("sessionUserObject");
                
        //newRating is based on the rating from the obj
        Rating.createRating(userLoggedIn.getUserID(), submittedRating, submittedComment, specificFoodItemRetrieved.getFoodItemID());
        //Forwarding to the jsp page.
        String url ="/foodItem.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
        }

}
