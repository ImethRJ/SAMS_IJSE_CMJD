package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.LecturerDAO;
import com.ijse.sams.db.DBConnection;
import com.ijse.sams.entity.LecturerEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAOImpl implements LecturerDAO {
    @Override
    public boolean save(LecturerEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO lecturers (name, email, contact, user_id) VALUES (?, ?, ?, ?)",
            entity.getName(),
            entity.getEmail(),
            entity.getContact(),
            entity.getUserId()
        );
    }

    @Override
    public boolean update(LecturerEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE lecturers SET name = ?, email = ?, contact = ?, user_id = ? WHERE id = ?",
            entity.getName(),
            entity.getEmail(),
            entity.getContact(),
            entity.getUserId(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM lecturers WHERE id = ?", id);
    }

    @Override
    public List<LecturerEntity> getAll() throws Exception {
        List<LecturerEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM lecturers");
        while (rst.next()) {
            list.add(new LecturerEntity(
                rst.getInt("id"),
                rst.getString("name"),
                rst.getString("email"),
                rst.getString("contact"),
                rst.getObject("user_id") != null ? rst.getInt("user_id") : null
            ));
        }
        return list;
    }

    @Override
    public boolean saveSubjectAssignments(int lecturerId, List<Integer> subjectIds) throws Exception {
        Connection conn = null;
        boolean success = false;
        try {
            conn = DBConnection.getInstance().getConnection();
            conn.setAutoCommit(false);
            CrudUtil.executeUpdate("DELETE FROM lecturer_subjects WHERE lecturer_id = ?", lecturerId);
            for (Integer subjectId : subjectIds) {
                CrudUtil.executeUpdate(
                    "INSERT INTO lecturer_subjects (lecturer_id, subject_id) VALUES (?, ?)",
                    lecturerId,
                    subjectId
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
    public List<Integer> getAssignedSubjectIds(int lecturerId) throws Exception {
        List<Integer> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT subject_id FROM lecturer_subjects WHERE lecturer_id = ?", lecturerId);
        while (rst.next()) {
            list.add(rst.getInt("subject_id"));
        }
        return list;
    }
}
