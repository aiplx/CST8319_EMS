package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sendRequest")
public class SendRequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employee = request.getParameter("employee");
        String manager = request.getParameter("manager");
        String requestType = request.getParameter("requestType");
        String comments = request.getParameter("comments");

        // Database connection details
        String jdbcUrl = "jdbc:mysql://localhost:3306/ems_schema";
        String jdbcUser = "root";
        String jdbcPassword = "Houseee25!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String sql = "INSERT INTO user_requests (request_id, request_type, employee_id, request_date, request_status, comments, employee_name) VALUES (?, ?, ?, ?, ?, ?, ?, 'Pending')";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, employee);
            stmt.setString(2, manager);
            stmt.setString(3, requestType);
            stmt.setString(4, comments);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

            response.sendRedirect("viewRequests.jsp");  // Redirect to the manager dashboard after submission
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
