package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.AttendanceBO;
import com.ijse.sams.dto.AttendanceDTO;
import com.ijse.sams.dto.CustomDTO;
import java.sql.Date;
import java.util.List;

public class AttendanceController {
    private final AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ATTENDANCE);

    public boolean saveAttendanceList(List<AttendanceDTO> dtoList) throws Exception {
        return attendanceBO.saveAttendanceList(dtoList);
    }

    public List<AttendanceDTO> getAttendanceBySchedule(int scheduleId) throws Exception {
        return attendanceBO.getAttendanceBySchedule(scheduleId);
    }

    public List<CustomDTO> getDetailedAttendanceReport(Integer studentId, Integer courseId, Integer subjectId, Date startDate, Date endDate, Integer lecturerId) throws Exception {
        return attendanceBO.getDetailedAttendanceReport(studentId, courseId, subjectId, startDate, endDate, lecturerId);
    }

    public List<CustomDTO> getAttendanceSummaryReport(Integer courseId, Integer subjectId, Integer lecturerId) throws Exception {
        return attendanceBO.getAttendanceSummaryReport(courseId, subjectId, lecturerId);
    }
}
