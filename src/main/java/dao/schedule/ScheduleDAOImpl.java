package dao.schedule;

import dto.ScheduleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {
    private static final Logger logger = LogManager.getLogger(ScheduleDAOImpl.class);

    @Override
    public void addSchedule(ScheduleDTO schedule) {
        String sql = "INSERT INTO schedule (employee_id, date, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getEmployeeId());
            stmt.setDate(2, schedule.getDate());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.executeUpdate();
            logger.info("Schedule added for employee ID: {}", schedule.getEmployeeId());
        } catch (SQLException e) {
            logger.error("Error adding schedule", e);
        }
    }

    @Override
    public void updateSchedule(ScheduleDTO schedule) {
        String sql = "UPDATE schedule SET employee_id = ?, date = ?, start_time = ?, end_time = ? WHERE schedule_id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getEmployeeId());
            stmt.setDate(2, schedule.getDate());
            stmt.setTime(3, schedule.getStartTime());
            stmt.setTime(4, schedule.getEndTime());
            stmt.setInt(5, schedule.getScheduleId());
            stmt.executeUpdate();
            logger.info("Schedule updated for schedule ID: {}", schedule.getScheduleId());
        } catch (SQLException e) {
            logger.error("Error updating schedule", e);
        }
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        String sql = "DELETE FROM schedule WHERE schedule_id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
            logger.info("Schedule deleted with ID: {}", scheduleId);
        } catch (SQLException e) {
            logger.error("Error deleting schedule", e);
        }
    }

    @Override
    public ScheduleDTO getScheduleById(int scheduleId) {
        String sql = "SELECT * FROM schedule WHERE schedule_id = ?";
        ScheduleDTO schedule = null;
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                schedule = new ScheduleDTO();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setEmployeeId(rs.getInt("employee_id"));
                schedule.setDate(rs.getDate("date"));
                schedule.setStartTime(rs.getTime("start_time"));
                schedule.setEndTime(rs.getTime("end_time"));
                logger.info("Schedule retrieved with ID: {}", scheduleId);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving schedule", e);
        }
        return schedule;
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        String sql = "SELECT * FROM schedule";
        List<ScheduleDTO> schedules = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                schedule.setEmployeeId(rs.getInt("employee_id"));
                schedule.setDate(rs.getDate("date"));
                schedule.setStartTime(rs.getTime("start_time"));
                schedule.setEndTime(rs.getTime("end_time"));
                schedules.add(schedule);
                logger.info("Schedule added to list with ID: {}", schedule.getScheduleId());
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all schedules", e);
        }
        return schedules;
    }
}