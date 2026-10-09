package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SporterModel;

public class SporterDAO {

    public List<SporterModel> findAll() {
        List<SporterModel> sporters = new ArrayList<>();
        String sql = """
            SELECT u.id, u.username, u.email, u.phone, u.address,
                   s.name, s.age, s.gender
            FROM sporters s
            JOIN users u ON s.user_id = u.id
            ORDER BY s.id
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                sporters.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error loading sporters: " + e.getMessage());
        }
        return sporters;
    }

    public boolean create(SporterModel sporter) {
        String userSql = """
            INSERT INTO users (username, password, email, phone, address)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
            """;
        String sporterSql = """
            INSERT INTO sporters (user_id, name, age, gender)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int userId;
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, sporter.getUsername());
                userStmt.setString(2, sporter.getPassword());
                userStmt.setString(3, sporter.getEmail());
                userStmt.setString(4, sporter.getPhone());
                userStmt.setString(5, sporter.getAddress());

                try (ResultSet rs = userStmt.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        return false;
                    }
                    userId = rs.getInt("id");
                }
            }

            try (PreparedStatement sporterStmt = conn.prepareStatement(sporterSql)) {
                sporterStmt.setInt(1, userId);
                sporterStmt.setString(2, sporter.getName());
                sporterStmt.setInt(3, sporter.getAge());
                sporterStmt.setString(4, sporter.getGender());
                sporterStmt.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            System.err.println("Error creating sporter: " + e.getMessage());
            return false;
        }
    }

    private SporterModel mapRow(ResultSet rs) throws SQLException {
        return new SporterModel(
            rs.getInt("id"),
            rs.getString("username"),
            "",
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("address"),
            rs.getString("name"),
            rs.getInt("age"),
            rs.getString("gender")
        );
    }
}
