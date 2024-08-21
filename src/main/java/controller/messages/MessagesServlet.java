package controller.messages;


import dto.EmployeeDTO;
import dto.Message;
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
import java.util.ArrayList;
import java.util.List;

/**
 *  The {@code MessagesServlet} class extends {@link HttpServlet} and is responsible for handling GET and Post requests
 *  to the URL paths "/MessagesServlet" and "/messages". It saves and retrieves messages to and from the database.
 */
@WebServlet(urlPatterns = {"/MessagesServlet", "/messages"})
public class MessagesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MessagesServlet.class);

    /**
     * Handles HTTP GET requests.
     * Retrieves messages from the database for the currently logged-in user and forwards them to the "messages.jsp"
     * page to be displayed.
     * It also updates the session with the number of unread messages the user has.
     * @param request  the {@link HttpServletRequest} object that contains the request the client made to the servlet
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Message> messages = new ArrayList<>();
        HttpSession session = request.getSession();



        try {

            if (session != null && session.getAttribute("employee") != null) {
                int employeeID = ((EmployeeDTO) session.getAttribute("employee")).getEmployeeId();
                NotificationService notificationService = new NotificationService();
                messages = notificationService.getAllUserMessages(employeeID);
                int numOfUnread = notificationService.numOfUnreadMessages(employeeID);
                session.setAttribute("unreadMessages", numOfUnread);

            }


            request.setAttribute("messages", messages);
            request.getRequestDispatcher("messages.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error retrieving messages: {}", e.getMessage());
            request.getRequestDispatcher("messages.jsp").forward(request, response);
        }

    }

    /** Handles HTTP POST requests.
     * Handles the creation of user messages and the marking of messages as read.
     * It saves messages to the database and saves the changes of the messages read status.
     * Upon an action, it redirects to the "/MessagesServlet" to refresh the page.
     * @param request  the {@link HttpServletRequest} object that contains the request the client made to the servlet
     * @param response the {@link HttpServletResponse} object that contains the response the servlet returns to the client
     * @throws ServletException if the request could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");

        String action = request.getParameter("action");
        if ("markMessage".equals(action)) {
            int messageID = (int) Long.parseLong(request.getParameter("messageID"));
            NotificationService notificationService = new NotificationService();
            notificationService.markAsRead(messageID);


            if (session.getAttribute("employee") != null) {
                int employeeID = employee.getEmployeeId();
                int unreadMessages = notificationService.numOfUnreadMessages(employeeID);
                session.setAttribute("unreadMessages", unreadMessages);
            }

        } else {

            try {
                String senderIdStr = request.getParameter("senderId");
                String receiverIdStr = request.getParameter("receiverId");
                String title = request.getParameter("title");
                String content = request.getParameter("content");

                logger.info("Parsed values - senderId: {}, receiverId: {}, title: {}, content: {}", senderIdStr, receiverIdStr, title, content);

                int senderId = Integer.parseInt(senderIdStr);

                int receiverId = Integer.parseInt(receiverIdStr);


                NotificationService notificationService = new NotificationService();
                notificationService.createNotification(senderId, receiverId, title, content);
            } catch (Exception e) {
                logger.error("Error sending message: {}", e.getMessage());
                logger.error("The messages: {}", request.getAttribute("messages"));
                request.getRequestDispatcher("messages.jsp").forward(request, response);
            }

        }


        response.sendRedirect(request.getContextPath() + "/MessagesServlet");


    }


}
