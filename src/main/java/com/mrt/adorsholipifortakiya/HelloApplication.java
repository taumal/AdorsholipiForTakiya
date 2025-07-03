package com.mrt.adorsholipifortakiya;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.KeyCode;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();

        AlphabetViewer viewer = new AlphabetViewer();
        Scene scene = new Scene(viewer.getView(), 800, 600);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                stage.close();
                return;
            }

            String keyName = event.getCode().toString();
            if (keyName.length() == 1 && Character.isLetter(keyName.charAt(0))) {
                viewer.showLetter(keyName);
            }
        });

        stage.setTitle("Alphabet Learner");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}