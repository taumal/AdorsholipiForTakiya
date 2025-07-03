package com.mrt.adorsholipifortakiya.model;

public class NumberItem {
    private final String number;
    private final String description;
    private final String imagePath;
    private final String soundPath;

    public NumberItem(String number, String description, String imagePath, String soundPath) {
        this.number = number;
        this.description = description;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }

    public String getNumber() {
        return number;
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
