package de.knee.backup.ui;/**
 * Created by Nils on 10.02.2017.
 * Package: de.knee.backup.ui
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BackupUi extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scene.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaxHeight(primaryStage.getHeight());
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMaxWidth(primaryStage.getWidth());
        primaryStage.setMinWidth(primaryStage.getWidth());
    }
}
