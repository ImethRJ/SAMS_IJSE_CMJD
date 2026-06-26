package com.ijse.sams.dto;

public class AttendanceDTO {
    private int id;
    private int scheduleId;
    private int studentId;
    private String studentName;
    private String regNumber;
    private String status;

    public AttendanceDTO() {
    }

    public AttendanceDTO(int id, int scheduleId, int studentId, String status) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.studentId = studentId;
        this.status = status;
    }

    public AttendanceDTO(int id, int scheduleId, int studentId, String studentName, String regNumber, String status) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.regNumber = regNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
