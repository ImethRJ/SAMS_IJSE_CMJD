package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.ScheduleBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.CourseDAO;
import com.ijse.sams.dao.custom.LecturerDAO;
import com.ijse.sams.dao.custom.ScheduleDAO;
import com.ijse.sams.dao.custom.SubjectDAO;
import com.ijse.sams.dto.ScheduleDTO;
import com.ijse.sams.entity.CourseEntity;
import com.ijse.sams.entity.LecturerEntity;
import com.ijse.sams.entity.ScheduleEntity;
import com.ijse.sams.entity.SubjectEntity;
import java.util.ArrayList;
import java.util.List;

public class ScheduleBOImpl implements ScheduleBO {
    private final ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SCHEDULE);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBJECT);
    private final LecturerDAO lecturerDAO = (LecturerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LECTURER);

    private void checkConflicts(ScheduleDTO dto) throws Exception {
        List<ScheduleEntity> all = scheduleDAO.getAll();
        for (ScheduleEntity s : all) {
            if (s.getId() == dto.getId()) {
                continue;
            }
            if (s.getDate().toString().equals(dto.getDate().toString()) && s.getTimeSlot().equalsIgnoreCase(dto.getTimeSlot())) {
                if (s.getHallNumber().equalsIgnoreCase(dto.getHallNumber())) {
                    throw new RuntimeException("Hall " + dto.getHallNumber() + " is already booked for this date and time slot.");
                }
                if (s.getLecturerId() == dto.getLecturerId()) {
                    throw new RuntimeException("Lecturer is already scheduled to teach in another hall during this date and time slot.");
                }
            }
        }
    }

    @Override
    public boolean saveSchedule(ScheduleDTO dto) throws Exception {
        checkConflicts(dto);
        return scheduleDAO.save(new ScheduleEntity(dto.getId(), dto.getCourseId(), dto.getSubjectId(), dto.getLecturerId(), dto.getDate(), dto.getTimeSlot(), dto.getHallNumber()));
    }

    @Override
    public boolean updateSchedule(ScheduleDTO dto) throws Exception {
        checkConflicts(dto);
        return scheduleDAO.update(new ScheduleEntity(dto.getId(), dto.getCourseId(), dto.getSubjectId(), dto.getLecturerId(), dto.getDate(), dto.getTimeSlot(), dto.getHallNumber()));
    }

    @Override
    public boolean deleteSchedule(int id) throws Exception {
        return scheduleDAO.delete(id);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() throws Exception {
        List<ScheduleEntity> entities = scheduleDAO.getAll();
        List<CourseEntity> courses = courseDAO.getAll();
        List<SubjectEntity> subjects = subjectDAO.getAll();
        List<LecturerEntity> lecturers = lecturerDAO.getAll();

        List<ScheduleDTO> dtos = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            String courseName = "Unknown";
            for (CourseEntity c : courses) {
                if (c.getId() == entity.getCourseId()) {
                    courseName = c.getName();
                    break;
                }
            }
            String subjectName = "Unknown";
            for (SubjectEntity s : subjects) {
                if (s.getId() == entity.getSubjectId()) {
                    subjectName = s.getName();
                    break;
                }
            }
            String lecturerName = "Unknown";
            for (LecturerEntity l : lecturers) {
                if (l.getId() == entity.getLecturerId()) {
                    lecturerName = l.getName();
                    break;
                }
            }
            dtos.add(new ScheduleDTO(
                entity.getId(),
                entity.getCourseId(),
                entity.getSubjectId(),
                entity.getLecturerId(),
                entity.getDate(),
                entity.getTimeSlot(),
                courseName,
                subjectName,
                lecturerName,
                entity.getHallNumber()
            ));
        }
        return dtos;
    }

    @Override
    public List<ScheduleDTO> getSchedulesByLecturer(int lecturerId) throws Exception {
        List<ScheduleEntity> entities = scheduleDAO.getSchedulesByLecturer(lecturerId);
        List<CourseEntity> courses = courseDAO.getAll();
        List<SubjectEntity> subjects = subjectDAO.getAll();
        List<LecturerEntity> lecturers = lecturerDAO.getAll();

        List<ScheduleDTO> dtos = new ArrayList<>();
        for (ScheduleEntity entity : entities) {
            String courseName = "Unknown";
            for (CourseEntity c : courses) {
                if (c.getId() == entity.getCourseId()) {
                    courseName = c.getName();
                    break;
                }
            }
            String subjectName = "Unknown";
            for (SubjectEntity s : subjects) {
                if (s.getId() == entity.getSubjectId()) {
                    subjectName = s.getName();
                    break;
                }
            }
            String lecturerName = "Unknown";
            for (LecturerEntity l : lecturers) {
                if (l.getId() == entity.getLecturerId()) {
                    lecturerName = l.getName();
                    break;
                }
            }
            dtos.add(new ScheduleDTO(
                entity.getId(),
                entity.getCourseId(),
                entity.getSubjectId(),
                entity.getLecturerId(),
                entity.getDate(),
                entity.getTimeSlot(),
                courseName,
                subjectName,
                lecturerName,
                entity.getHallNumber()
            ));
        }
        return dtos;
    }
}
