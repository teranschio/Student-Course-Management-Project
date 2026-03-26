<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.scm.StudentCourseManagement.dao.StudentDAO" %>
<%@ page import="com.scm.StudentCourseManagement.dao.CourseDAO" %>
<%@ page import="com.scm.StudentCourseManagement.dao.EnrollmentDAO" %>
<%@ page import="java.util.List" %>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    if (session.getAttribute("user") == null) {
        response.sendRedirect("index.html");
    }
%>

<%
    StudentDAO studentDAO = new StudentDAO();
    CourseDAO courseDAO = new CourseDAO();
    EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    int totalStudents = studentDAO.countStudents();
    int activeStudents = studentDAO.countActiveStudents();
    int totalCourses = courseDAO.countCourses();
    int totalEnrollments = enrollmentDAO.countEnrollments();
    
    EnrollmentDAO EnrollmentDAO = new EnrollmentDAO();

    List<Object[]> recentEnrollments = enrollmentDAO.getRecentEnrollments();
    List<Object[]> popularCourses = enrollmentDAO.getPopularCourses();
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard - Student Course Management</title>

    <link rel="icon" href="images/student.png" />

    <link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@400;600;700&display=swap" rel="stylesheet" />

    <style>
        :root {
            --primary-dark: #0f2854;
            --primary: #1c4d8d;
            --primary-light: #4988c4;
            --accent-light: #bde8f5;
            --white: #ffffff;
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
            background-color: var(--primary-dark);
            color: white;
            padding: 1rem;
            display: flex;
            justify-content: space-between;
        }

        .menu-toggle {
            display: none;
            font-size: 1.8rem;
            cursor: pointer;
        }

        .container {
            display: flex;
            flex: 1;
        }

        nav {
            width: 250px;
            background-color: var(--primary);
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
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        main {
            flex: 1;
            padding: 2rem;
        }

        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 1rem;
            margin-bottom: 2rem;
        }

        .card {
            background: white;
            padding: 1.5rem;
            border-left: 6px solid var(--primary-light);
            border-radius: 6px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
        }

        .card h3 {
            margin-bottom: 0.5rem;
        }
        
	        /* CONTENT GRID */
	      .dashboard-content {
	        display: grid;
	        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
	        gap: 1.5rem;
	      }
	
	      /* BOX */
	      .box {
	        background-color: var(--white);
	        padding: 1.5rem;
	        border-radius: 6px;
	        box-shadow: 0 3px 8px rgba(0, 0, 0, 0.1);
	      }
	
	      .box ul {
	        list-style: none;
	        margin-top: 1rem;
	      }
	
	      .box li {
	        margin-bottom: 0.5rem;
	      }

        .btn {
            display: inline-block;
            padding: 0.5rem 1rem;
            background-color: var(--primary-light);
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-top: 0.5rem;
        }

        footer {
            background-color: var(--primary-dark);
            color: white;
            text-align: center;
            padding: 1rem;
        }

        @media (max-width: 900px) {
            nav {
                width: 0;
                overflow: hidden;
                padding: 0;
            }

            nav.active {
                width: 220px;
                padding: 1rem;
            }

            .menu-toggle {
                display: block;
            }
        }
    </style>
</head>

<body>

<header>
    <h1>Student Course Management</h1>
    <span class="menu-toggle" id="menuToggle">&#9776;</span>
</header>

<div class="container">
    <nav id="sidebar">
        <ul>
            <li><a href="students">Students</a></li>
            <li><a href="courses">Courses</a></li>
            <li><a href="enrollments">Enrollments</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>

    <main>

        <h2>Overview</h2>

        <!-- ✅ Dynamic Cards -->
        <div class="cards">
            <div class="card">
                <h3>Total Students</h3>
                <p><%= totalStudents %></p>
            </div>

            <div class="card">
                <h3>Active Students</h3>
                <p><%= activeStudents %></p>
            </div>

            <div class="card">
                <h3>Total Courses</h3>
                <p><%= totalCourses %></p>
            </div>

            <div class="card">
                <h3>Total Enrollments</h3>
                <p><%= totalEnrollments %></p>
            </div>
        </div>

        <!-- Optional sections (static for now) -->
        <section class="dashboard-content">
          <article class="box">
            <h3>Recent Enrollments</h3>
            <ul>
				<%
				    if (recentEnrollments != null) {
				        for (Object[] row : recentEnrollments) {
				%>
				    <li><%= row[0] %> → <%= row[1] %></li>
				<%
				        }
				    }
				%>
			</ul>
          </article>

          <article class="box">
            <h3>Most Popular Courses</h3>
            <ul>
				<%
				    if (popularCourses != null) {
				        for (Object[] row : popularCourses) {
				%>
				    <li><%= row[0] %> – <%= row[1] %> Students</li>
				<%
				        }
				    }
				%>
			</ul>
          </article>

          <article class="box">
            <h3>Quick Actions</h3>
            <a href="student-form.jsp" class="btn">Add Student</a><br />
            <a href="course-form.jsp" class="btn">Add Course</a><br />
            <a href="enrollments" class="btn">Enroll Student</a>
          </article>
        </section>

        <br>
        <a href="logout" class="btn">Logout</a>

    </main>
</div>

<footer>
    &copy; 2026 Student Course Management System
</footer>

<script>
    const menuToggle = document.getElementById("menuToggle");
    const sidebar = document.getElementById("sidebar");

    menuToggle.addEventListener("click", () => {
        sidebar.classList.toggle("active");
    });

    window.addEventListener("resize", () => {
        if (window.innerWidth > 900) {
            sidebar.classList.remove("active");
        }
    });
</script>

</body>
</html>