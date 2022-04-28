package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.persistence.ReimbursementDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.revature.Driver.scan;

public class ReimbursementService {

    private static ReimbursementDAO rd;

    public ReimbursementService(){
        rd = new ReimbursementDAO();
    }

    public static boolean find(int userID, int reqID){
        ResultSet rs = ReimbursementDAO.find(userID, reqID);

        try {
            if (rs.next()) {
                return true;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Reimbursement create(Reimbursement r){
            return rd.create(r);
    }

    public static Reimbursement read(int reqID){
        Reimbursement r = rd.read(reqID);
        System.out.println("Requester ID: " + r.getUserID());
        System.out.println("Reimbursement request ID: " + r.getId());
        System.out.println("Status: " + r.getStatus());
        System.out.println("Reimbursement type: " + r.getType());
        System.out.println("Description: " + r.getDescription() + "\n");
        return r;
    }

    public static void update(Reimbursement r){
        rd.update(r);
    }

    public static void delete(int reqID){
        rd.delete(reqID);
    }

    public static List<Reimbursement> viewRequestsUser(int userID){
        List<Reimbursement> list = ReimbursementDAO.getByUserID(userID);
        ReimbursementService.printList(list);
        return list;
    }

    public static List<Reimbursement> viewRequestsAdmin(String status){
        List<Reimbursement> list = new ArrayList<>();

        if(status.equals("All")){
            list = rd.getAll();
        } else {
            list = ReimbursementDAO.getByStatus(status);
        }

        /*switch(choice){
            case 1:
                list = ReimbursementDAO.getByStatus("Pending");
                break;
            case 2:
                list = ReimbursementDAO.getByStatus("Approved");
                break;
            case 3:
                list = ReimbursementDAO.getByStatus("Denied");
                break;
            case 4:
                list = rd.getAll();
                break;
            default:
                break;
        }*/

        if(list.isEmpty()){
            System.out.println("There are no requests to display.");
        }

        ReimbursementService.printList(list);
        return list;
    }

    private static void printList(List<Reimbursement> list){
        for (Reimbursement reimbursement : list) {
            System.out.println("Requester ID: " + reimbursement.getUserID());
            System.out.println("Reimbursement request ID: " + reimbursement.getId());
            System.out.println("Status: " + reimbursement.getStatus());
            System.out.println("Reimbursement type: " + reimbursement.getType());
            System.out.println("Description: " + reimbursement.getDescription() + "\n");
        }
    }
}
