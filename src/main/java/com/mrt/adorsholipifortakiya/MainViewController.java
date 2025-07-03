package com.mrt.adorsholipifortakiya;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class MainViewController {

    @FXML
    private StackPane contentPane;

    @FXML
    private void handleAlphabetButtonAction() {
        AlphabetViewer viewer = new AlphabetViewer();
        contentPane.getChildren().setAll(viewer.getView());

        contentPane.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                contentPane.getChildren().clear(); // Clear the viewer when escaping
                contentPane.getScene().setOnKeyPressed(null); // Remove key handler
                return;
            }

            String keyName = event.getCode().toString();
            if (keyName.length() == 1 && Character.isLetter(keyName.charAt(0))) {
                viewer.showLetter(keyName);
            }
        });
    }

    @FXML
    private void handleNumberButtonAction() {
        NumberViewer viewer = new NumberViewer();
        contentPane.getChildren().setAll(viewer.getView());

        contentPane.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                contentPane.getChildren().clear(); // Clear the viewer when escaping
                contentPane.getScene().setOnKeyPressed(null); // Remove key handler
                return;
            }

            if (event.getCode().isDigitKey()) {
                viewer.showNumber(event.getText());
            }
        });
    }

    @FXML
    private void handleExitButtonAction() {
        Platform.exit();
    }
}
