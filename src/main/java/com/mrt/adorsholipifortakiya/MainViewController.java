package com.mrt.adorsholipifortakiya;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.input.KeyCode;
import javafx.application.Platform;

public class MainViewController {

    @FXML
    private StackPane contentPane;

    @FXML
    private HBox buttonContainer;

    @FXML
    private void handleAlphabetButtonAction() {
        buttonContainer.setVisible(false);
        buttonContainer.setManaged(false);

        AlphabetViewer viewer = new AlphabetViewer(() -> {
            buttonContainer.setVisible(true);
            buttonContainer.setManaged(true);
        });
        contentPane.getChildren().setAll(viewer.getView());

        contentPane.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                contentPane.getChildren().clear(); // Clear the viewer when escaping
                contentPane.getScene().setOnKeyPressed(null); // Remove key handler
                buttonContainer.setVisible(true);
                buttonContainer.setManaged(true);
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
        buttonContainer.setVisible(false);
        buttonContainer.setManaged(false);

        NumberViewer viewer = new NumberViewer(() -> {
            buttonContainer.setVisible(true);
            buttonContainer.setManaged(true);
        });
        contentPane.getChildren().setAll(viewer.getView());

        contentPane.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                contentPane.getChildren().clear(); // Clear the viewer when escaping
                contentPane.getScene().setOnKeyPressed(null); // Remove key handler
                buttonContainer.setVisible(true);
                buttonContainer.setManaged(true);
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
