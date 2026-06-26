package com.ijse.sams.dto;

import java.sql.Date;

public class ScheduleDTO {
    private int id;
    private int courseId;
    private int subjectId;
    private int lecturerId;
    private Date date;
    private String timeSlot;
    private String hallNumber;
    private String courseName;
    private String subjectName;
    private String lecturerName;

    public ScheduleDTO() {
    }

    public ScheduleDTO(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    public ScheduleDTO(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot, String hallNumber) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.hallNumber = hallNumber;
    }

    public ScheduleDTO(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot, String courseName, String subjectName, String lecturerName) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.courseName = courseName;
        this.subjectName = subjectName;
        this.lecturerName = lecturerName;
    }

    public ScheduleDTO(int id, int courseId, int subjectId, int lecturerId, Date date, String timeSlot, String courseName, String subjectName, String lecturerName, String hallNumber) {
        this.id = id;
        this.courseId = courseId;
        this.subjectId = subjectId;
        this.lecturerId = lecturerId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.courseName = courseName;
        this.subjectName = subjectName;
        this.lecturerName = lecturerName;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }
}
