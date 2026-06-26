package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.AttendanceDAO;
import com.ijse.sams.db.DBConnection;
import com.ijse.sams.entity.AttendanceEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean save(AttendanceEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO attendance (schedule_id, student_id, status) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE status = ?",
            entity.getScheduleId(),
            entity.getStudentId(),
            entity.getStatus(),
            entity.getStatus()
        );
    }

    @Override
    public boolean update(AttendanceEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE attendance SET status = ? WHERE id = ?",
            entity.getStatus(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM attendance WHERE id = ?", id);
    }

    @Override
    public List<AttendanceEntity> getAll() throws Exception {
        List<AttendanceEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM attendance");
        while (rst.next()) {
            list.add(new AttendanceEntity(
                rst.getInt("id"),
                rst.getInt("schedule_id"),
                rst.getInt("student_id"),
                rst.getString("status")
            ));
        }
        return list;
    }

    @Override
    public boolean saveAttendanceList(List<AttendanceEntity> attendanceList) throws Exception {
        Connection conn = null;
        boolean success = false;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            for (AttendanceEntity entity : attendanceList) {
                CrudUtil.executeUpdate(
                    "INSERT INTO attendance (schedule_id, student_id, status) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE status = ?",
                    entity.getScheduleId(),
                    entity.getStudentId(),
                    entity.getStatus(),
                    entity.getStatus()
                );
            }
            conn.commit();
            success = true;
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
            }
        }
        return success;
    }

    @Override
    public List<AttendanceEntity> getAttendanceBySchedule(int scheduleId) throws Exception {
        List<AttendanceEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM attendance WHERE schedule_id = ?", scheduleId);
        while (rst.next()) {
            list.add(new AttendanceEntity(
                rst.getInt("id"),
                rst.getInt("schedule_id"),
                rst.getInt("student_id"),
                rst.getString("status")
            ));
        }
        return list;
    }
}
