package com.mrt.adorsholipifortakiya;

import com.mrt.adorsholipifortakiya.model.AlphabetItem;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class BengaliVowelViewer {
    private final BorderPane root;
    private final Text letterDisplay;
    private final Text descriptionDisplay;
    private final ImageView imageView;

    private final Map<String, AlphabetItem> alphabetMap;

    public BengaliVowelViewer(Runnable onBack) {
        Font banglaFont = null;
        try {
            banglaFont = Font.loadFont(new FileInputStream("src/main/resources/fonts/kalpurush.ttf"), 100);
        } catch (FileNotFoundException e) {
            System.out.println("Font not found. Using default.");
            banglaFont = Font.font("Arial", 100);
        }


        root = new BorderPane();
        root.setPadding(new Insets(20));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            if (root.getParent() instanceof StackPane) {
                ((StackPane) root.getParent()).getChildren().clear();
            }
            onBack.run();
        });
        backButton.setStyle(
                "-fx-background-color: #f44336; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 16px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10 15; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
        );

        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 20, 0));
        root.setTop(topBar);


        letterDisplay = new Text("অ");
        letterDisplay.setFont(Font.font(banglaFont.getFamily(), 350));

        descriptionDisplay = new Text("অ -তে অলি");
        descriptionDisplay.setFont(Font.font(banglaFont.getFamily(), 34));

        imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        VBox imageAndDescriptionBox = new VBox(10, imageView, descriptionDisplay);
        imageAndDescriptionBox.setAlignment(Pos.CENTER);

        HBox centerBox = new HBox(50, letterDisplay, imageAndDescriptionBox);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        alphabetMap = loadAlphabetItems();
        showLetter("A");  // Show default

        root.setBottom(createAlphabetButtons());
    }

    public Pane getView() {
        return root;
    }

    private FlowPane createAlphabetButtons() {
        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10));

        for (String letter : loadAlphabetItems().keySet()) {
            Button btn = new Button(letter);
            btn.setPrefWidth(80); // Increased width
            btn.setStyle(
                "-fx-background-color: #6c36f4; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " + // Increased font size
                "-fx-font-weight: bold; " +
                "-fx-padding: 15 20; " + // Increased padding
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            );

            // Hover effect
            btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #6c36f4; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 15 20; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            ));
            btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #6c36f4; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 15 20; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            ));

            btn.setOnAction(e -> showLetter(letter));
            buttonPane.getChildren().add(btn);
        }

        return buttonPane;
    }

    public void showLetter(String letter) {
        AlphabetItem item = alphabetMap.get(letter);
        if (item == null) return;

        letterDisplay.setText(item.getLetter());
        descriptionDisplay.setText(item.getDescription());

        // Load Image
        File imgFile = new File(item.getImagePath());
        if (imgFile.exists()) {
            imageView.setImage(new Image(imgFile.toURI().toString()));
        }

        // Play sound
        File soundFile = new File(item.getSoundPath());
        if (soundFile.exists()) {
            Media media = new Media(soundFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }

        // Trigger animations
        playAnimations();
    }

    private Map<String, AlphabetItem> loadAlphabetItems() {
        Map<String, AlphabetItem> map = new TreeMap<>(Comparator.naturalOrder());

        map.put("অ", new AlphabetItem("অ", "অ -তে ", "src/main/resources/images/অ.png", "src/main/resources/sounds/অ.mp3"));
        map.put("আ", new AlphabetItem("আ", "আ -তে ", "src/main/resources/images/আ.png", "src/main/resources/sounds/আ.mp3"));
        map.put("ই", new AlphabetItem("ই", "ই -তে ", "src/main/resources/images/ই.png", "src/main/resources/sounds/ই.mp3"));
        map.put("ঈ", new AlphabetItem("ঈ", "ঈ -তে ", "src/main/resources/images/ঈ.png", "src/main/resources/sounds/ঈ.mp3"));
        map.put("ঋ", new AlphabetItem("ঋ", "ঋ -তে ", "src/main/resources/images/ঋ.png", "src/main/resources/sounds/ঋ.mp3"));
        map.put("এ", new AlphabetItem("এ", "এ -তে ", "src/main/resources/images/এ.png", "src/main/resources/sounds/এ.mp3"));
        map.put("ঐ", new AlphabetItem("ঐ", "ঐ -তে ", "src/main/resources/images/ঐ.png", "src/main/resources/sounds/ঐ.mp3"));
        map.put("ও", new AlphabetItem("ও", "ও -তে ", "src/main/resources/images/ও.png", "src/main/resources/sounds/ও.mp3"));
        map.put("ঔ", new AlphabetItem("ঔ", "ঔ -তে ", "src/main/resources/images/ঔ.png", "src/main/resources/sounds/ঔ.mp3"));

        return map;
    }

    private void playAnimations() {
        // Scale animation for the big letter
        ScaleTransition scale = new ScaleTransition(Duration.millis(300), letterDisplay);
        scale.setFromX(0.5);
        scale.setFromY(0.5);
        scale.setToX(1.0);
        scale.setToY(1.0);

        // Fade animation for the image
        FadeTransition fade = new FadeTransition(Duration.millis(400), imageView);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);

        // Slide animation for the description
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), descriptionDisplay);
        slide.setFromX(-200);
        slide.setToX(0);

        // Start them
        scale.play();
        fade.play();
        slide.play();
    }

}
