package com.calorizer.servlets;

import com.calorizer.db.UserAccountDAO;
import com.calorizer.items.UserAccount;
import com.calorizer.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/registration/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
            System.out.println("check userName: " + userName);
        } else {
                // Найти user в DB.
                user = new UserAccountDAO().findUser(userName, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                    System.out.println("check findUser : " + user);
                }
        }
        // В случае, если есть ошибка (т.е. нет такого юзера),
        // forward (перенаправить) к /WEB-INF/views/login.jsp
        if (hasError) {

            user = new UserAccount(userName, password);

            // TODO Сохранить информацию в request attribute перед forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/registration/login.jsp");

            dispatcher.forward(request, response);
        }
        // В случае, если нет ошибки.
        // Сохранить информацию пользователя в Session.
        // И перенаправить к странице profile.
        else {
            HttpSession session = request.getSession();
            // todo AppUtils.storeLoginedUser(session, user);

            // Если пользователь выбирает функцию "Remember me".
            if (remember) {
                AppUtils.storeUserCookie(response, user);
            }
            // Наоборот, удалить Cookie
            else {
                AppUtils.deleteUserCookie(response);
            }


            System.out.println("check hasError ");
            // Redirect (Перенаправить) на страницу /profilepage.
            //replace to usersettings page
            response.sendRedirect(request.getContextPath() + "/profilepage.jsp");
        }
    }

}
