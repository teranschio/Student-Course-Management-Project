package com.scm.StudentCourseManagement.model;

import java.sql.Date;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;
    private Date enrolledDate;

    public Enrollment() {}

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, Date enrolledDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledDate = enrolledDate;
    }

    public int getEnrollmentId() { return enrollmentId; }
    public void setEnrollmentId(int enrollmentId) { this.enrollmentId = enrollmentId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public Date getEnrolledDate() { return enrolledDate; }
    public void setEnrolledDate(Date enrolledDate) { this.enrolledDate = enrolledDate; }
}