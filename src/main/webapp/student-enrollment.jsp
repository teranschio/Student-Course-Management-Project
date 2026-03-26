<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.scm.StudentCourseManagement.model.Student" %>
<%@ page import="com.scm.StudentCourseManagement.model.Course" %>
<%@ page import="com.scm.StudentCourseManagement.dao.CourseDAO" %>

<%
    Student student = (Student) request.getAttribute("student");

	@SuppressWarnings("unchecked")
    List<Integer> courseIds = (List<Integer>) request.getAttribute("courseIds");

    CourseDAO courseDAO = new CourseDAO();
%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("user") == null) {
        response.sendRedirect("index.html");
    }
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    
    <link rel="icon" href="images/student.png" />
    
    <title>Student Enrollments</title>

    <style>
        body {
            font-family: Arial;
            padding: 2rem;
            background: #bde8f5;
        }

        table {
            width: 100%;
            background: white;
            border-collapse: collapse;
        }

        th, td {
            padding: 0.8rem;
            border: 1px solid #ddd;
        }

        th {
            background: #4988c4;
            color: white;
        }

        .btn {
            padding: 0.4rem 0.8rem;
            background: red;
            color: white;
            text-decoration: none;
        }
    </style>
</head>

<body>

<h2>Enrollments of <%= student.getName() %></h2>

<br>

<table>
    <thead>
        <tr>
            <th>Course Name</th>
            <th>Action</th>
        </tr>
    </thead>

    <tbody>
    <%
        if (courseIds != null) {
            for (int courseId : courseIds) {
                Course c = courseDAO.getCourseById(courseId);
    %>
        <tr>
            <td><%= c.getName() %></td>
            <td>
                <a href="enrollments?action=unenroll&studentId=<%= student.getStudentId() %>&courseId=<%= courseId %>"
                   class="btn"
                   onclick="return confirm('Remove enrollment?')">
                   Remove
                </a>
            </td>
        </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<br>
<a href="enrollments">← Back</a>

</body>
</html>