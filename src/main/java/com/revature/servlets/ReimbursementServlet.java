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
        if (req.getHeader("mode").equals("user")){
            List<Reimbursement> model = service.viewRequestsUser(Integer.parseInt(req.getHeader("user_id")));
            String json = rwjson.writeValueAsString(model);
            resp.setContentType("application/json");
            resp.getWriter().print(json);
            if (model.isEmpty()) {
                resp.setStatus(204);
            } else {
                resp.setStatus(200);
            }
        } else if (req.getHeader("mode").equals("id")){
            if(service.find(Integer.parseInt(req.getHeader("user_id")), Integer.parseInt(req.getHeader("req_id")))) {
                Reimbursement model = service.read(Integer.parseInt(req.getHeader("req_id")));
                String json = rwjson.writeValueAsString(model);
                resp.setContentType("application/json");
                resp.getWriter().print(json);
                resp.setStatus(200);
            }
            else{
                resp.setStatus(412);
            }
        } else if (req.getHeader("mode").equals("status")){
            List<Reimbursement> model = service.viewRequestsAdmin(req.getHeader("status"));

            if (model.isEmpty()) {
                resp.setStatus(204);
            } else {
                String json = rwjson.writeValueAsString(model);
                resp.setContentType("application/json");
                resp.getWriter().print(json);
                resp.setStatus(200);
            }
        } else if (req.getHeader("mode").equals("admin")){
            Reimbursement model = service.read(Integer.parseInt(req.getHeader("req_id")));

            if(!(model.equals(null))) {
                String json = rwjson.writeValueAsString(model);
                resp.setContentType("application/json");
                resp.getWriter().print(json);
                resp.setStatus(200);
            }
            else {
                resp.setStatus(204);
            }
        }
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
        if(service.find(Integer.parseInt(req.getHeader("user_id")), Integer.parseInt(req.getHeader("req_id")))) {
            service.delete(Integer.parseInt(req.getHeader("req_id")));
            resp.setStatus(200);
        } else{
            resp.setStatus(412);
        }
    }
}
