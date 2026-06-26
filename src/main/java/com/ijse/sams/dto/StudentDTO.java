package com.ijse.sams.dto;

public class StudentDTO {
    private int id;
    private String name;
    private String regNumber;
    private String email;
    private String contact;
    private int courseId;
    private String courseName;

    public StudentDTO() {
    }

    public StudentDTO(int id, String name, String regNumber, String email, String contact, int courseId) {
        this.id = id;
        this.name = name;
        this.regNumber = regNumber;
        this.email = email;
        this.contact = contact;
        this.courseId = courseId;
        this.courseIds = new java.util.ArrayList<>();
        if (courseId > 0) {
            this.courseIds.add(courseId);
        }
    }

    public StudentDTO(int id, String name, String regNumber, String email, String contact, int courseId, String courseName) {
        this.id = id;
        this.name = name;
        this.regNumber = regNumber;
        this.email = email;
        this.contact = contact;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseIds = new java.util.ArrayList<>();
        if (courseId > 0) {
            this.courseIds.add(courseId);
        }
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

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    private java.util.List<Integer> courseIds = new java.util.ArrayList<>();

    public java.util.List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(java.util.List<Integer> courseIds) {
        this.courseIds = courseIds;
        if (courseIds != null && !courseIds.isEmpty()) {
            this.courseId = courseIds.get(0);
        }
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
        if (this.courseIds == null) {
            this.courseIds = new java.util.ArrayList<>();
        }
        if (this.courseIds.isEmpty()) {
            this.courseIds.add(courseId);
        } else {
            this.courseIds.set(0, courseId);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
