package dao.schedule;

import dto.ScheduleDTO;
import java.util.List;

public interface ScheduleDAO {
    /**
     * Adds a new schedule to the database.
     *
     * @param schedule The schedule data to be added.
     */
    void addSchedule(ScheduleDTO schedule);

    /**
     * Updates an existing schedule in the database.
     *
     * @param schedule The schedule data with updated information.
     */
    void updateSchedule(ScheduleDTO schedule);

    /**
     * Deletes a schedule from the database based on its ID.
     *
     * @param scheduleId The ID of the schedule to be deleted.
     */
    void deleteSchedule(int scheduleId);

    /**
     * Retrieves a schedule's details from the database based on its ID.
     *
     * @param scheduleId The ID of the schedule to be retrieved.
     * @return The schedule data, or null if no schedule is found with the given ID.
     */
    ScheduleDTO getScheduleById(int scheduleId);

    /**
     * Retrieves a list of all schedules from the database.
     *
     * @return A list of all schedule data.
     */
    List<ScheduleDTO> getAllSchedules();
}