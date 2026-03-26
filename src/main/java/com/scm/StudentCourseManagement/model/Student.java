package com.scm.StudentCourseManagement.model;

import java.sql.Date;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String status;

    public Student() {}

    public Student(String name, String email, String phone, Date dob, String status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.status = status;
    }

    public Student(int studentId, String name, String email, String phone, Date dob, String status) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.status = status;
    }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}