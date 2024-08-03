package controller;

import dto.EmployeeDTO;
import org.apache.logging.log4j.LogManager;
import service.notifications.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

@WebServlet("/Unread")
public class UnreadMessagesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UnreadMessagesServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int unreadMessages = 0;

        if (session != null && session.getAttribute("employee") != null) {
            try {
                EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
                int employeeID = employee.getEmployeeId();
                NotificationService notificationService = new NotificationService();
                unreadMessages = notificationService.numOfUnreadMessages(employeeID);
                logger.info("Unread messages for employee {}: {}", employeeID, unreadMessages);
            } catch (Exception e) {
                logger.error("Error retrieving unread messages count: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return;
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("UnAuthorized Access: No session available");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"unreadMessages\": " + unreadMessages + "}");
    }
}