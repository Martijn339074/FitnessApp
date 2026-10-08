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
        String sql = "SELECT name, age, gender, email, phone, address FROM sporters ORDER BY id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sporters.add(new SporterModel(
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading sporters: " + e.getMessage());
        }
        return sporters;
    }
}