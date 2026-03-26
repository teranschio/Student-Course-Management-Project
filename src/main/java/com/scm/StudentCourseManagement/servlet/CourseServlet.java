package com.scm.StudentCourseManagement.servlet;

import com.scm.StudentCourseManagement.dao.CourseDAO;
import com.scm.StudentCourseManagement.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    // ✅ Handle GET (List / Delete / Edit)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            listCourses(request, response);
        } else {
            switch (action) {
                case "delete":
                    deleteCourse(request, response);
                    break;
                case "edit":
                    editCourse(request, response);
                    break;
                default:
                    listCourses(request, response);
            }
        }
    }

    // ✅ Handle POST (Add / Update)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String courseId = request.getParameter("courseId");

        if (courseId == null || courseId.isEmpty()) {
            insertCourse(request, response);
        } else {
            updateCourse(request, response);
        }
    }

    // ================= METHODS =================

    private void listCourses(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Course> courses = courseDAO.getAllCoursesAsc();
        request.setAttribute("courses", courses);
        request.getRequestDispatcher("course.jsp").forward(request, response);
    }

    private void insertCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Course course = new Course(
                request.getParameter("name"),
                request.getParameter("description"),
                request.getParameter("duration"),
                Double.parseDouble(request.getParameter("fee"))
        );

        courseDAO.addCourse(course);
        response.sendRedirect("courses");
    }

    private void editCourse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseDAO.getCourseById(id);

        request.setAttribute("course", course);
        request.getRequestDispatcher("course-form.jsp").forward(request, response);
    }

    private void updateCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Course course = new Course(
                Integer.parseInt(request.getParameter("courseId")),
                request.getParameter("name"),
                request.getParameter("description"),
                request.getParameter("duration"),
                Double.parseDouble(request.getParameter("fee"))
        );

        courseDAO.updateCourse(course);
        response.sendRedirect("courses");
    }

    private void deleteCourse(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(id);
        response.sendRedirect("courses");
    }
}