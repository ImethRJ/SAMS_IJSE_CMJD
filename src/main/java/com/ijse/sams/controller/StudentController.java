package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.StudentBO;
import com.ijse.sams.dto.StudentDTO;
import java.util.List;

public class StudentController {
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public boolean saveStudent(StudentDTO dto) throws Exception {
        return studentBO.saveStudent(dto);
    }

    public boolean updateStudent(StudentDTO dto) throws Exception {
        return studentBO.updateStudent(dto);
    }

    public boolean deleteStudent(int id) throws Exception {
        return studentBO.deleteStudent(id);
    }

    public List<StudentDTO> getAllStudents() throws Exception {
        return studentBO.getAllStudents();
    }

    public List<StudentDTO> getStudentsByCourse(int courseId) throws Exception {
        return studentBO.getStudentsByCourse(courseId);
    }
}
