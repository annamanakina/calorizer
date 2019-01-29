package com.calorizer.filter;

import com.calorizer.db.UserAccountDAO;
import com.calorizer.items.UserAccount;
import com.calorizer.utils.AppUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

///* for all servlet
//@WebFilter(filterName = "cookieFilter", urlPatterns = { "/profile" , "/error"})
public class CookieFilter {
        /*implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        UserAccount userInSession = AppUtils.getLoginedUser(session);
        System.out.println("doFilter: "+session+", userInSession "+userInSession);

        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(request, servletResponse);
            return;
        } else {
            System.out.println("doFilter else: "+session);
            String error = "Для входя в личный кабинет нажмите \"Войти\" или \"Регистрация\"";
            request.setAttribute("accessError", error);
            System.out.println("doFilter else setAttribute(\"accessError\": ");
            //request.getRequestDispatcher("/pages/error/testerror.jsp").forward(request, servletResponse);
           // ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/pages/error/testerror.jsp");
            System.out.println("doFilter else: getRequestDispatcher");
        }

        // Флаг(flag) для проверки Cookie.
        /*String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            String userName = AppUtils.getUserNameInCookie(request);
            UserAccount user = new UserAccountDAO().findUser(userName);
            AppUtils.storeLoginedUser(session, user);

            // Отметить проверенные Cookie.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }*/
      /*  filterChain.doFilter(request, servletResponse);

    }

    @Override
    public void destroy() {

    }*/
}
