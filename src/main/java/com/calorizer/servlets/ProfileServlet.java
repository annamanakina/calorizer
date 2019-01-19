package com.calorizer.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/profilepage")
public class ProfileServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String username = request.getParameter("user_login");
            String password = request.getParameter("user_password");
            System.out.println("profile page "+username + ", "+password);
            //request.getRequestDispatcher("/profilepage.jsp").forward(request, response);
        }

}
