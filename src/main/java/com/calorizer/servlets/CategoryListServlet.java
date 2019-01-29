package com.calorizer.servlets;

import com.calorizer.db.ProductCategoryDAO;
import com.calorizer.items.ProductCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = {"/category"} )
public class CategoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductCategory> list = new ProductCategoryDAO().getAll();
        request.setAttribute("categoryList", list);
        request.getRequestDispatcher("/pages/productCategory.jsp").forward(request, response);
    }
}
