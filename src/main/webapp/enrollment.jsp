<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.scm.StudentCourseManagement.model.Student" %>
<%@ page import="com.scm.StudentCourseManagement.model.Course" %>

<%
	@SuppressWarnings("unchecked")
    List<Student> students = (List<Student>) request.getAttribute("students");

	@SuppressWarnings("unchecked")
	List<Course> courses = (List<Course>) request.getAttribute("courses");
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Enrollments</title>

    <link rel="icon" href="images/student.png" />

    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@400;600;700&display=swap" rel="stylesheet" />

    <style>
        :root {
            --primary-dark: #0f2854;
            --primary: #1c4d8d;
            --primary-light: #4988c4;
            --accent-light: #bde8f5;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Josefin Sans", sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            background-color: var(--accent-light);
        }

        header {
            background: var(--primary-dark);
            color: white;
            padding: 1rem;
        }

        .container {
            display: flex;
            flex: 1;
        }

        nav {
            width: 250px;
            background: var(--primary);
            color: white;
            padding: 1rem;
        }

        nav ul { list-style: none; }
        nav li { margin: 1rem 0; }
        nav a {
            color: white;
            text-decoration: none;
            font-weight: 600;
        }

        main {
            flex: 1;
            padding: 2rem;
        }

        form {
            background: white;
            padding: 1.5rem;
            border-radius: 6px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
            max-width: 500px;
            margin-bottom: 2rem;
        }

        form select {
            width: 100%;
            padding: 0.6rem;
            margin: 0.5rem 0 1rem 0;
        }

        button {
            padding: 0.6rem 1.2rem;
            background: var(--primary-light);
            color: white;
            border: none;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 0.8rem;
            border: 1px solid #ddd;
        }

        th {
            background: var(--primary-light);
            color: white;
        }

        .btn-danger {
            background: red;
            color: white;
            padding: 0.4rem 0.8rem;
            text-decoration: none;
        }
    </style>
</head>

<body>

<header>
    <h1>Enrollments Management</h1>
</header>

<div class="container">
    <nav>
        <ul>
            <li><a href="dashboard.jsp">Dashboard</a></li>
            <li><a href="students">Students</a></li>
            <li><a href="courses">Courses</a></li>
        </ul>
    </nav>

    <main>

        <h2>Add Enrollment</h2>

        <form action="enrollments" method="post">

            <label>Select Student</label>
            <select name="studentId" required>
                <option value="">-- Choose Student --</option>
                <%
                    if (students != null) {
                        for (Student s : students) {
                %>
                    <option value="<%= s.getStudentId() %>">
                        <%= s.getName() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>

            <label>Select Course</label>
            <select name="courseId" required>
                <option value="">-- Choose Course --</option>
                <%
                    if (courses != null) {
                        for (Course c : courses) {
                %>
                    <option value="<%= c.getCourseId() %>">
                        <%= c.getName() %>
                    </option>
                <%
                        }
                    }
                %>
            </select>

            <button type="submit">Enroll</button>
        </form>

        <h2>View Student Enrollments</h2>

        <table>
            <thead>
                <tr>
                    <th>Student Name</th>
                    <th>Action</th>
                </tr>
            </thead>

            <tbody>
            <%
                if (students != null) {
                    for (Student s : students) {
            %>
                <tr>
                    <td><%= s.getName() %></td>
                    <td>
                        <a href="enrollments?action=view&id=<%= s.getStudentId() %>" class="btn-danger">
                            View Enrollments
                        </a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>

    </main>
</div>

</body>
</html>