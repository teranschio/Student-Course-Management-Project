package com.scm.StudentCourseManagement.dao;

import com.scm.StudentCourseManagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // 1) Enroll Student into Course
    public boolean enrollStudent(int studentId, int courseId) {

        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 2) Get All Courses of a Student
    public List<Integer> getCoursesByStudent(int studentId) {

        List<Integer> courseIds = new ArrayList<>();
        String sql = "SELECT course_id FROM enrollments WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    courseIds.add(rs.getInt("course_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseIds;
    }

    // 3) Get All Students of a Course
    public List<Integer> getStudentsByCourse(int courseId) {

        List<Integer> studentIds = new ArrayList<>();
        String sql = "SELECT student_id FROM enrollments WHERE course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    studentIds.add(rs.getInt("student_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentIds;
    }

    // 4) Unenroll Student
    public boolean unenrollStudent(int studentId, int courseId) {

        String sql = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 5) Count Total Enrollments
    public int countEnrollments() {

        String sql = "SELECT COUNT(*) FROM enrollments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // 6) Check if Student Already Enrolled
    public boolean isEnrolled(int studentId, int courseId) {

        String sql = "SELECT 1 FROM enrollments WHERE student_id = ? AND course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    // To Get 5 Recent Enrollments
    public List<Object[]> getRecentEnrollments() {
        List<Object[]> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT s.name, c.name " +
                         "FROM enrollments e " +
                         "JOIN students s ON e.student_id = s.student_id " +
                         "JOIN courses c ON e.course_id = c.course_id " +
                         "ORDER BY e.enrollment_id DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString(1); // student name
                row[1] = rs.getString(2); // course name
                list.add(row);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // To Get 5 Popular Courses Available
    public List<Object[]> getPopularCourses() {
        List<Object[]> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT c.name, COUNT(e.student_id) AS total " +
                         "FROM enrollments e " +
                         "JOIN courses c ON e.course_id = c.course_id " +
                         "GROUP BY c.course_id, c.name " +
                         "ORDER BY total DESC LIMIT 5";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString(1); // course name
                row[1] = rs.getInt(2);    // count
                list.add(row);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}