package com.ijse.sams.entity;

import java.sql.Date;

public class ScheduleEntity {
    private int id;
    private int courseId;
    private int subjectId;
    private int lecturerId;
    private Date date;
    private String timeSlot;
    private String hallNumber;

    public ScheduleEntity() {
    }

    public ScheduleEntity(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public ScheduleEntity(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot, String hallNumber) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.hallNumber = hallNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }
}
