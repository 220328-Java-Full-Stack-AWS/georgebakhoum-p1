package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper rwjson;

    @Override
    public void init() throws ServletException {
        this.service = new UserService();
        this.rwjson = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model;

        if(req.getHeader("mode").equals("getByUsername")){
            model = service.getByUsername(req.getHeader("username"));
        } else {
            model = service.read(Integer.parseInt(req.getHeader("user_id")));
        }

        String json = rwjson.writeValueAsString(model);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model =  rwjson.readValue(req.getInputStream(), User.class);
        String json = "";

        if (req.getHeader("mode").equals("register")){
            model = service.create(model);
            if(!(model.equals(null))) {
                resp.setStatus(201);
            }
        } else if (req.getHeader("mode").equals("login")){
            model = service.login(model.getUsername(), model.getPassword());
            if(!(model.equals(null))) {
                resp.setStatus(200);
            }
        }

        json = rwjson.writeValueAsString(model);
        resp.getWriter().print(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User model = rwjson.readValue(req.getInputStream(), User.class);
        service.update(model);
        String json = rwjson.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.delete(Integer.parseInt(req.getHeader("user_id")));
        resp.setStatus(200);
    }
}
