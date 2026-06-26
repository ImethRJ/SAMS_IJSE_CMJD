package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.StudentBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.CourseDAO;
import com.ijse.sams.dao.custom.StudentDAO;
import com.ijse.sams.dto.StudentDTO;
import com.ijse.sams.entity.CourseEntity;
import com.ijse.sams.entity.StudentEntity;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        StudentEntity entity = new StudentEntity(dto.getId(), dto.getName(), dto.getRegNumber(), dto.getEmail(), dto.getContact(), dto.getCourseId());
        entity.setCourseIds(dto.getCourseIds());
        return studentDAO.save(entity);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        StudentEntity entity = new StudentEntity(dto.getId(), dto.getName(), dto.getRegNumber(), dto.getEmail(), dto.getContact(), dto.getCourseId());
        entity.setCourseIds(dto.getCourseIds());
        return studentDAO.update(entity);
    }

    @Override
    public boolean deleteStudent(int id) throws Exception {
        return studentDAO.delete(id);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<StudentEntity> entities = studentDAO.getAll();
        List<CourseEntity> courses = courseDAO.getAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (StudentEntity entity : entities) {
            List<String> enrolledCourseNames = new ArrayList<>();
            for (int cid : entity.getCourseIds()) {
                for (CourseEntity c : courses) {
                    if (c.getId() == cid) {
                        enrolledCourseNames.add(c.getName());
                        break;
                    }
                }
            }
            String combinedNames = String.join(", ", enrolledCourseNames);
            StudentDTO dto = new StudentDTO(
                entity.getId(),
                entity.getName(),
                entity.getRegNumber(),
                entity.getEmail(),
                entity.getContact(),
                entity.getCourseId(),
                combinedNames.isEmpty() ? "None" : combinedNames
            );
            dto.setCourseIds(entity.getCourseIds());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<StudentDTO> getStudentsByCourse(int courseId) throws Exception {
        List<StudentEntity> entities = studentDAO.getStudentsByCourse(courseId);
        List<StudentDTO> dtos = new ArrayList<>();
        for (StudentEntity entity : entities) {
            StudentDTO dto = new StudentDTO(
                entity.getId(),
                entity.getName(),
                entity.getRegNumber(),
                entity.getEmail(),
                entity.getContact(),
                entity.getCourseId()
            );
            dto.setCourseIds(entity.getCourseIds());
            dtos.add(dto);
        }
        return dtos;
    }
}
