package com.calorizer.servlets;

import com.calorizer.db.ProductCategoryDAO;
import com.calorizer.db.ProductDAO;
import com.calorizer.items.Product;
import com.calorizer.items.ProductCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addProduct"} )
public class CreateProductServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String categoryTitle = request.getParameter("addProductCategoryInput");
        System.out.println("CreateProductServlet doPost addProductCategoryInput: " + categoryTitle);

        String productTitle = request.getParameter("addProductInput");
        double proteins = Double.parseDouble(request.getParameter("addProteinsInput"));
        double fats = Double.parseDouble(request.getParameter("addFatsInput"));
        double carbohydrates = Double.parseDouble(request.getParameter("addCarbohydratesInput"));
        double calories = Double.parseDouble(request.getParameter("addCaloriesInput"));

        /*ProductCategory category = new ProductCategoryDAO().getByTitle(categoryTitle);
        if (category == null) {
            //create new category
            category = new ProductCategoryDAO().createCategory(categoryTitle);
        }*/
        ProductCategory category = new ProductCategoryDAO().createCategory(categoryTitle);
        Product product = new Product(productTitle, proteins, fats, carbohydrates, calories, category.getId());
        new ProductDAO().create(product);

        request.setAttribute("resultMessage", "Новый продукт '" + product.getTitle() + "' добавлен в каталог" );
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
