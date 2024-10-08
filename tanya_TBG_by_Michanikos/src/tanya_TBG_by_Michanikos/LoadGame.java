package tanya_TBG_by_Michanikos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadGame {

    // Method to load the player's progress from the database
    public static Player loadProgress(int playerId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Player player = null;

        try {
            // Establish a database connection
            connection = DatabaseConnection.getConnection();

            // SQL query to retrieve the player's progress from the database
            String sql = "SELECT player_id, current_scene, player_stats, inventory " +
                         "FROM player_progress WHERE player_id = ?";

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, playerId); // Set the player ID to load the correct progress

            resultSet = pstmt.executeQuery();

            // Check if the result contains a player record
            if (resultSet.next()) {
                // Initialize a new Player object and set its fields from the database
                player = new Player();
                player.setPlayerId(resultSet.getInt("player_id"));
                player.setCurrentScene(resultSet.getString("current_scene"));
                player.setPlayerStats(resultSet.getInt("player_stats"));
                player.setInventory(resultSet.getString("inventory"));

                System.out.println("Player progress loaded successfully.");
            } else {
                System.out.println("No saved progress found for player ID: " + playerId);
            }

        } catch (SQLException e) {
            System.out.println("Error while loading player progress.");
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (pstmt != null) pstmt.close();
                DatabaseConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return player; // Return the loaded player object (or null if not found)
    }
}
