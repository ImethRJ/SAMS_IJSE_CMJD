package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.SuperDAO;
import com.ijse.sams.entity.CustomEntity;
import java.sql.Date;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getDetailedAttendanceReport(Integer studentId, Integer courseId, Integer subjectId, Date startDate, Date endDate, Integer lecturerId) throws Exception;
    List<CustomEntity> getAttendanceSummaryReport(Integer courseId, Integer subjectId, Integer lecturerId) throws Exception;
}
