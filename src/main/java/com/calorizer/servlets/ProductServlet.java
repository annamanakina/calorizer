package com.calorizer.servlets;

import com.calorizer.db.PersonDAO;
import com.calorizer.items.Note;
import com.calorizer.db.NoteDAO;
import com.calorizer.db.ProductDAO;
import com.calorizer.items.Person;
import com.calorizer.items.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ProductServlet session: " + request.getSession().getId() + "thread: "+ Thread.currentThread().getName());

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

        //TODO pass person id
        Person person = new PersonDAO().getById(5);
        double dailyCalories = person.getMetabolicRate().getDailyCaloriesIntakes();

        double excessOfDailyCalorie = 0.0;
        if (dailyCalories < caloriesSum){
            excessOfDailyCalorie = (caloriesSum - dailyCalories) / dailyCalories*100;
        }

        request.setAttribute("dailyCalories", dailyCalories);
        request.setAttribute("excessOfDailyCalorie", excessOfDailyCalorie);

        request.setAttribute("list", notes);
        request.setAttribute("productWeight", weightSum);
        request.setAttribute("size", notes.size());

        BigDecimal ps = new BigDecimal(proteinsSum).setScale(1, RoundingMode.HALF_UP);
        request.setAttribute("proteinsSum", ps);
        System.out.println("proteinsSum: " + proteinsSum);
        System.out.println("proteinsSum ps: " + ps);

        request.setAttribute("fatsSum", fatsSum);
        System.out.println("fatsSum: " + fatsSum);

        request.setAttribute("carbohydratesSum", carbohydratesSum);
        System.out.println("carbohydratesSum: "+ carbohydratesSum);
        request.setAttribute("caloriesSum", caloriesSum);
        System.out.println("caloriesSum: "+ caloriesSum);

        request.setAttribute("selectedDate", selectedDate);

        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
