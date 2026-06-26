package com.ijse.sams.entity;

public class AttendanceEntity {
    private int id;
    private int scheduleId;
    private int studentId;
    private String status;

    public AttendanceEntity() {
    }

    public AttendanceEntity(int id, int scheduleId, int studentId, String status) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.studentId = studentId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
