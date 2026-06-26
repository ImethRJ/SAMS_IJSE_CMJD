package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.UserDTO;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO dto) throws Exception;
    boolean updateUser(UserDTO dto) throws Exception;
    boolean deleteUser(int id) throws Exception;
    List<UserDTO> getAllUsers() throws Exception;
    UserDTO login(String username, String password) throws Exception;
}
