package com.scm.StudentCourseManagement.servlet;

import com.scm.StudentCourseManagement.dao.EnrollmentDAO;
import com.scm.StudentCourseManagement.dao.StudentDAO;
import com.scm.StudentCourseManagement.dao.CourseDAO;
import com.scm.StudentCourseManagement.model.Student;
import com.scm.StudentCourseManagement.model.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/enrollments")
public class EnrollmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    private EnrollmentDAO enrollmentDAO;
    private StudentDAO studentDAO;
    private CourseDAO courseDAO;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
    }

    // ✅ Handle GET (View / Unenroll)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            showEnrollmentPage(request, response);
        } else {
            switch (action) {
                case "view":
                    viewStudentEnrollments(request, response);
                    break;
                case "unenroll":
                    unenrollStudent(request, response);
                    break;
                default:
                    showEnrollmentPage(request, response);
            }
        }
    }

    // ✅ Handle POST (Enroll)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        if (!enrollmentDAO.isEnrolled(studentId, courseId)) {
            enrollmentDAO.enrollStudent(studentId, courseId);
        }

        response.sendRedirect("enrollments?action=view&id=" + studentId);
    }

    // ================= METHODS =================

    private void showEnrollmentPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentDAO.getAllStudents();
        List<Course> courses = courseDAO.getAllCourses();

        request.setAttribute("students", students);
        request.setAttribute("courses", courses);

        request.getRequestDispatcher("enrollment.jsp").forward(request, response);
    }

    private void viewStudentEnrollments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int studentId = Integer.parseInt(request.getParameter("id"));

        Student student = studentDAO.getStudentById(studentId);
        List<Integer> courseIds = enrollmentDAO.getCoursesByStudent(studentId);

        request.setAttribute("student", student);
        request.setAttribute("courseIds", courseIds);

        request.getRequestDispatcher("student-enrollment.jsp").forward(request, response);
    }

    private void unenrollStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        enrollmentDAO.unenrollStudent(studentId, courseId);

        response.sendRedirect("enrollments?action=view&id=" + studentId);
    }
}