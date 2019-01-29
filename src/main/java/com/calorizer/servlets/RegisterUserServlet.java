package com.calorizer.servlets;


import com.calorizer.db.PersonDAO;
import com.calorizer.db.UserAccountDAO;
import com.calorizer.items.Person;
import com.calorizer.items.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/register"})
public class RegisterUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/registration/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        UserAccount user;
       // boolean hasError = false;
        String errorString = null;

        if (firstName == null || lastName == null || userName == null || password == null || confirmPassword == null ||
                firstName.length() == 0 || lastName.length() == 0 || userName.length() == 0 || password.length() == 0 || confirmPassword.length() == 0) {
         //   hasError = true;
            errorString = "Все поля необходимы для заполнения!";
            request.setAttribute("errorString", errorString);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/registration/register.jsp");
            dispatcher.forward(request, response);
        } else {
            Person person = new Person(firstName, lastName);
            System.out.println("person : " + person);
            String id = new PersonDAO().createPerson(person);
            System.out.println("id : " + id);
            user = new UserAccount(userName, password, String.valueOf(id));
            new UserAccountDAO().create(user);

            request.setAttribute("user", user);
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/profilepage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
