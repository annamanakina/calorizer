package com.calorizer.servlets.note;

import com.calorizer.items.Note;
import com.calorizer.db.NoteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/getNotesByDate"} )
public class GetNotesServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String selectedDate = request.getParameter("selectedDate");

        List<Note> notes = new NoteDAO().getByDate(selectedDate);
        notes.stream().forEach(System.out::println);

        double proteinsSum = notes.stream().mapToDouble(
                (item) -> item.getProteinsPerWeight()).sum();

        double fatsSum = notes.stream().mapToDouble(
                (item) -> item.getFatsPerWeight()).sum();
        double carbohydratesSum = notes.stream().mapToDouble(
                (item) -> item.getCarbohydratesPerWeight()).sum();
        double caloriesSum = notes.stream().mapToDouble(
                (item) -> item.getCaloriesPerWeight()).sum();
        double weightSum = notes.stream().mapToDouble(
                (item) -> item.getWeight()).sum();

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
