package com.ijse.sams.bo.custom.impl;

import com.ijse.sams.bo.custom.CourseBO;
import com.ijse.sams.dao.DAOFactory;
import com.ijse.sams.dao.custom.CourseDAO;
import com.ijse.sams.dao.custom.SubjectDAO;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.SubjectDTO;
import com.ijse.sams.entity.CourseEntity;
import com.ijse.sams.entity.SubjectEntity;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUBJECT);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        return courseDAO.save(new CourseEntity(dto.getId(), dto.getName(), dto.getDescription()));
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        return courseDAO.update(new CourseEntity(dto.getId(), dto.getName(), dto.getDescription()));
    }

    @Override
    public boolean deleteCourse(int id) throws Exception {
        return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        List<CourseEntity> entities = courseDAO.getAll();
        List<CourseDTO> dtos = new ArrayList<>();
        for (CourseEntity entity : entities) {
            dtos.add(new CourseDTO(entity.getId(), entity.getName(), entity.getDescription()));
        }
        return dtos;
    }

    @Override
    public boolean saveSubject(SubjectDTO dto) throws Exception {
        return subjectDAO.save(new SubjectEntity(dto.getId(), dto.getName(), dto.getCourseId()));
    }

    @Override
    public boolean updateSubject(SubjectDTO dto) throws Exception {
        return subjectDAO.update(new SubjectEntity(dto.getId(), dto.getName(), dto.getCourseId()));
    }

    @Override
    public boolean deleteSubject(int id) throws Exception {
        return subjectDAO.delete(id);
    }

    @Override
    public List<SubjectDTO> getSubjectsByCourse(int courseId) throws Exception {
        List<SubjectEntity> entities = subjectDAO.getSubjectsByCourse(courseId);
        List<SubjectDTO> dtos = new ArrayList<>();
        for (SubjectEntity entity : entities) {
            dtos.add(new SubjectDTO(entity.getId(), entity.getName(), entity.getCourseId()));
        }
        return dtos;
    }
}
