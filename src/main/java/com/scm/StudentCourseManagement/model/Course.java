package com.scm.StudentCourseManagement.model;

public class Course {

    private int courseId;
    private String name;
    private String description;
    private String duration;
    private double fee;

    public Course() {}

    public Course(String name, String description, String duration, double fee) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.fee = fee;
    }

    public Course(int courseId, String name, String description, String duration, double fee) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.fee = fee;
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }
}