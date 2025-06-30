package com.mrt.adorsholipifortakiya.model;

public class AlphabetItem {
    private final String letter;
    private final String description; // e.g., "A for Apple"
    private final String imagePath;
    private final String soundPath;

    public AlphabetItem(String letter, String description, String imagePath, String soundPath) {
        this.letter = letter;
        this.description = description;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }

    public String getLetter() {
        return letter;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSoundPath() {
        return soundPath;
    }
}
