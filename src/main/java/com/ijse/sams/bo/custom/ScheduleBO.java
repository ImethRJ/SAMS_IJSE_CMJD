package com.ijse.sams.bo.custom;

import com.ijse.sams.bo.SuperBO;
import com.ijse.sams.dto.ScheduleDTO;
import java.util.List;

public interface ScheduleBO extends SuperBO {
    boolean saveSchedule(ScheduleDTO dto) throws Exception;
    boolean updateSchedule(ScheduleDTO dto) throws Exception;
    boolean deleteSchedule(int id) throws Exception;
    List<ScheduleDTO> getAllSchedules() throws Exception;
    List<ScheduleDTO> getSchedulesByLecturer(int lecturerId) throws Exception;
}
