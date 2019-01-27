package com.calorizer.servlets;

import com.calorizer.calculation.ActivityMultiplier;
import com.calorizer.db.PersonDAO;
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


@WebServlet(urlPatterns = {"/usersettings"})
public class UserSettingsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init UserSettingsServlet ");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet UserSettingsServlet ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        System.out.println("UserSettingsServlet doPost gender: " + request.getParameter("gender")
                + ",  age: " + request.getParameter("age")
                + ", height: " + request.getParameter("height")
                + ", weight: " + request.getParameter("weight")
                + ", lifestyle: " + request.getParameter("lifestyle"));

        String gender = request.getParameter("gender");
        Sex sex = Sex.getValueBy(gender);

        int age = Integer.parseInt(request.getParameter("age"));
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        String lifestyle = request.getParameter("lifestyle");

        System.out.println("lifestyle: " + lifestyle);

        Person person = new Person(sex, age, weight, height);
        double bmi = MetabolicIndexFactory.calculate(BMI, person);
        double bmr = MetabolicIndexFactory.calculate(BMR, person);

        MetabolicRate mr = new MetabolicRate(ActivityMultiplier.getElementBy(lifestyle), bmi, bmr);
        person.setMetabolicRate(mr);
        //System.out.println("person: "+person.getMetabolicRate() );

        double dci = MetabolicIndexFactory.calculate(DAILY_CALORIE_INTAKE, person);
        mr.setDailyCaloriesIntakes(dci);
        person.notifyObservers();
        //todo add if (user is logged in)

        System.out.println("create person: " + new PersonDAO().create(person));
        request.setAttribute("mbi", bmi);
        request.setAttribute("daily_intake", dci);
        request.setAttribute("bmr", bmr);
        request.getRequestDispatcher("/pages/main.jsp").forward(request, response);
    }
}
