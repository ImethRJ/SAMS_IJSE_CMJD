package com.ijse.sams.controller;

import com.ijse.sams.bo.BOFactory;
import com.ijse.sams.bo.custom.ScheduleBO;
import com.ijse.sams.dto.ScheduleDTO;
import java.util.List;

public class ScheduleController {
    private final ScheduleBO scheduleBO = (ScheduleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SCHEDULE);

    public boolean saveSchedule(ScheduleDTO dto) throws Exception {
        return scheduleBO.saveSchedule(dto);
    }

    public boolean updateSchedule(ScheduleDTO dto) throws Exception {
        return scheduleBO.updateSchedule(dto);
    }

    public boolean deleteSchedule(int id) throws Exception {
        return scheduleBO.deleteSchedule(id);
    }

    public List<ScheduleDTO> getAllSchedules() throws Exception {
        return scheduleBO.getAllSchedules();
    }

    public List<ScheduleDTO> getSchedulesByLecturer(int lecturerId) throws Exception {
        return scheduleBO.getSchedulesByLecturer(lecturerId);
    }
}
