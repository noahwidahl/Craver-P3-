package controller;

import dbHelpers.ReadQuery;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ReadServlet", urlPatterns = {"/read"})
public class ReadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String supplierIdParam = request.getParameter("supplierId");
        System.out.println("Supplier ID in ReadServlet: " + supplierIdParam); // Debug line

        String table;
        String url;

        if (supplierIdParam != null && !supplierIdParam.isEmpty()) {
            int supplierId = Integer.parseInt(supplierIdParam);
            ReadQuery rq = new ReadQuery(supplierId);
            rq.doRead();
            table = rq.getHTMLTable();
            url = "foodItemList.jsp"; // JSP file for displaying food items of a supplier
        } else {
            ReadQuery rq = new ReadQuery();
            rq.doRead();
            table = rq.getHTMLTable();
            url = "/read.jsp"; // JSP file for displaying all food items
        }

        request.setAttribute("table", table);
        request.setAttribute("supplierId", supplierIdParam); // Forward supplierId to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
