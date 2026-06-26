package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.SubjectDAO;
import com.ijse.sams.entity.SubjectEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {
    @Override
    public boolean save(SubjectEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO subjects (name, course_id) VALUES (?, ?)",
            entity.getName(),
            entity.getCourseId()
        );
    }

    @Override
    public boolean update(SubjectEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE subjects SET name = ?, course_id = ? WHERE id = ?",
            entity.getName(),
            entity.getCourseId(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM subjects WHERE id = ?", id);
    }

    @Override
    public List<SubjectEntity> getAll() throws Exception {
        List<SubjectEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM subjects");
        while (rst.next()) {
            list.add(new SubjectEntity(
                rst.getInt("id"),
                rst.getString("name"),
                rst.getInt("course_id")
            ));
        }
        return list;
    }

    @Override
    public List<SubjectEntity> getSubjectsByCourse(int courseId) throws Exception {
        List<SubjectEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM subjects WHERE course_id = ?", courseId);
        while (rst.next()) {
            list.add(new SubjectEntity(
                rst.getInt("id"),
                rst.getString("name"),
                rst.getInt("course_id")
            ));
        }
        return list;
    }
}
