package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimbursementServlet extends HttpServlet {
    private ReimbursementService service;
    private ObjectMapper rwjson;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.rwjson = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Reimbursement> model = service.viewRequestsUser(Integer.parseInt(req.getHeader("user_id")));
        String json = rwjson.writeValueAsString(model);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement model =  rwjson.readValue(req.getInputStream(), Reimbursement.class);
        model = service.create(model);
        String json = rwjson.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement model = rwjson.readValue(req.getInputStream(), Reimbursement.class);
        service.update(model);
        String json = rwjson.writeValueAsString(model);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //service.cancelRequest(Integer.parseInt(req.getHeader("user_id")), Integer.parseInt(req.getHeader("req_id")));
        resp.setStatus(200);
    }
}
