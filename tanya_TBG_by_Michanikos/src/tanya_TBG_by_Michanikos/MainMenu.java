package tanya_TBG_by_Michanikos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

    private JFrame frame;

    // Constructor to initialize and display the main menu
    public MainMenu() {
        frame = new JFrame("Tanya: Saga of the Evil - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        // Title label
        JLabel titleLabel = new JLabel("Tanya: Saga of the Evil", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // Buttons for "New Game", "Load Game", and "Exit"
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the buttons
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSavedGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitGame();
            }
        });

        // Add components to the frame
        frame.add(titleLabel);
        frame.add(newGameButton);
        frame.add(loadGameButton);
        frame.add(exitButton);

        // Display the window
        frame.setVisible(true);
    }

    // Method to start a new game
    private void startNewGame() {
        // Create a new player and start the story
        Player player = new Player(1, "scene_1", 10, "Sword");
        StoryManager storyManager = new StoryManager();

        // Close the main menu window
        frame.dispose();

        // Display the new game scene and player info
        System.out.println("Starting New Game...");
        player.displayPlayerInfo();
        System.out.println("Current Scene: " + storyManager.getCurrentSceneDescription());

        // Proceed to game loop or other functionality
    }

    // Method to load a saved game
    private void loadSavedGame() {
        // Simulate asking for player ID or auto-loading the last saved game
        int playerId = 1; // For demo purposes, assuming player ID 1
        Player loadedPlayer = LoadGame.loadProgress(playerId);

        if (loadedPlayer != null) {
            // Display loaded player info and current scene
            System.out.println("Loaded Game:");
            loadedPlayer.displayPlayerInfo();

            StoryManager storyManager = new StoryManager();
            storyManager.setCurrentScene(loadedPlayer.getCurrentScene());
            System.out.println("Current Scene: " + storyManager.getCurrentSceneDescription());

            // Close the main menu window
            frame.dispose();
            
            // Proceed to game loop or other functionality
        } else {
            JOptionPane.showMessageDialog(frame, "No saved progress found for Player ID: " + playerId);
        }
    }

    // Method to exit the game
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Exiting game...");
            System.exit(0);
        }
    }

    // Main method to launch the main menu
    public static void main(String[] args) {
        new MainMenu();  // Display the main menu
    }
}