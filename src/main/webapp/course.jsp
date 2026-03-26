<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.scm.StudentCourseManagement.model.Course" %>

<%
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
    <title>Courses</title>

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
            display: flex;
            justify-content: space-between;
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

        nav ul {
            list-style: none;
        }

        nav li {
            margin: 1rem 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: 600;
        }

        main {
            flex: 1;
            padding: 2rem;
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

        .btn {
            padding: 0.4rem 0.8rem;
            background: var(--primary-light);
            color: white;
            text-decoration: none;
            margin-right: 5px;
        }

        .delete {
            background: red;
        }

        .editors{
            display:flex;
            flex-direction:column;
            gap:5px;
        }
    </style>
</head>

<body>

<header>
    <h1>Courses Management</h1>
</header>

<div class="container">
    <nav>
        <ul>
            <li><a href="dashboard.jsp">Dashboard</a></li>
            <li><a href="students">Students</a></li>
            <li><a href="enrollments">Enrollments</a></li>
        </ul>
    </nav>

    <main>

        <h2>Course List</h2>

        <br>
        <a href="course-form.jsp" class="btn">Add Course</a>
        <br><br>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Duration</th>
                    <th>Fee</th>
                    <th>Actions</th>
                </tr>
            </thead>

            <tbody>
            <%
                if (courses != null) {
                    for (Course c : courses) {
            %>
                <tr>
                    <td><%= c.getCourseId() %></td>
                    <td><%= c.getName() %></td>
                    <td><%= c.getDescription() %></td>
                    <td><%= c.getDuration() %></td>
                    <td><%= c.getFee() %></td>
                    <td class="editors">
                        <a href="courses?action=edit&id=<%= c.getCourseId() %>" class="btn">Edit</a>
                        <a href="courses?action=delete&id=<%= c.getCourseId() %>"
                           class="btn delete"
                           onclick="return confirm('Are you sure?')">Delete</a>
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