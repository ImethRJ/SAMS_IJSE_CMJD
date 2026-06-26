package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.AttendanceEntity;
import java.util.List;

public interface AttendanceDAO extends CrudDAO<AttendanceEntity> {
    boolean saveAttendanceList(List<AttendanceEntity> attendanceList) throws Exception;
    List<AttendanceEntity> getAttendanceBySchedule(int scheduleId) throws Exception;
}
