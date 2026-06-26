package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.CourseBO;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.SubjectDTO;
import java.util.List;

public class CourseController {
    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.COURSE);

    public boolean saveCourse(CourseDTO dto) throws Exception {
        return courseBO.saveCourse(dto);
    }

    public boolean updateCourse(CourseDTO dto) throws Exception {
        return courseBO.updateCourse(dto);
    }

    public boolean deleteCourse(int id) throws Exception {
        return courseBO.deleteCourse(id);
    }

    public List<CourseDTO> getAllCourses() throws Exception {
        return courseBO.getAllCourses();
    }

    public boolean saveSubject(SubjectDTO dto) throws Exception {
        return courseBO.saveSubject(dto);
    }

    public boolean updateSubject(SubjectDTO dto) throws Exception {
        return courseBO.updateSubject(dto);
    }

    public boolean deleteSubject(int id) throws Exception {
        return courseBO.deleteSubject(id);
    }

    public List<SubjectDTO> getSubjectsByCourse(int courseId) throws Exception {
        return courseBO.getSubjectsByCourse(courseId);
    }
}
