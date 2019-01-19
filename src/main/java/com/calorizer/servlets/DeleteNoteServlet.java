package com.calorizer.servlets;

import com.calorizer.db.NoteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deletenote"} )
public class DeleteNoteServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("DeleteNoteServlet init");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deletedIndex = request.getParameter("delete_button_id2");
        System.out.println("DeleteNoteServlet delete_item_id: " + deletedIndex);
        new NoteDAO().delete(Integer.parseInt(deletedIndex));

        //обновить список пунктов?

        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
