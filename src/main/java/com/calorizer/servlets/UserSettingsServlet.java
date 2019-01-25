package com.calorizer.servlets;

import com.calorizer.calculation.ActivityMultiplier;
import com.calorizer.factory.MetabolicIndexFactory;
import com.calorizer.items.MetabolicRate;
import com.calorizer.items.Person;
import com.calorizer.items.Sex;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.calorizer.factory.MetabolicIndexFactory.*;


@WebServlet(urlPatterns = {"/usersettings"} )
public class UserSettingsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

       /* System.out.println("UserSettingsServlet doPost gender: " + request.getParameter("gender")
                + ",  age: " + request.getParameter("age")
                + ", height: " + request.getParameter("height")
                + ", weight: " + request.getParameter("weight")
                + ", lifestyle: " + request.getParameter("lifestyle"));*/

        String gender = request.getParameter("gender");
        Sex sex = Sex.getValueBy(gender);

        int age = Integer.parseInt(request.getParameter("age"));
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        String lifestyle = request.getParameter("lifestyle");

        System.out.println("lifestyle: "+lifestyle);

        Person person = new Person(sex, age, weight, height);

        double bmi = MetabolicIndexFactory.calculate(BMI, person);
        double bmr = MetabolicIndexFactory.calculate(BMR, person);

        //System.out.println("mbi: "+bmi + ", bmr: " +bmr);
        MetabolicRate mr = new MetabolicRate(ActivityMultiplier.getElementBy(lifestyle), bmi, bmr);

        person.setMetabolicRate(mr);
        System.out.println("person: "+person.getMetabolicRate() );

        double dci = MetabolicIndexFactory.calculate(DAILY_CALORIE_INTAKE, person);
        mr.setDailyCaloriesIntakes(dci);
        System.out.println("person: "+person.getMetabolicRate() );
       request.setAttribute("mbi", bmi);
       request.setAttribute("daily_intake", dci);
       request.setAttribute("bmr", bmr);
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
