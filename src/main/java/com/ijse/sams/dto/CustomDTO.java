package com.ijse.sams.dto;

import java.sql.Date;

public class CustomDTO {
    private String studentName;
    private String regNumber;
    private String courseName;
    private String subjectName;
    private Date date;
    private String timeSlot;
    private String status;
    private int totalSessions;
    private int presentCount;
    private int absentCount;
    private int lateCount;
    private double attendancePercentage;

    public CustomDTO() {
    }

    public CustomDTO(String studentName, String regNumber, String courseName, String subjectName, Date date, String timeSlot, String status) {
        this.studentName = studentName;
        this.regNumber = regNumber;
        this.courseName = courseName;
        this.subjectName = subjectName;
        this.date = date;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    public CustomDTO(String studentName, String regNumber, int totalSessions, int presentCount, int absentCount, int lateCount, double attendancePercentage) {
        this.studentName = studentName;
        this.regNumber = regNumber;
        this.totalSessions = totalSessions;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.lateCount = lateCount;
        this.attendancePercentage = attendancePercentage;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public void setLateCount(int lateCount) {
        this.lateCount = lateCount;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(double attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
