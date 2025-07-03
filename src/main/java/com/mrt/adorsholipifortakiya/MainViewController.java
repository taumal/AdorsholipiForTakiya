package com.mrt.adorsholipifortakiya;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {

    @FXML
    private void handleAlphabetButtonAction() {
        Stage stage = (Stage) Main.getScene().getWindow();
        AlphabetViewer viewer = new AlphabetViewer();
        Scene scene = new Scene(viewer.getView());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    Main.showMainMenu();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }

            String keyName = event.getCode().toString();
            if (keyName.length() == 1 && Character.isLetter(keyName.charAt(0))) {
                viewer.showLetter(keyName);
            }
        });

        stage.setScene(scene);
        stage.setMaximized(true);
    }

    @FXML
    private void handleNumberButtonAction() {
        Stage stage = (Stage) Main.getScene().getWindow();
        NumberViewer viewer = new NumberViewer();
        Scene scene = new Scene(viewer.getView());

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                try {
                    Main.showMainMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

            if (event.getCode().isDigitKey()) {
                viewer.showNumber(event.getText());
            }
        });

        stage.setScene(scene);
        stage.setMaximized(true);
    }

    @FXML
    private void handleExitButtonAction() {
        System.exit(0);
    }
}
