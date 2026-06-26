package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.UserBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.UserDAO;
import com.ijse.sams.dto.UserDTO;
import com.ijse.sams.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO dto) throws Exception {
        return userDAO.save(new UserEntity(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws Exception {
        return userDAO.update(new UserEntity(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean deleteUser(int id) throws Exception {
        return userDAO.delete(id);
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<UserEntity> entities = userDAO.getAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(new UserDTO(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRole()));
        }
        return dtos;
    }

    @Override
    public UserDTO login(String username, String password) throws Exception {
        UserEntity entity = userDAO.findByUsername(username);
        if (entity != null && entity.getPassword().equals(password)) {
            return new UserDTO(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getRole());
        }
        return null;
    }
}
