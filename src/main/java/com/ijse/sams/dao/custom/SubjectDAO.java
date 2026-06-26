package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.SubjectEntity;
import java.util.List;

public interface SubjectDAO extends CrudDAO<SubjectEntity> {
    List<SubjectEntity> getSubjectsByCourse(int courseId) throws Exception;
}
