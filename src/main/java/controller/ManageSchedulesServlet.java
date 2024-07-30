package controller;


import dao.ScheduleDAO;
import dao.ScheduleDAOImpl;
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

@WebServlet("/manageSchedules")
public class ManageSchedulesServlet extends HttpServlet {
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    private static final Logger logger = LogManager.getLogger(ManageSchedulesServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ScheduleDTO> schedules = scheduleDAO.getAllSchedules();
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("manageSchedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String employeeIdStr = request.getParameter("employeeId");
        String dateStr = request.getParameter("date");
        String startTimeStr = request.getParameter("startTime");
        String endTimeStr = request.getParameter("endTime");

        try {
            int employeeId = Integer.parseInt(employeeIdStr);
            Date date = Date.valueOf(dateStr);
            Time startTime = Time.valueOf(startTimeStr + ":00");
            Time endTime = Time.valueOf(endTimeStr + ":00");

            if ("delete".equals(action)) {
                int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
                scheduleDAO.deleteSchedule(scheduleId);
            } else if ("update".equals(action)) {
                int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setScheduleId(scheduleId);
                schedule.setEmployeeId(employeeId);
                schedule.setDate(date);
                schedule.setStartTime(startTime);
                schedule.setEndTime(endTime);
                scheduleDAO.updateSchedule(schedule);
            } else if ("add".equals(action)) {
                ScheduleDTO schedule = new ScheduleDTO();
                schedule.setEmployeeId(employeeId);
                schedule.setDate(date);
                schedule.setStartTime(startTime);
                schedule.setEndTime(endTime);
                scheduleDAO.addSchedule(schedule);
            }
            response.sendRedirect("manageSchedules");
        } catch (IllegalArgumentException e) {
            logger.error("Invalid time format: startTime={}, endTime={}", startTimeStr, endTimeStr, e);
            request.setAttribute("error", "Invalid time format. Please use HH:MM format.");
            doGet(request, response);
        } catch (Exception e) {
            logger.error("Error processing request", e);
            request.setAttribute("error", "An error occurred while processing your request.");
            doGet(request, response);
        }
    }
}