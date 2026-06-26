package com.ijse.sams.dao.custom;

import com.ijse.sams.dao.CrudDAO;
import com.ijse.sams.entity.ScheduleEntity;
import java.util.List;

public interface ScheduleDAO extends CrudDAO<ScheduleEntity> {
    List<ScheduleEntity> getSchedulesByLecturer(int lecturerId) throws Exception;
}
