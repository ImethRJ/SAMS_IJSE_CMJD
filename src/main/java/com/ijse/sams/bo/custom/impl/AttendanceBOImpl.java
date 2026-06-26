package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.AttendanceBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.AttendanceDAO;
import com.ijse.sams.dao.custom.QueryDAO;
import com.ijse.sams.dao.custom.StudentDAO;
import com.ijse.sams.dto.AttendanceDTO;
import com.ijse.sams.dto.CustomDTO;
import com.ijse.sams.entity.AttendanceEntity;
import com.ijse.sams.entity.CustomEntity;
import com.ijse.sams.entity.StudentEntity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AttendanceBOImpl implements AttendanceBO {
    private final AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean saveAttendanceList(List<AttendanceDTO> dtoList) throws Exception {
        List<AttendanceEntity> entities = new ArrayList<>();
        for (AttendanceDTO dto : dtoList) {
            entities.add(new AttendanceEntity(dto.getId(), dto.getScheduleId(), dto.getStudentId(), dto.getStatus()));
        }
        return attendanceDAO.saveAttendanceList(entities);
    }

    @Override
    public List<AttendanceDTO> getAttendanceBySchedule(int scheduleId) throws Exception {
        List<AttendanceEntity> entities = attendanceDAO.getAttendanceBySchedule(scheduleId);
        List<StudentEntity> students = studentDAO.getAll();
        List<AttendanceDTO> dtos = new ArrayList<>();
        for (AttendanceEntity entity : entities) {
            String studentName = "Unknown";
            String regNumber = "Unknown";
            for (StudentEntity s : students) {
                if (s.getId() == entity.getStudentId()) {
                    studentName = s.getName();
                    regNumber = s.getRegNumber();
                    break;
                }
            }
            dtos.add(new AttendanceDTO(
                entity.getId(),
                entity.getScheduleId(),
                entity.getStudentId(),
                studentName,
                regNumber,
                entity.getStatus()
            ));
        }
        return dtos;
    }

    @Override
    public List<CustomDTO> getDetailedAttendanceReport(Integer studentId, Integer courseId, Integer subjectId, Date startDate, Date endDate, Integer lecturerId) throws Exception {
        List<CustomEntity> entities = queryDAO.getDetailedAttendanceReport(studentId, courseId, subjectId, startDate, endDate, lecturerId);
        List<CustomDTO> dtos = new ArrayList<>();
        for (CustomEntity entity : entities) {
            dtos.add(new CustomDTO(
                entity.getStudentName(),
                entity.getRegNumber(),
                entity.getCourseName(),
                entity.getSubjectName(),
                entity.getDate(),
                entity.getTimeSlot(),
                entity.getStatus()
            ));
        }
        return dtos;
    }

    @Override
    public List<CustomDTO> getAttendanceSummaryReport(Integer courseId, Integer subjectId, Integer lecturerId) throws Exception {
        List<CustomEntity> entities = queryDAO.getAttendanceSummaryReport(courseId, subjectId, lecturerId);
        List<CustomDTO> dtos = new ArrayList<>();
        for (CustomEntity entity : entities) {
            dtos.add(new CustomDTO(
                entity.getStudentName(),
                entity.getRegNumber(),
                entity.getTotalSessions(),
                entity.getPresentCount(),
                entity.getAbsentCount(),
                entity.getLateCount(),
                entity.getAttendancePercentage()
            ));
        }
        return dtos;
    }
}
