package tanya_TBG_by_Michanikos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveGame {

    // Method to save the player's progress to the database
    public static void saveProgress(Player player) {
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            // Establish a database connection
            connection = DatabaseConnection.getConnection();

            // SQL query to insert or update the player's progress in the database
            String sql = "MERGE INTO player_progress p " +
                         "USING (SELECT ? AS player_id FROM dual) src " +
                         "ON (p.player_id = src.player_id) " +
                         "WHEN MATCHED THEN " +
                         "  UPDATE SET p.current_scene = ?, p.player_stats = ?, p.inventory = ? " +
                         "WHEN NOT MATCHED THEN " +
                         "  INSERT (player_id, current_scene, player_stats, inventory) " +
                         "  VALUES (?, ?, ?, ?)";

            pstmt = connection.prepareStatement(sql);

            // Set the parameters based on the Player object
            pstmt.setInt(1, player.getPlayerId());
            pstmt.setString(2, player.getCurrentScene());
            pstmt.setInt(3, player.getPlayerStats());
            pstmt.setString(4, player.getInventory());

            // For insert when no match is found
            pstmt.setInt(5, player.getPlayerId());
            pstmt.setString(6, player.getCurrentScene());
            pstmt.setInt(7, player.getPlayerStats());
            pstmt.setString(8, player.getInventory());

            // Execute the SQL statement
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Player progress saved successfully.");
            } else {
                System.out.println("Failed to save player progress.");
            }

        } catch (SQLException e) {
            System.out.println("Error while saving player progress.");
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (pstmt != null) pstmt.close();
                DatabaseConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
