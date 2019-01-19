package com.calorizer.servlets;

import com.calorizer.calculation.Formulas;
import com.calorizer.items.Person;
import com.calorizer.items.Sex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/usersettings"} )
public class UserSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //получать это по name
        System.out.println("UserSettingsServlet doPost gender: " + request.getParameter("gender")
                + ",  age: " + request.getParameter("age")
                + ", height: " + request.getParameter("height")
                + ", weight: " + request.getParameter("weight")
                + ", lifestyle: " + request.getParameter("lifestyle"));

        String gender = request.getParameter("gender");
        Sex sex;
        if (gender.equals(Sex.FEMALE.name())) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }

        String age = request.getParameter("age");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String lifestyle = request.getParameter("lifestyle");

        Person person = new Person(1, sex, Integer.parseInt(age),
                Integer.parseInt(weight), Integer.parseInt(height));

       double mbi = Formulas.calculateBodyMassIndex(person);
       double mbr = Formulas.calculateBMRHarrisBenedictEquation(person);
       request.setAttribute("mbi", mbi);
       //request.setAttribute("daily_intake", weightSum);
       request.setAttribute("bmr", mbr);


        //отобразить тут таблицу
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
