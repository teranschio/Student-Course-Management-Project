package com.scm.StudentCourseManagement.dao;

import com.scm.StudentCourseManagement.model.Course;
import com.scm.StudentCourseManagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    // 1) Add Course
    public boolean addCourse(Course course) {

        String sql = "INSERT INTO courses (name, description, duration, fee) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getDuration());
            ps.setDouble(4, course.getFee());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 2) Get All Courses
    public List<Course> getAllCourses() {

        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("duration"),
                        rs.getDouble("fee")
                );

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    // 3) Get Course By ID
    public Course getCourseById(int courseId) {

        String sql = "SELECT * FROM courses WHERE course_id = ?";
        Course course = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    course = new Course(
                            rs.getInt("course_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("duration"),
                            rs.getDouble("fee")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    // 4) Update Course
    public boolean updateCourse(Course course) {

        String sql = "UPDATE courses SET name=?, description=?, duration=?, fee=? WHERE course_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, course.getName());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getDuration());
            ps.setDouble(4, course.getFee());
            ps.setInt(5, course.getCourseId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 5) Delete Course
    public boolean deleteCourse(int courseId) {

        String sql = "DELETE FROM courses WHERE course_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 6) Count Courses
    public int countCourses() {

        String sql = "SELECT COUNT(*) FROM courses";

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
    
    // 7) Get All Courses in Ascending Order
    public List<Course> getAllCoursesAsc() {

        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY course_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("duration"),
                        rs.getDouble("fee")
                );

                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}