package com.calorizer.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;


@WebServlet("/profile")
public class AuthorizationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        System.out.println("encoding: "+request.getCharacterEncoding());

        System.out.println(Locale.getDefault().getCountry());
        String username = request.getParameter("user_login");
        String password = request.getParameter("user_password");
        System.out.println("doPost "+username + ", "+password);

        request.setAttribute("username",username);
        request.setAttribute("password", password);

        request.getRequestDispatcher("/profilepage.jsp").forward(request, response);
    }


   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(Locale.getDefault().getCountry());
        String username = request.getParameter("user_login");
        String password = request.getParameter("user_password");
        System.out.println("doGet "+username + ", "+password);

        request.setAttribute("username",username);
        request.setAttribute("password", password);
        //request.setAttribute("username", URLDecoder.decode(username, "UTF-8"));
        //request.setAttribute("password", URLDecoder.decode(password, "UTF-8"));
        //request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/profilepage.jsp").forward(request, response);
    }*/

}
