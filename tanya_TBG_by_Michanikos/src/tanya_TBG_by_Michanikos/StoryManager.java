package tanya_TBG_by_Michanikos;

import java.util.HashMap;

public class StoryManager {
    private HashMap<String, String> scenes;  // A map to store scenes and their descriptions
    private String currentScene;             // Keeps track of the current scene

    // Constructor to initialize scenes and set the initial scene
    public StoryManager() {
        scenes = new HashMap<>();
        initializeStory();
        currentScene = "scene_1";  // Starting scene
    }

    // Method to initialize the game story with scenes and descriptions
    private void initializeStory() {
        scenes.put("scene_1", "You wake up in a battlefield with no memory of how you got there.");
        scenes.put("scene_2", "You see an enemy unit approaching. Do you run or fight?");
        scenes.put("scene_3", "Victory! You've defeated the enemy and now must decide your next move.");
        scenes.put("scene_4", "You stumble upon a secret hideout with valuable information.");
        // Add more scenes to expand the story
    }

    // Method to get the description of the current scene
    public String getCurrentSceneDescription() {
        return scenes.get(currentScene);
    }

    // Method to transition to the next scene based on the player's choice
    public void moveToScene(String nextScene) {
        if (scenes.containsKey(nextScene)) {
            currentScene = nextScene;
            System.out.println("Moving to: " + currentScene);
        } else {
            System.out.println("Invalid scene. No transition occurred.");
        }
    }

    // Method to get the current scene identifier
    public String getCurrentScene() {
        return currentScene;
    }

    // Method to set the current scene (useful when loading saved progress)
    public void setCurrentScene(String currentScene) {
        if (scenes.containsKey(currentScene)) {
            this.currentScene = currentScene;
        } else {
            System.out.println("Invalid scene provided.");
        }
    }
    
    // Display the available scenes (for debugging or story expansion)
    public void displayAvailableScenes() {
        System.out.println("Available Scenes:");
        for (String scene : scenes.keySet()) {
            System.out.println(scene + ": " + scenes.get(scene));
        }
    }
}
