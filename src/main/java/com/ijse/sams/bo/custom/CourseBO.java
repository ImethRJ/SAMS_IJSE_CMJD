package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.CourseDTO;
import com.ijse.sams.dto.SubjectDTO;
import java.util.List;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDTO dto) throws Exception;
    boolean updateCourse(CourseDTO dto) throws Exception;
    boolean deleteCourse(int id) throws Exception;
    List<CourseDTO> getAllCourses() throws Exception;

    boolean saveSubject(SubjectDTO dto) throws Exception;
    boolean updateSubject(SubjectDTO dto) throws Exception;
    boolean deleteSubject(int id) throws Exception;
    List<SubjectDTO> getSubjectsByCourse(int courseId) throws Exception;
}
