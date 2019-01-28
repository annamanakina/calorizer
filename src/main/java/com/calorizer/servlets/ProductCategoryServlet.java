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


@WebServlet(urlPatterns = {"/category"} )
public class ProductCategoryServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ProductCategoryServlet init");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("categoryItemId"));

        System.out.println("-----------------");
        List<Product> list = new ProductDAO().getByProductCategoryId(Integer.parseInt(request.getParameter("categoryItemId")));
        list.forEach(System.out::println);
        System.out.println("-----------------");


        //List<Product> productList = ProductFactory.createProduct(Integer.parseInt(request.getParameter("categoryItemId"))).createProducts();
        request.setAttribute("productList", list);
        request.getRequestDispatcher("/pages/category.jsp").forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ProductCategoryServlet doPost");
        System.out.println(req.getParameter("postRequestCategory"));
    }

}
