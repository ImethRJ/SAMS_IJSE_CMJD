package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.AttendanceDTO;
import com.ijse.sams.dto.CustomDTO;
import java.sql.Date;
import java.util.List;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendanceList(List<AttendanceDTO> dtoList) throws Exception;
    List<AttendanceDTO> getAttendanceBySchedule(int scheduleId) throws Exception;
    List<CustomDTO> getDetailedAttendanceReport(Integer studentId, Integer courseId, Integer subjectId, Date startDate, Date endDate, Integer lecturerId) throws Exception;
    List<CustomDTO> getAttendanceSummaryReport(Integer courseId, Integer subjectId, Integer lecturerId) throws Exception;
}
