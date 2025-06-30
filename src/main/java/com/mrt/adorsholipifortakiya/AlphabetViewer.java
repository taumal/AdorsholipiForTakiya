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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AlphabetViewer {
    private final BorderPane root;
    private final Text letterDisplay;
    private final Text descriptionDisplay;
    private final ImageView imageView;

    private final Map<String, AlphabetItem> alphabetMap;

    public AlphabetViewer() {
        root = new BorderPane();
//        root.setCenter(centerBox);
//        root.setBottom(createAlphabetButtons());
        root.setPadding(new Insets(20));

        letterDisplay = new Text("A");
        letterDisplay.setFont(Font.font("Arial", 250));

        descriptionDisplay = new Text("A for Apple");
        descriptionDisplay.setFont(Font.font("Arial", 24));

        imageView = new ImageView();
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        VBox centerBox = new VBox(10, letterDisplay, imageView, descriptionDisplay);
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

        for (char c = 'A'; c <= 'Z'; c++) {
            String letter = String.valueOf(c);
            Button btn = new Button(letter);
            btn.setPrefWidth(40);
            btn.setOnAction(e -> letterDisplay.setText(letter));
            buttonPane.getChildren().add(btn);
        }

        return buttonPane;
    }

    private void showLetter(String letter) {
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
        Map<String, AlphabetItem> map = new HashMap<>();

        // For demo, only A–D. You can extend this to full A–Z.
        map.put("A", new AlphabetItem("A", "A for Apple", "src/resources/images/A.jpg", "src/resources/sounds/a.mp3"));
        map.put("B", new AlphabetItem("B", "B for Ball", "src/resources/images/B.jpg", "src/resources/sounds/b.mp3"));
        map.put("C", new AlphabetItem("C", "C for Cat", "src/resources/images/C.jpg", "src/resources/sounds/c.mp3"));
        map.put("D", new AlphabetItem("D", "D for Dog", "src/resources/images/D.jpg", "src/resources/sounds/d.mp3"));

        // Add more...
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
