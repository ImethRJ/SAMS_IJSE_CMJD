package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.UserBO;
import com.ijse.sams.dto.UserDTO;
import java.util.List;

public class UserController {
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public boolean saveUser(UserDTO dto) throws Exception {
        return userBO.saveUser(dto);
    }

    public boolean updateUser(UserDTO dto) throws Exception {
        return userBO.updateUser(dto);
    }

    public boolean deleteUser(int id) throws Exception {
        return userBO.deleteUser(id);
    }

    public List<UserDTO> getAllUsers() throws Exception {
        return userBO.getAllUsers();
    }

    public UserDTO login(String username, String password) throws Exception {
        return userBO.login(username, password);
    }
}
