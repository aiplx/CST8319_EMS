package controller.messages;


import dto.EmployeeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.notification.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The {@code UnreadMessagesServlet} class is responsible for handling GET requests to the URL path "/Unread"
 * It retrieves the number of unread messages a logged-in user has and returns the number as a JSON response.
 */
@WebServlet("/Unread")
public class UnreadMessagesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UnreadMessagesServlet.class);

    /**
     * Handles HTTP GET request. Retrieves the number of unread messages the currently logged-in user has.
     * If the user is not logged in, it returns an unauthorized access code.
     *
     * @param request  the {@link HttpServletRequest} object that contains the request the client made to the servlet,
     *                includes session information such as the employee ID.
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client,
     *                includes information such as the number of unread messages or an appropriate error code.
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
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