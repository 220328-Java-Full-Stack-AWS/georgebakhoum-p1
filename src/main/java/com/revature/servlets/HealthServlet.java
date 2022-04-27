package com.revature.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HealthServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("is this eager?");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(getInitParameter("initParam"));

        resp.setStatus(200);
        String pass = "Health check passed!";
        resp.getWriter().print(pass);
        System.out.println(pass);
    }
}
