package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.UserEntity;

public interface UserDAO extends CrudDAO<UserEntity> {
    UserEntity findByUsername(String username) throws Exception;
    UserEntity findById(int id) throws Exception;
}
