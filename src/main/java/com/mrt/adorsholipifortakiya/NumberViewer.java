package com.mrt.adorsholipifortakiya;

import com.mrt.adorsholipifortakiya.model.NumberItem;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumberViewer {
    private final BorderPane root;
    private final Text numberDisplay;
    private final Text descriptionDisplay;
    private final ImageView imageView;

    private final Map<String, NumberItem> numberMap;

    public NumberViewer() {
        root = new BorderPane();
        root.setPadding(new Insets(20));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
                Main.showMainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

        numberDisplay = new Text("1");
        numberDisplay.setFont(Font.font("Arial", 350));

        descriptionDisplay = new Text("One");
        descriptionDisplay.setFont(Font.font("Arial", 34));

        imageView = new ImageView();
        imageView.setFitHeight(300);
        imageView.setPreserveRatio(true);

        VBox imageAndDescriptionBox = new VBox(10, imageView, descriptionDisplay);
        imageAndDescriptionBox.setAlignment(Pos.CENTER);

        HBox centerBox = new HBox(50, numberDisplay, imageAndDescriptionBox);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        numberMap = loadNumberItems();
        showNumber("1");  // Show default

        root.setBottom(createNumberButtons());
    }

    public Pane getView() {
        return root;
    }

    private FlowPane createNumberButtons() {
        FlowPane buttonPane = new FlowPane();
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10));

        for (int i = 1; i <= 10; i++) {
            String number = String.valueOf(i);
            Button btn = new Button(number);
            btn.setPrefWidth(80); // Increased width
            btn.setStyle(
                "-fx-background-color: #4CAF50; " +
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
                "-fx-background-color: #45a049; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 15 20; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            ));
            btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #4CAF50; " +
                "-fx-text-fill: white; " +
                "-fx-font-size: 20px; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 15 20; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5; " +
                "-fx-cursor: hand;"
            ));

            btn.setOnAction(e -> showNumber(number));
            buttonPane.getChildren().add(btn);
        }

        return buttonPane;
    }

    public void showNumber(String number) {
        NumberItem item = numberMap.get(number);
        if (item == null) return;

        numberDisplay.setText(item.getNumber());
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

    private Map<String, NumberItem> loadNumberItems() {
        Map<String, NumberItem> map = new HashMap<>();
        map.put("1", new NumberItem("1", "One", "src/main/resources/images/1.jpg", "src/main/resources/sounds/1.mp3"));
        map.put("2", new NumberItem("2", "Two", "src/main/resources/images/2.jpg", "src/main/resources/sounds/2.mp3"));
        map.put("3", new NumberItem("3", "Three", "src/main/resources/images/3.jpg", "src/main/resources/sounds/3.mp3"));
        map.put("4", new NumberItem("4", "Four", "src/main/resources/images/4.jpg", "src/main/resources/sounds/4.mp3"));
        map.put("5", new NumberItem("5", "Five", "src/main/resources/images/5.jpg", "src/main/resources/sounds/5.mp3"));
        map.put("6", new NumberItem("6", "Six", "src/main/resources/images/6.jpg", "src/main/resources/sounds/6.mp3"));
        map.put("7", new NumberItem("7", "Seven", "src/main/resources/images/7.jpg", "src/main/resources/sounds/7.mp3"));
        map.put("8", new NumberItem("8", "Eight", "src/main/resources/images/8.jpg", "src/main/resources/sounds/8.mp3"));
        map.put("9", new NumberItem("9", "Nine", "src/main/resources/images/9.jpg", "src/main/resources/sounds/9.mp3"));
        map.put("10", new NumberItem("10", "Ten", "src/main/resources/images/10.jpg", "src/main/resources/sounds/10.mp3"));
        return map;
    }

    private void playAnimations() {
        // Scale animation for the big number
        ScaleTransition scale = new ScaleTransition(Duration.millis(300), numberDisplay);
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
