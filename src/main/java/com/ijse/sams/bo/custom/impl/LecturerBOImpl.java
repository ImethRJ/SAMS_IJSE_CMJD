package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.LecturerBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.LecturerDAO;
import com.ijse.sams.dao.custom.UserDAO;
import com.ijse.sams.dto.LecturerDTO;
import com.ijse.sams.entity.LecturerEntity;
import com.ijse.sams.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;

public class LecturerBOImpl implements LecturerBO {
    private final LecturerDAO lecturerDAO = (LecturerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LECTURER);
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveLecturer(LecturerDTO dto) throws Exception {
        if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
            if (userDAO.findByUsername(dto.getUsername()) != null) {
                throw new RuntimeException("Username already exists!");
            }
            UserEntity userEntity = new UserEntity(0, dto.getUsername(), dto.getPassword(), "Lecturer");
            if (!userDAO.save(userEntity)) {
                return false;
            }
            UserEntity savedUser = userDAO.findByUsername(dto.getUsername());
            if (savedUser == null) {
                return false;
            }
            dto.setUserId(savedUser.getId());
        }
        return lecturerDAO.save(new LecturerEntity(
            dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getContact(),
            dto.getUserId()
        ));
    }

    @Override
    public boolean updateLecturer(LecturerDTO dto) throws Exception {
        if (dto.getUsername() != null && !dto.getUsername().isEmpty()) {
            UserEntity existingUser = userDAO.findByUsername(dto.getUsername());
            if (existingUser != null && existingUser.getId() != dto.getUserId()) {
                throw new RuntimeException("Username already exists!");
            }
            if (dto.getUserId() != null && dto.getUserId() > 0) {
                UserEntity userEntity = new UserEntity(dto.getUserId(), dto.getUsername(), dto.getPassword(), "Lecturer");
                userDAO.update(userEntity);
            } else {
                UserEntity userEntity = new UserEntity(0, dto.getUsername(), dto.getPassword(), "Lecturer");
                if (userDAO.save(userEntity)) {
                    UserEntity savedUser = userDAO.findByUsername(dto.getUsername());
                    if (savedUser != null) {
                        dto.setUserId(savedUser.getId());
                    }
                }
            }
        }
        return lecturerDAO.update(new LecturerEntity(
            dto.getId(),
            dto.getName(),
            dto.getEmail(),
            dto.getContact(),
            dto.getUserId()
        ));
    }

    @Override
    public boolean deleteLecturer(int id) throws Exception {
        List<LecturerEntity> lecturers = lecturerDAO.getAll();
        Integer userIdToDelete = null;
        for (LecturerEntity l : lecturers) {
            if (l.getId() == id) {
                userIdToDelete = l.getUserId();
                break;
            }
        }
        boolean lecturerDeleted = lecturerDAO.delete(id);
        if (lecturerDeleted && userIdToDelete != null && userIdToDelete > 0) {
            userDAO.delete(userIdToDelete);
        }
        return lecturerDeleted;
    }

    @Override
    public List<LecturerDTO> getAllLecturers() throws Exception {
        List<LecturerEntity> entities = lecturerDAO.getAll();
        List<LecturerDTO> dtos = new ArrayList<>();
        for (LecturerEntity entity : entities) {
            String username = "";
            String password = "";
            if (entity.getUserId() != null && entity.getUserId() > 0) {
                UserEntity user = userDAO.findById(entity.getUserId());
                if (user != null) {
                    username = user.getUsername();
                    password = user.getPassword();
                }
            }
            dtos.add(new LecturerDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getContact(),
                lecturerDAO.getAssignedSubjectIds(entity.getId()),
                entity.getUserId(),
                username,
                password
            ));
        }
        return dtos;
    }

    @Override
    public boolean saveSubjectAssignments(int lecturerId, List<Integer> subjectIds) throws Exception {
        return lecturerDAO.saveSubjectAssignments(lecturerId, subjectIds);
    }

    @Override
    public List<Integer> getAssignedSubjectIds(int lecturerId) throws Exception {
        return lecturerDAO.getAssignedSubjectIds(lecturerId);
    }
}
