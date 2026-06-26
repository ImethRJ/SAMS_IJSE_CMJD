package com.ijse.sams.dto;

public class SubjectDTO {
    private int id;
    private String name;
    private int courseId;
    private String courseName;

    public SubjectDTO() {
    }

    public SubjectDTO(int id, String name, int courseId) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
    }

    public SubjectDTO(int id, String name, int courseId, String courseName) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
