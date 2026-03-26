package com.scm.StudentCourseManagement.dao;

import com.scm.StudentCourseManagement.util.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean validateUser(String username, String password) throws SQLException {
    	try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT password_hash FROM users WHERE username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");
                if (BCrypt.checkpw(password, storedHash)) {
                    return true;
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


