package controller;

import dto.EmployeeDTO;
import dto.Message;
import service.notifications.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/Messages")
public class MessageServlet {
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Message> messages = new ArrayList<>();
            HttpSession session = request.getSession();
            if (session != null && session.getAttribute("employee") != null) {
                int employeeID = ((EmployeeDTO) session.getAttribute("employee")).getEmployeeId();
                NotificationService notificationService = new NotificationService();
                messages = notificationService.getAllUserMessages(employeeID);
            }

            request.setAttribute("messages", messages);
            request.getRequestDispatcher("messages.jsp").forward(request, response);
        } catch (Exception e) {
            logger.error("Error retrieving messages: {}" ,e.getMessage());
            request.getRequestDispatcher("messages.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int senderId = Integer.parseInt(request.getParameter("senderId"));
            int receiverId = Integer.parseInt(request.getParameter("receiverId"));

            String title = request.getParameter("title");
            String content = request.getParameter("content");


            NotificationService notificationService = new NotificationService();
            notificationService.createNotification(senderId, receiverId, title, content);
        } catch (Exception e) {
            logger.error("Error sending message: {}" ,e.getMessage());
            request.getRequestDispatcher("messages.jsp").forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/Messages");


    }
}
