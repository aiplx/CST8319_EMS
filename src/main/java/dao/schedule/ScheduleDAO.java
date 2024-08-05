package dao.schedule;

import dto.ScheduleDTO;
import java.util.List;

public interface ScheduleDAO {
    void addSchedule(ScheduleDTO schedule);
    void updateSchedule(ScheduleDTO schedule);
    void deleteSchedule(int scheduleId);
    ScheduleDTO getScheduleById(int scheduleId);
    List<ScheduleDTO> getAllSchedules();
    List<ScheduleDTO> getSchedulesByEmployeeId(int employeeId);
}

