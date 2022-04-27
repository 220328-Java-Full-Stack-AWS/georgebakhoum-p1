package com.revature.persistence;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDAO implements CRUDInterface<User> {

    public static ResultSet find(String username, String email) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            return pstmt.executeQuery();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            User u = new User();

            if(rs.next()){
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setId(rs.getInt("user_id"));
                u.setRole(rs.getString("role"));

                if(username.equals(u.getUsername()) && password.equals(u.getPassword())){
                    System.out.println("Login successful!");
                    return u;
                }
            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }

        System.out.println("Invalid username and/or password");
        System.out.println();
        return null;
    }

    @Override
    public User create(User u) {
        String sql = "INSERT INTO users (first_name, last_name, username, password, email, role)" +
                "values (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, u.getFirstName());
            pstmt.setString(2, u.getLastName());
            pstmt.setString(3, u.getUsername());
            pstmt.setString(4, u.getPassword());
            pstmt.setString(5, u.getEmail());
            pstmt.setString(6, u.getRole());
            pstmt.executeUpdate();
        }
        catch (SQLException | IOException e){
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public User read(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                User u = new User();
                u.setId(rs.getInt("user_id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                return u;
            }
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ?, email = ?, role = ? WHERE user_id = ?";

        try{
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getRole());
            pstmt.setInt(7, user.getId());
            pstmt.executeUpdate();
            System.out.println("User updated!\n");
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User deleted!");
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<User> list = new ArrayList<>();

            while(rs.next()){
                User u = new User();
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                list.add(u);
            }
            return list;
        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    public User getByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            User u = new User();

            while(rs.next()){
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setRole(rs.getString("role"));
                u.setId(rs.getInt("user_id"));

                if(username.equals(u.getUsername())){
                    System.out.println("User found!");
                    System.out.println();
                    return u;
                }
            }
        } catch (SQLException | IOException e){
            e.printStackTrace();
        }
        System.out.println("User not found.");
        System.out.println();
        return null;
    }
}
