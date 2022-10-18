package com.elRols.springboot.horariointerceptor.app.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component("schedulle")
public class HorarioInterceptor implements HandlerInterceptor {

    @Value("${config.hours.opening}")
    private Integer opeinig;
    @Value("${config.hours.closing}")
    private Integer closing;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= opeinig && hour < closing) {
            StringBuilder message = new StringBuilder("Welcome to customer service");
            message.append(", attention from ");
            message.append(opeinig);
            message.append(" hrs. ");
            message.append(" to ");
            message.append(closing);
            message.append(" hrs. ");

            request.setAttribute("message", message.toString());
            return true;
        }
        response.sendRedirect(request.getContextPath().concat("/cerrado"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        if(modelAndView != null && handler instanceof HandlerMethod) {
            modelAndView.addObject("hours", message);
        }
    }
}
