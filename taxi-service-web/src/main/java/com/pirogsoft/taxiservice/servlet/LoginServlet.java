package com.pirogsoft.taxiservice.servlet;

import com.pirogsoft.taxiservice.ComponentsContainer;
import com.pirogsoft.taxiservice.model.user.User;
import com.pirogsoft.taxiservice.service.UserService;
import com.pirogsoft.taxiservice.servlet.validation.ValidationUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pirogsoft.taxiservice.web.SessionAttributes.CURRENT_USER;

public class LoginServlet extends HttpServlet {
    private final UserService userService = ComponentsContainer.getInstance().getUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String login = req.getParameter(FormFields.LOGIN);
        String password = req.getParameter(FormFields.PASSWORD);
        ValidationUtils.validateMandatory(errors, login, "login");
        ValidationUtils.validateMandatory(errors, password, "password");
        Optional<User> userOptional = Optional.empty();
        if (errors.isEmpty()) {
            userOptional = userService.findUser(login, password);
            if (userOptional.isEmpty()) {
                errors.add("login or password is incorrect");
            }
        }
        if (errors.isEmpty()) {
            req.getSession().setAttribute(CURRENT_USER, userOptional.get());
            resp.sendRedirect(req.getContextPath() + "/ordering");
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            req.setAttribute("errors", errors);
            dispatcher.forward(req, resp);
        }
    }

    private static class FormFields {
        private static final String LOGIN = "login";
        private static final String PASSWORD = "password";
    }
}
