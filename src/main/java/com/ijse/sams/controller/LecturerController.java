package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.LecturerBO;
import com.ijse.sams.dto.LecturerDTO;
import java.util.List;

public class LecturerController {
    private final LecturerBO lecturerBO = (LecturerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.LECTURER);

    public boolean saveLecturer(LecturerDTO dto) throws Exception {
        return lecturerBO.saveLecturer(dto);
    }

    public boolean updateLecturer(LecturerDTO dto) throws Exception {
        return lecturerBO.updateLecturer(dto);
    }

    public boolean deleteLecturer(int id) throws Exception {
        return lecturerBO.deleteLecturer(id);
    }

    public List<LecturerDTO> getAllLecturers() throws Exception {
        return lecturerBO.getAllLecturers();
    }

    public boolean saveSubjectAssignments(int lecturerId, List<Integer> subjectIds) throws Exception {
        return lecturerBO.saveSubjectAssignments(lecturerId, subjectIds);
    }

    public List<Integer> getAssignedSubjectIds(int lecturerId) throws Exception {
        return lecturerBO.getAssignedSubjectIds(lecturerId);
    }
}
