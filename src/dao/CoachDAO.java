package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CoachModel;

public class CoachDAO {

    public List<CoachModel> findAll() {
        List<CoachModel> coaches = new ArrayList<>();
        String sql = """
            SELECT u.id, u.username, u.email, u.phone, u.address,
                   c.name, c.specialty
            FROM coaches c
            JOIN users u ON c.user_id = u.id
            ORDER BY c.id
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                coaches.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error loading coaches: " + e.getMessage());
        }
        return coaches;
    }

    public boolean create(CoachModel coach) {
        String userSql = """
            INSERT INTO users (username, password, email, phone, address)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
            """;
        String coachSql = """
            INSERT INTO coaches (user_id, name, specialty)
            VALUES (?, ?, ?)
            """;

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            int userId;
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, coach.getUsername());
                userStmt.setString(2, coach.getPassword());
                userStmt.setString(3, coach.getEmail());
                userStmt.setString(4, coach.getPhone());
                userStmt.setString(5, coach.getAddress());

                try (ResultSet rs = userStmt.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        return false;
                    }
                    userId = rs.getInt("id");
                }
            }

            try (PreparedStatement coachStmt = conn.prepareStatement(coachSql)) {
                coachStmt.setInt(1, userId);
                coachStmt.setString(2, coach.getName());
                coachStmt.setString(3, coach.getSpecialty());
                coachStmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating coach: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Error closing connection: " + closeEx.getMessage());
                }
            }
        }
    }

    private CoachModel mapRow(ResultSet rs) throws SQLException {
        return new CoachModel(
            rs.getInt("id"),
            rs.getString("username"),
            "",
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getString("name"),
            rs.getString("specialty")
        );
    }
}
