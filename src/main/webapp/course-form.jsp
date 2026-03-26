<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.scm.StudentCourseManagement.model.Course" %>

<%
    Course course = (Course) request.getAttribute("course");
    boolean isEdit = course != null;
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
    <title><%= isEdit ? "Edit Course" : "Add Course" %></title>

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

        form {
            background: white;
            padding: 1.5rem;
            border-radius: 6px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
            max-width: 500px;
        }

        form input {
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

        .back {
            display: inline-block;
            margin-top: 1rem;
            text-decoration: none;
            color: var(--primary);
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
            <li><a href="courses">Courses</a></li>
            <li><a href="enrollments">Enrollments</a></li>
        </ul>
    </nav>

    <main>

        <h2><%= isEdit ? "Edit Course" : "Add New Course" %></h2>
        <form action="courses" method="post">

            <% if (isEdit) { %>
                <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
            <% } %>

            <label>Name</label>
            <input type="text" name="name"
                   value="<%= isEdit ? course.getName() : "" %>" required>

            <label>Description</label>
            <input type="text" name="description"
                   value="<%= isEdit ? course.getDescription() : "" %>" required>

            <label>Duration</label>
            <input type="text" name="duration"
                   value="<%= isEdit ? course.getDuration() : "" %>" required>

            <label>Fee</label>
            <input type="number" step="0.01" name="fee"
                   value="<%= isEdit ? course.getFee() : "" %>" required>

            <button type="submit">
                <%= isEdit ? "Update Course" : "Add Course" %>
            </button>
        </form>

        <a href="courses" class="back">← Back to Course List</a>
    </main>
</div>

</body>
</html>