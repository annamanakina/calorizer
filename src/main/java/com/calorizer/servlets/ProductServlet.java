package com.calorizer.servlets;

import com.calorizer.calculation.Note;
import com.calorizer.db.NoteDAO;
import com.calorizer.db.ProductDAO;
import com.calorizer.items.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(urlPatterns = {"/product"} )
public class ProductServlet extends HttpServlet {

    List<Note> notes;
    ArrayList<Product> selectedProducts;

    @Override
    public void init() throws ServletException {
        System.out.println("ProductServlet init");
        notes = new ArrayList();
        selectedProducts = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ProductServlet doGet");
       /* String selectedDate = request.getParameter("selectedDate");

        notes.addAll(new NoteDAO().getByDate(selectedDate));

        double proteinsSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getProteins()).sum();

        double fatsSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getFats()).sum();
        double carbohydratesSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getCarbohydrates()).sum();
        double caloriesSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getCalories()).sum();
        double weightSum = notes.stream().mapToDouble(
                (item) -> item.getWeight()).sum();

        request.setAttribute("list", notes);
        request.setAttribute("productWeight", weightSum);
        request.setAttribute("size", selectedProducts.size());//notes.size?
        request.setAttribute("proteinsSum", proteinsSum);
        request.setAttribute("fatsSum", fatsSum);
        request.setAttribute("carbohydratesSum", carbohydratesSum);
        request.setAttribute("caloriesSum", caloriesSum);
        request.setAttribute("selectedDate", selectedDate);

        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //System.out.println("ProductServlet doPost userInput: " + request.getParameter("productUserInput"));
        //todo делать пересчет каллорийности согласно весу продукта
        System.out.println("ProductServlet doPost weightInput: " + request.getParameter("weightUserInput"));
        String productWeight = request.getParameter("weightUserInput");
        String productTitle = request.getParameter("productUserInput");
        String selectedDate = request.getParameter("selectedDate");



        //System.out.println("ProductServlet doPost now date: "+ request.getParameter("todayDate"));
         //тут получаем title
        Product product = new ProductDAO().getByTitle(productTitle);
        selectedProducts.add(product);

        /*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(new java.util.Date());*/

        System.out.println("ProductServlet currentDate: " +selectedDate);

        Note note = new Note(product, Integer.parseInt(productWeight), selectedDate);
        if (new NoteDAO().create(note)) {
            notes.addAll(new NoteDAO().getByDate(selectedDate));
        }

       //нужно из notes получить все показатели по калорийности с учетом веса

        double proteinsSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getProteins()).sum();

        double fatsSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getFats()).sum();
        double carbohydratesSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getCarbohydrates()).sum();
        double caloriesSum = selectedProducts.stream().mapToDouble(
                (item) -> item.getCalories()).sum();
        double weightSum = notes.stream().mapToDouble(
                (item) -> item.getWeight()).sum();

//TODO NOTE OBJECT?
        System.out.println("ProductServlet doPost " + product);
        /*request.setAttribute("list", selectedProducts);
        request.setAttribute("productWeight", productWeight);
        request.setAttribute("size", selectedProducts.size());
        request.setAttribute("proteinsSum", proteinsSum);
        request.setAttribute("fatsSum", fatsSum);
        request.setAttribute("carbohydratesSum", carbohydratesSum);
        request.setAttribute("caloriesSum", caloriesSum);*/

        request.setAttribute("list", notes);
        request.setAttribute("productWeight", weightSum);
        request.setAttribute("size", selectedProducts.size());//notes.size?
        request.setAttribute("proteinsSum", proteinsSum);
        request.setAttribute("fatsSum", fatsSum);
        request.setAttribute("carbohydratesSum", carbohydratesSum);
        request.setAttribute("caloriesSum", caloriesSum);
        request.setAttribute("selectedDate", selectedDate);

        //отобразить тут таблицу
        //request.setAttribute("MyProfile", );
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }


}
