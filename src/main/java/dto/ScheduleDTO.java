package dto;

import java.sql.Date;
import java.sql.Time;

public class ScheduleDTO {
    // Fields representing the schedule's information
    private int scheduleId;   // Unique identifier for the schedule
    private int employeeId;   // ID of the employee associated with this schedule
    private Date date;        // Date of the schedule
    private Time startTime;   // Start time of the schedule
    private Time endTime;     // End time of the schedule

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
