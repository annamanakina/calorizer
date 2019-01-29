package com.calorizer.filter;

import com.calorizer.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/checkMetabolicRateRaramsFilter"})
public class CheckMetabolicRateParamsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("UTF-8");

        String height = servletRequest.getParameter(Constant.PERSON_HEIGHT);
        String age = servletRequest.getParameter(Constant.PERSON_AGE);
        String weight = servletRequest.getParameter(Constant.PERSON_WEIGHT);

        if (age == null & height == null & weight == null ) {
            sendErrorMessage(servletRequest, servletResponse);
        } else if ( (age != null && age.equals(""))  |  (height != null && height.equals("")) | (weight != null &&weight.equals(""))){
            sendErrorMessage(servletRequest, servletResponse);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void sendErrorMessage(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        servletRequest.setAttribute("error_message", "заполните необходимые поля!!");
        servletRequest.getRequestDispatcher("/pages/usersettings.jsp").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
