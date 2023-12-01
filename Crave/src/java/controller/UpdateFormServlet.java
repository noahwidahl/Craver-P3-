package controller;

import dbHelpers.ReadRecord;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FoodItem;

@WebServlet(name = "UpdateFormServlet", urlPatterns = {"/update"})
public class UpdateFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int foodItemID = Integer.parseInt(request.getParameter("FoodItemID"));
            ReadRecord rr = new ReadRecord(foodItemID);
            rr.doRead();
            FoodItem foodItem = rr.getFooditem();

            request.setAttribute("FoodItem", foodItem);
            String url = "/updateForm.jsp"; // Make sure this is the correct path to your update form JSP

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid FoodItem ID");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An internal server error occurred");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for displaying the update form for FoodItem records";
    }
}
