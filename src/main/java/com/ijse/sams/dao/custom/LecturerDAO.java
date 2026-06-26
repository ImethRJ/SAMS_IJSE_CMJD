package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.LecturerEntity;
import java.util.List;

public interface LecturerDAO extends CrudDAO<LecturerEntity> {
    boolean saveSubjectAssignments(int lecturerId, List<Integer> subjectIds) throws Exception;
    List<Integer> getAssignedSubjectIds(int lecturerId) throws Exception;
}
