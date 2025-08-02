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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AlphabetViewer {
    private final BorderPane root;
    private final Text letterDisplay;
    private final Text descriptionDisplay;
    private final ImageView imageView;

    private final Map<String, AlphabetItem> alphabetMap;

    public AlphabetViewer(Runnable onBack) {
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


        letterDisplay = new Text("A");
        letterDisplay.setFont(Font.font("Arial", 350));

        descriptionDisplay = new Text("A for Apple");
        descriptionDisplay.setFont(Font.font("Arial", 34));

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

        for (char c = 'A'; c <= 'Z'; c++) {
            String letter = String.valueOf(c);
            Button btn = new Button(letter);
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
        Map<String, AlphabetItem> map = new HashMap<>();

        map.put("A", new AlphabetItem("A", "A for Apple", "src/main/resources/images/A.png", "src/main/resources/sounds/a.mp3"));
        map.put("B", new AlphabetItem("B", "B for Ball", "src/main/resources/images/B.png", "src/main/resources/sounds/b.mp3"));
        map.put("C", new AlphabetItem("C", "C for Cat", "src/main/resources/images/C.png", "src/main/resources/sounds/c.mp3"));
        map.put("D", new AlphabetItem("D", "D for Dog", "src/main/resources/images/D.png", "src/main/resources/sounds/d.mp3"));
        map.put("E", new AlphabetItem("E", "E for Elephant", "src/main/resources/images/E.png", "src/main/resources/sounds/e.mp3"));
        map.put("F", new AlphabetItem("F", "F for Fish", "src/main/resources/images/F.png", "src/main/resources/sounds/f.mp3"));
        map.put("G", new AlphabetItem("G", "G for Goat", "src/main/resources/images/G.png", "src/main/resources/sounds/g.mp3"));
        map.put("H", new AlphabetItem("H", "H for Hat", "src/main/resources/images/H.png", "src/main/resources/sounds/h.mp3"));
        map.put("I", new AlphabetItem("I", "I for Igloo", "src/main/resources/images/I.png", "src/main/resources/sounds/i.mp3"));
        map.put("J", new AlphabetItem("J", "J for Jar", "src/main/resources/images/J.png", "src/main/resources/sounds/j.mp3"));
        map.put("K", new AlphabetItem("K", "K for Kite", "src/main/resources/images/K.png", "src/resources/sounds/k.mp3"));
        map.put("L", new AlphabetItem("L", "L for Lamp", "src/main/resources/images/L.png", "src/main/resources/sounds/l.mp3"));
        map.put("M", new AlphabetItem("M", "M for Moon", "src/main/resources/images/M.png", "src/main/resources/sounds/m.mp3"));
        map.put("N", new AlphabetItem("N", "N for Nest", "src/main/resources/images/N.png", "src/main/resources/sounds/n.mp3"));
        map.put("O", new AlphabetItem("O", "O for Orange", "src/main/resources/images/O.png", "src/main/resources/sounds/o.mp3"));
        map.put("P", new AlphabetItem("P", "P for Peacock", "src/main/resources/images/P_cok.png", "src/main/resources/sounds/p.mp3"));
        map.put("Q", new AlphabetItem("Q", "Q for Quail", "src/main/resources/images/Q.png", "src/main/resources/sounds/q.mp3"));
        map.put("R", new AlphabetItem("R", "R for Rabbit", "src/main/resources/images/R.png", "src/main/resources/sounds/r.mp3"));
        map.put("S", new AlphabetItem("S", "S for Sunflower", "src/main/resources/images/S.png", "src/main/resources/sounds/s.mp3"));
        map.put("T", new AlphabetItem("T", "T for Tree", "src/main/resources/images/T.png", "src/main/resources/sounds/t.mp3"));
        map.put("U", new AlphabetItem("U", "U for Umbrella", "src/main/resources/images/U.png", "src/main/resources/sounds/u.mp3"));
        map.put("V", new AlphabetItem("V", "V for Vase", "src/main/resources/images/V.png", "src/main/resources/sounds/v.mp3"));
        map.put("W", new AlphabetItem("W", "W for Watermelon", "src/main/resources/images/W.png", "src/main/resources/sounds/w.mp3"));
        map.put("X", new AlphabetItem("X", "X for Xylophone", "src/main/resources/images/X.png", "src/main/resources/sounds/x.mp3"));
        map.put("Y", new AlphabetItem("Y", "Y for Yolk", "src/main/resources/images/Y.png", "src/main/resources/sounds/y.mp3"));
        map.put("Z", new AlphabetItem("Z", "Z for Zebra", "src/main/resources/images/Z.png", "src/main/resources/sounds/z.mp3"));

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
