package controller.employeemanagement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateRequestStatus")
public class UpdateRequestStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ticketNo = request.getParameter("ticketNo");
        String action = request.getParameter("action");
        String status = "Pending";

        if ("Approve".equals(action)) {
            status = "Approved";
        } else if ("Deny".equals(action)) {
            status = "Denied";
        }

        // Database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/ems_schema";
        String jdbcUser = "root";
        String jdbcPassword = "Houseee25!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);

            String sql = "UPDATE user_requests SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, status);
            stmt.setString(2, ticketNo);

            stmt.executeUpdate();
            stmt.close();
            conn.close();

            response.sendRedirect("viewRequests.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
