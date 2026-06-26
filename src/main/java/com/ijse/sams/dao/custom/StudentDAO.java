package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.StudentEntity;
import java.util.List;

public interface StudentDAO extends CrudDAO<StudentEntity> {
    List<StudentEntity> getStudentsByCourse(int courseId) throws Exception;
}
