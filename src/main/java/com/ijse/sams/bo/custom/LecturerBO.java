package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.LecturerDTO;
import java.util.List;

public interface LecturerBO extends SuperBO {
    boolean saveLecturer(LecturerDTO dto) throws Exception;
    boolean updateLecturer(LecturerDTO dto) throws Exception;
    boolean deleteLecturer(int id) throws Exception;
    List<LecturerDTO> getAllLecturers() throws Exception;
    boolean saveSubjectAssignments(int lecturerId, List<Integer> subjectIds) throws Exception;
    List<Integer> getAssignedSubjectIds(int lecturerId) throws Exception;
}
