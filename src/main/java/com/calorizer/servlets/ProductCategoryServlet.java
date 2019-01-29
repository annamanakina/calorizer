package com.calorizer.servlets;

import com.calorizer.db.ProductDAO;
import com.calorizer.items.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/productList"} )
public class ProductCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = new ProductDAO().getByProductCategoryId(Integer.parseInt(request.getParameter("categoryItemId")));
        request.setAttribute("productList", list);
        request.getRequestDispatcher("/pages/productList.jsp").forward(request, response);
    }


}
