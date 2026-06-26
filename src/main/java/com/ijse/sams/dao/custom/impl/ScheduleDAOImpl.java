package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.ScheduleDAO;
import com.ijse.sams.entity.ScheduleEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public boolean save(ScheduleEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO schedules (course_id, subject_id, lecturer_id, date, time_slot, hall_number) VALUES (?, ?, ?, ?, ?, ?)",
            entity.getCourseId(),
            entity.getSubjectId(),
            entity.getLecturerId(),
            entity.getDate(),
            entity.getTimeSlot(),
            entity.getHallNumber()
        );
    }

    @Override
    public boolean update(ScheduleEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE schedules SET course_id = ?, subject_id = ?, lecturer_id = ?, date = ?, time_slot = ?, hall_number = ? WHERE id = ?",
            entity.getCourseId(),
            entity.getSubjectId(),
            entity.getLecturerId(),
            entity.getDate(),
            entity.getTimeSlot(),
            entity.getHallNumber(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM schedules WHERE id = ?", id);
    }

    @Override
    public List<ScheduleEntity> getAll() throws Exception {
        List<ScheduleEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM schedules");
        while (rst.next()) {
            list.add(new ScheduleEntity(
                rst.getInt("id"),
                rst.getInt("course_id"),
                rst.getInt("subject_id"),
                rst.getInt("lecturer_id"),
                rst.getDate("date"),
                rst.getString("time_slot"),
                rst.getString("hall_number")
            ));
        }
        return list;
    }

    @Override
    public List<ScheduleEntity> getSchedulesByLecturer(int lecturerId) throws Exception {
        List<ScheduleEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM schedules WHERE lecturer_id = ?", lecturerId);
        while (rst.next()) {
            list.add(new ScheduleEntity(
                rst.getInt("id"),
                rst.getInt("course_id"),
                rst.getInt("subject_id"),
                rst.getInt("lecturer_id"),
                rst.getDate("date"),
                rst.getString("time_slot"),
                rst.getString("hall_number")
            ));
        }
        return list;
    }
}
