package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.CoachModel;
import model.SporterModel;
import model.UserModel;

public class UserDAO {

    public UserModel findByUsername(String username) {
        String sql = """
            SELECT u.id, u.username, u.password, u.email, u.phone, u.address,
                   s.name AS sporter_name, s.age, s.gender, s.weight_kg, s.height_cm,
                   c.name AS coach_name, c.specialty
            FROM users u
            LEFT JOIN sporters s ON s.user_id = u.id
            LEFT JOIN coaches c ON c.user_id = u.id
            WHERE u.username = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading user: " + e.getMessage());
        }
        return null;
    }

    public boolean create(UserModel user) {
        String sql = """
            INSERT INTO users (username, password, email, phone, address)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }
                user.setId(rs.getInt("id"));
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            return false;
        }
    }

    public boolean update(UserModel user) {
        String sql = """
            UPDATE users
            SET password = ?, email = ?, phone = ?, address = ?
            WHERE id = ?
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getAddress());
            stmt.setInt(5, user.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    private UserModel mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String address = rs.getString("address");

        if (rs.getString("sporter_name") != null) {
            return new SporterModel(
                id, username, password, email, phone, address,
                rs.getString("sporter_name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getDouble("weight_kg"),
                rs.getDouble("height_cm")
            );
        }
        if (rs.getString("coach_name") != null) {
            return new CoachModel(
                id, username, password, email, phone, address,
                rs.getString("coach_name"),
                rs.getString("specialty")
            );
        }
        return new UserModel(id, username, password, email, phone, address);
    }
}
