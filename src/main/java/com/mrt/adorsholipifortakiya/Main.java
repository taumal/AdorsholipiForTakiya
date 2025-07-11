package com.mrt.adorsholipifortakiya;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    private static Stage primaryStage;
    private static Scene mainScene;

    public static void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Adorsholipi for Takiya");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Parent root = fxmlLoader.load();
        mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static Scene getScene() {
        return mainScene;
    }
}
