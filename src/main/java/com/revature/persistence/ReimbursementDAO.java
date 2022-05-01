package com.revature.persistence;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO implements CRUDInterface<Reimbursement> {

    public static ResultSet find(int userID, int reqID) {
        String sql = "SELECT * FROM reimbursements WHERE user_id = ? AND req_id = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, reqID);
            return pstmt.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement create(Reimbursement r) {
        String sql = "INSERT INTO reimbursements (status, type, description, user_id, amount) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, r.getStatus());
            pstmt.setString(2, r.getType());
            pstmt.setString(3, r.getDescription());
            pstmt.setInt(4, r.getUserID());
            pstmt.setFloat(5, r.getAmount());
            pstmt.executeUpdate();
            System.out.println("\nReimbursement request sent!");
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Reimbursement read(int id) {
        String sql = "SELECT * FROM reimbursements WHERE req_id = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                Reimbursement r = new Reimbursement();
                r.setId(rs.getInt("req_id"));
                r.setType(rs.getString("type"));
                r.setStatus(rs.getString("status"));
                r.setDescription(rs.getString("description"));
                r.setUserID(rs.getInt("user_id"));
                r.setAmount(rs.getFloat("amount"));
                return r;
            }
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(Reimbursement unprocessedReimbursement) {
        String sql = "UPDATE reimbursements SET status = ?, type = ?, description = ?, amount = ? WHERE req_id = ?";

        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, unprocessedReimbursement.getStatus());
            pstmt.setString(2, unprocessedReimbursement.getType());
            pstmt.setString(3, unprocessedReimbursement.getDescription());
            pstmt.setFloat(4, unprocessedReimbursement.getAmount());
            pstmt.setInt(5, unprocessedReimbursement.getId());
            pstmt.executeUpdate();
            System.out.println("Reimbursement request updated!\n");
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM reimbursements WHERE req_id = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Reimbursement request deleted!");
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Reimbursement> getAll() {
        String sql = "SELECT * FROM reimbursements ORDER BY req_id ASC";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Reimbursement> list = new ArrayList<>();

            while(rs.next()){
                Reimbursement r = new Reimbursement();
                r.setId(rs.getInt("req_id"));
                r.setType(rs.getString("type"));
                r.setStatus(rs.getString("status"));
                r.setDescription(rs.getString("description"));
                r.setUserID(rs.getInt("user_id"));
                r.setAmount(rs.getFloat("amount"));
                list.add(r);
            }
            return list;
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static List<Reimbursement> getByUserID(int id) {
        String sql = "SELECT * FROM reimbursements WHERE user_id = ? ORDER BY req_id ASC";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<Reimbursement> list = new ArrayList<>();

            while(rs.next()){
                Reimbursement r = new Reimbursement();
                r.setId(rs.getInt("req_id"));
                r.setType(rs.getString("type"));
                r.setStatus(rs.getString("status"));
                r.setDescription(rs.getString("description"));
                r.setUserID(rs.getInt("user_id"));
                r.setAmount(rs.getFloat("amount"));
                list.add(r);
            }
            return list;
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public static List<Reimbursement> getByStatus(String status) {
        String sql = "SELECT * FROM reimbursements WHERE status = ? ORDER BY req_id ASC";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            List<Reimbursement> list = new ArrayList<>();

            while(rs.next()){
                Reimbursement r = new Reimbursement();
                r.setId(rs.getInt("req_id"));
                r.setType(rs.getString("type"));
                r.setStatus(rs.getString("status"));
                r.setDescription(rs.getString("description"));
                r.setUserID(rs.getInt("user_id"));
                r.setAmount(rs.getFloat("amount"));
                list.add(r);
            }
            return list;
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
