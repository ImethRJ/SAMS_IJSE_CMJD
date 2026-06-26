package com.ijse.sams.dao.custom.impl;

import com.ijse.sams.dao.CrudUtil;
import com.ijse.sams.dao.custom.UserDAO;
import com.ijse.sams.entity.UserEntity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(UserEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "INSERT INTO users (username, password, role) VALUES (?, ?, ?)",
            entity.getUsername(),
            entity.getPassword(),
            entity.getRole()
        );
    }

    @Override
    public boolean update(UserEntity entity) throws Exception {
        return CrudUtil.executeUpdate(
            "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?",
            entity.getUsername(),
            entity.getPassword(),
            entity.getRole(),
            entity.getId()
        );
    }

    @Override
    public boolean delete(int id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public List<UserEntity> getAll() throws Exception {
        List<UserEntity> list = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM users");
        while (rst.next()) {
            list.add(new UserEntity(
                rst.getInt("id"),
                rst.getString("username"),
                rst.getString("password"),
                rst.getString("role")
            ));
        }
        return list;
    }

    @Override
    public UserEntity findByUsername(String username) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM users WHERE username = ?", username);
        if (rst.next()) {
            return new UserEntity(
                rst.getInt("id"),
                rst.getString("username"),
                rst.getString("password"),
                rst.getString("role")
            );
        }
        return null;
    }

    @Override
    public UserEntity findById(int id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM users WHERE id = ?", id);
        if (rst.next()) {
            return new UserEntity(
                rst.getInt("id"),
                rst.getString("username"),
                rst.getString("password"),
                rst.getString("role")
            );
        }
        return null;
    }
}
