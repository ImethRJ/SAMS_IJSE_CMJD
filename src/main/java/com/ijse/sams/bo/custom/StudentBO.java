package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.StudentDTO;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO dto) throws Exception;
    boolean updateStudent(StudentDTO dto) throws Exception;
    boolean deleteStudent(int id) throws Exception;
    List<StudentDTO> getAllStudents() throws Exception;
    List<StudentDTO> getStudentsByCourse(int courseId) throws Exception;
}
