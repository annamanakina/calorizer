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
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            processRequest(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        //String testDate = request.getParameter("testDate");
        //System.out.println("ProductServlet doPost testDate: " + testDate);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String productTitle = request.getParameter("productUserInput");
        String productWeight = request.getParameter("weightUserInput");
        String selectedDate = request.getParameter("selectedDate");
        Product product = new ProductDAO().getByTitle(productTitle);

        List<Note> notes = new ArrayList<>();

        if (productTitle != null && productWeight != null) {
            Note note = new Note(product, Integer.parseInt(productWeight), selectedDate);
            new NoteDAO().create(note);
        }

        notes.addAll(new NoteDAO().getByDate(selectedDate));

        double proteinsSum = notes.stream().mapToDouble(
                Note::getProteinsPerWeight).sum();
        double fatsSum = notes.stream().mapToDouble(
                Note::getFatsPerWeight).sum();
        double carbohydratesSum = notes.stream().mapToDouble(
                Note::getCarbohydratesPerWeight).sum();
        double caloriesSum = notes.stream().mapToDouble(
                Note::getCaloriesPerWeight).sum();
        double weightSum = notes.stream().mapToDouble(
                Note::getWeight).sum();

        request.setAttribute("list", notes);
        request.setAttribute("productWeight", weightSum);
        request.setAttribute("size", notes.size());
        request.setAttribute("proteinsSum", proteinsSum);
        request.setAttribute("fatsSum", fatsSum);
        request.setAttribute("carbohydratesSum", carbohydratesSum);
        request.setAttribute("caloriesSum", caloriesSum);
        request.setAttribute("selectedDate", selectedDate);

        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
