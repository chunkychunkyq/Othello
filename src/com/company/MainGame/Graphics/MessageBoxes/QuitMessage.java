package com.company.MainGame.Graphics.MessageBoxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuitMessage {

    public static boolean result;

    public static boolean quitMessage(){

        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        boxWindow.setTitle("");
        Label label = new Label("Are you sure you want to quit?");

        Button affirm = new Button("Yes");
        Button reject = new Button("Cancel");

        affirm.setOnAction(e -> {
            result = true;                                 // yes sets the result boolean to true
            boxWindow.close(); });
        reject.setOnAction(e -> {
            result = false;                                // cancel sets the result boolean to false
            boxWindow.close(); });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, affirm, reject);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 200, 200, Color.DARKMAGENTA);
        boxWindow.setScene(scene);
        boxWindow.showAndWait();

        return result;
    }
}
