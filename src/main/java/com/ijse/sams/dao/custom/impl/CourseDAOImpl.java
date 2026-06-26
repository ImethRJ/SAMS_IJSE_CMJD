package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.CourseDAO;
import com.ijse.sams.entity.CourseEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean save(CourseEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO courses (name, description) VALUES (?, ?)",
            entity.getName(),
            entity.getDescription()
        );
    }

    @Override
    public boolean update(CourseEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE courses SET name = ?, description = ? WHERE id = ?",
            entity.getName(),
            entity.getDescription(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM courses WHERE id = ?", id);
    }

    @Override
    public List<CourseEntity> getAll() throws Exception {
        List<CourseEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM courses");
        while (rst.next()) {
            list.add(new CourseEntity(
                rst.getInt("id"),
                rst.getString("name"),
                rst.getString("description")
            ));
        }
        return list;
    }
}
