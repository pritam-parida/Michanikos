package tanya_TBG_by_Michanikos;

public class Player {
    private int playerId;            // Unique player ID
    private String currentScene;     // Current scene or stage the player is in
    private int playerStats;         // Numerical representation of player stats (e.g., health, experience)
    private String inventory;        // String to represent inventory items

    // Constructor to initialize Player with default values
    public Player() {
        this.playerId = 0;
        this.currentScene = "";
        this.playerStats = 0;
        this.inventory = "";
    }

    // Constructor to initialize Player with specific values
    public Player(int playerId, String currentScene, int playerStats, String inventory) {
        this.playerId = playerId;
        this.currentScene = currentScene;
        this.playerStats = playerStats;
        this.inventory = inventory;
    }

    // Getters and Setters for all fields
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }

    public int getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(int playerStats) {
        this.playerStats = playerStats;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    // Additional methods to update or modify player data can be added here
    public void addStats(int points) {
        this.playerStats += points;
    }

    public void addItemToInventory(String item) {
        if (this.inventory.isEmpty()) {
            this.inventory = item;
        } else {
            this.inventory += ", " + item;
        }
    }
    
    // Display player details
    public void displayPlayerInfo() {
        System.out.println("Player ID: " + playerId);
        System.out.println("Current Scene: " + currentScene);
        System.out.println("Player Stats: " + playerStats);
        System.out.println("Inventory: " + inventory);
    }
}
