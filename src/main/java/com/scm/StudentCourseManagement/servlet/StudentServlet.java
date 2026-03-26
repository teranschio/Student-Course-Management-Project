package com.scm.StudentCourseManagement.servlet;

import com.scm.StudentCourseManagement.dao.StudentDAO;
import com.scm.StudentCourseManagement.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    // ✅ Handle GET (List / Delete / Edit)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            listStudents(request, response);
        } else {
            switch (action) {
                case "delete":
                    deleteStudent(request, response);
                    break;
                case "edit":
                    editStudent(request, response);
                    break;
                default:
                    listStudents(request, response);
            }
        }
    }

    // ✅ Handle POST (Add / Update)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("studentId");

        if (studentId == null || studentId.isEmpty()) {
            insertStudent(request, response);
        } else {
            updateStudent(request, response);
        }
    }

    // ================= METHODS =================

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentDAO.getAllStudentsAsc();
        request.setAttribute("students", students);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Student student = new Student(
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("phone"),
                Date.valueOf(request.getParameter("dob")),
                request.getParameter("status")
        );

        studentDAO.addStudent(student);
        response.sendRedirect("students");
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.getStudentById(id);

        request.setAttribute("student", student);
        request.getRequestDispatcher("student-form.jsp").forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Student student = new Student(
                Integer.parseInt(request.getParameter("studentId")),
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("phone"),
                Date.valueOf(request.getParameter("dob")),
                request.getParameter("status")
        );

        studentDAO.updateStudent(student);
        response.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deleteStudent(id);
        response.sendRedirect("students");
    }
}