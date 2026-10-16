package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ExerciseModel;

public class ExerciseDAO {

    public List<ExerciseModel> findAll() {
        List<ExerciseModel> exercises = new ArrayList<>();
        String sql = """
            SELECT id, name, muscle_group, description
            FROM exercises
            ORDER BY id
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                exercises.add(new ExerciseModel(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("muscle_group"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error loading exercises: " + e.getMessage());
        }
        return exercises;
    }
}
