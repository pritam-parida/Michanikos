package tanya_TBG_by_Michanikos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGame {

    private Player player;
    private StoryManager storyManager;
    private JFrame gameFrame;
    private JTextArea storyTextArea;
    private JButton option1Button;
    private JButton option2Button;
    private JButton saveGameButton;

    // Constructor to initialize the game
    public MainGame() {
        // Create a new player and story manager
        player = new Player(1, "scene_1", 10, "Basic Sword, Shield");
        storyManager = new StoryManager();
        
        // Initialize game frame and UI components
        initializeUI();
        displayCurrentScene();
    }

    // Initialize the UI components
    private void initializeUI() {
        gameFrame = new JFrame("Tanya: Saga of the Evil - Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(600, 400);
        gameFrame.setLayout(null);

        storyTextArea = new JTextArea();
        storyTextArea.setBounds(50, 50, 500, 150);
        storyTextArea.setEditable(false);
        gameFrame.add(storyTextArea);

        option1Button = new JButton();
        option1Button.setBounds(50, 250, 200, 40);
        gameFrame.add(option1Button);

        option2Button = new JButton();
        option2Button.setBounds(350, 250, 200, 40);
        gameFrame.add(option2Button);

        saveGameButton = new JButton("Save Game");
        saveGameButton.setBounds(50, 320, 500, 40);
        gameFrame.add(saveGameButton);

        // Event listeners for option buttons
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOption1();
            }
        });

        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOption2();
            }
        });

        // Event listener for saving the game
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });

        gameFrame.setVisible(true);
    }

    // Display the current scene description and options
    private void displayCurrentScene() {
        String sceneDescription = storyManager.getCurrentSceneDescription();
        storyTextArea.setText(sceneDescription);

        switch (storyManager.getCurrentScene()) {
            case "scene_1":
                option1Button.setText("Fight the Enemy");
                option2Button.setText("Run Away");
                break;
            case "scene_2":
                option1Button.setText("Continue to Battle");
                option2Button.setText("Search for a Hideout");
                break;
            case "scene_3":
                option1Button.setText("Explore the Hideout");
                option2Button.setText("Return to the Battlefield");
                break;
            case "scene_4":
                option1Button.setText("Search for Supplies");
                option2Button.setText("Leave the Hideout");
                break;
            default:
                option1Button.setText("Option 1");
                option2Button.setText("Option 2");
                break;
        }
    }

    // Handle the first option choice
    private void handleOption1() {
        switch (storyManager.getCurrentScene()) {
            case "scene_1":
                storyManager.moveToScene("scene_2");
                player.addStats(10);  // Gain stats for fighting
                break;
            case "scene_2":
                storyManager.moveToScene("scene_3");
                break;
            case "scene_3":
                storyManager.moveToScene("scene_4");
                player.addItemToInventory("Secret Documents");  // Add item to inventory
                break;
            case "scene_4":
                storyManager.moveToScene("scene_1");  // Loop back to the beginning
                break;
        }
        displayCurrentScene();
        player.displayPlayerInfo();  // Update the player's state
    }

    // Handle the second option choice
    private void handleOption2() {
        switch (storyManager.getCurrentScene()) {
            case "scene_1":
                storyManager.moveToScene("scene_3");  // Running leads to a different path
                break;
            case "scene_2":
                storyManager.moveToScene("scene_4");
                break;
            case "scene_3":
                storyManager.moveToScene("scene_1");
                break;
            case "scene_4":
                storyManager.moveToScene("scene_2");
                break;
        }
        displayCurrentScene();
        player.displayPlayerInfo();  // Update the player's state
    }

    // Save the current game state
    private void saveGame() {
        SaveGame.saveProgress(player);
        JOptionPane.showMessageDialog(gameFrame, "Game Saved Successfully!");
    }

    // Main method to start the game
    public static void main(String[] args) {
        new MainGame();
    }
}
