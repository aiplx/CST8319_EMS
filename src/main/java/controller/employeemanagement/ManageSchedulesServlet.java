package controller.employeemanagement;


import dao.schedule.ScheduleDAO;
import dao.schedule.ScheduleDAOImpl;
import dto.ScheduleDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/manageSchedules")  // Servlet annotation to handle requests at /manageSchedules
public class ManageSchedulesServlet extends HttpServlet {
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();  // Data Access Object (DAO) for Schedule operations
    private static final Logger logger = LogManager.getLogger(ManageSchedulesServlet.class);  // Logger instance for logging events

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handles GET requests to display the list of schedules
        List<ScheduleDTO> schedules = scheduleDAO.getAllSchedules();  // Retrieve all schedules from the database
        request.setAttribute("schedules", schedules);  // Set the schedules as a request attribute
        request.getRequestDispatcher("manageSchedules.jsp").forward(request, response);  // Forward to the JSP page to display schedules
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handles POST requests to add, update, or delete schedules
        String action = request.getParameter("action");  // Retrieve the action parameter from the request
        String employeeIdStr = request.getParameter("employeeId");  // Retrieve the employee ID from the request
        String dateStr = request.getParameter("date");  // Retrieve the date from the request
        String startTimeStr = request.getParameter("startTime");  // Retrieve the start time from the request
        String endTimeStr = request.getParameter("endTime");  // Retrieve the end time from the request

        try {
            int employeeId = Integer.parseInt(employeeIdStr);  // Parse employee ID to an integer
            Date date = Date.valueOf(dateStr);  // Parse date to a SQL Date object
            Time startTime = Time.valueOf(startTimeStr + ":00");  // Parse start time to a SQL Time object, adding seconds
            Time endTime = Time.valueOf(endTimeStr + ":00");  // Parse end time to a SQL Time object, adding seconds

            if ("delete".equals(action)) {
                // Case "delete": Delete a schedule by its ID
                int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));  // Parse schedule ID from the request
                scheduleDAO.deleteSchedule(scheduleId);  // Delete the schedule from the database
            } else if ("update".equals(action)) {
                // Case "update": Update an existing schedule
                int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));  // Parse schedule ID from the request
                ScheduleDTO schedule = new ScheduleDTO();  // Create a new ScheduleDTO object
                schedule.setScheduleId(scheduleId);  // Set the schedule ID
                schedule.setEmployeeId(employeeId);  // Set the employee ID
                schedule.setDate(date);  // Set the date
                schedule.setStartTime(startTime);  // Set the start time
                schedule.setEndTime(endTime);  // Set the end time
                scheduleDAO.updateSchedule(schedule);  // Update the schedule in the database
            } else if ("add".equals(action)) {
                // Case "add": Add a new schedule
                ScheduleDTO schedule = new ScheduleDTO();  // Create a new ScheduleDTO object
                schedule.setEmployeeId(employeeId);  // Set the employee ID
                schedule.setDate(date);  // Set the date
                schedule.setStartTime(startTime);  // Set the start time
                schedule.setEndTime(endTime);  // Set the end time
                scheduleDAO.addSchedule(schedule);  // Add the new schedule to the database
            }
            response.sendRedirect("manageSchedules");  // Redirect to the schedules list page after the operation
        } catch (IllegalArgumentException e) {
            // Handle exception when there is an invalid time format
            logger.error("Invalid time format: startTime={}, endTime={}", startTimeStr, endTimeStr, e);
            request.setAttribute("error", "Invalid time format. Please use HH:MM format.");  // Set error message
            doGet(request, response);  // Redisplay the schedules page with the error message
        } catch (Exception e) {
            // Handle any other exceptions
            logger.error("Error processing request", e);
            request.setAttribute("error", "An error occurred while processing your request.");  // Set error message
            doGet(request, response);  // Redisplay the schedules page with the error message
        }
    }
}
