package controller;

import dbHelpers.FoodSupplierReadQuery;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FoodSuppliersServlet", urlPatterns = {"/foodsuppliersservlet"})
public class FoodSuppliersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FoodSupplierReadQuery rq = new FoodSupplierReadQuery();
        rq.doRead();
        String table = rq.getHTMLTable();
        request.setAttribute("table", table);
        String url = "/foodSupplier.jsp"; // Make sure this matches the JSP file name
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
