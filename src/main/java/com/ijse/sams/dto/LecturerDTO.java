package com.ijse.sams.dto;

import java.util.List;

public class LecturerDTO {
    private int id;
    private String name;
    private String email;
    private String contact;
    private List<Integer> assignedSubjectIds;
    private Integer userId;
    private String username;
    private String password;

    public LecturerDTO() {
    }

    public LecturerDTO(int id, String name, String email, String contact) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public LecturerDTO(int id, String name, String email, String contact, Integer userId, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public LecturerDTO(int id, String name, String email, String contact, List<Integer> assignedSubjectIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.assignedSubjectIds = assignedSubjectIds;
    }

    public LecturerDTO(int id, String name, String email, String contact, List<Integer> assignedSubjectIds, Integer userId, String username, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.assignedSubjectIds = assignedSubjectIds;
        this.userId = userId;
        this.username = username;
        this.password = password;
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

    public List<Integer> getAssignedSubjectIds() {
        return assignedSubjectIds;
    }

    public void setAssignedSubjectIds(List<Integer> assignedSubjectIds) {
        this.assignedSubjectIds = assignedSubjectIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
