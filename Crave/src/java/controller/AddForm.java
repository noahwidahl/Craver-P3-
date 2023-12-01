package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddForm", urlPatterns = {"/add"})
public class AddForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String foodSupplierID = request.getParameter("supplierId");

        if (foodSupplierID != null && !foodSupplierID.trim().isEmpty()) {
            request.setAttribute("FoodSupplierID", foodSupplierID);
            String url = "/add.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("foodSupplier.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "AddForm servlet handles forwarding to the add item form.";
    }
}
