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
                sporters.add(new SporterModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    "",
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading sporters: " + e.getMessage());
        }
        return sporters;
    }
}
