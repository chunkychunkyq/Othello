package com.company.MainGame.Graphics.MessageBoxes;

import com.company.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DisplayMessage {

    public static boolean result;

    public static void displayMessage(String title, String message){
        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        boxWindow.setTitle(title);
        Label label = new Label(message);                           // Parameters determine the message it reads
        Button close = new Button("OK");                       // The button closes the window
        close.setOnAction(e -> boxWindow.close());

        VBox layout = new VBox(30);                         // Label and button are in a VBox
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        boxWindow.setScene(scene);
        boxWindow.showAndWait();
    }

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

    public static void difficultySelect(){
        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please choose a difficulty");

        Button affirm = new Button("EASY");                    // 2 buttons, one for easy
        Button reject = new Button("HARD");                    // one for hard

        affirm.setOnAction(e -> {
            Main.computerDifficulty = 1;                                           // computer = 1 represents easy mode
            boxWindow.close(); });
        reject.setOnAction(e -> {
            Main.computerDifficulty = 2;                                           // computer = 2 represents hard mode
            boxWindow.close(); });

        VBox layout = new VBox(30);
        layout.getChildren().addAll(label, affirm, reject);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        boxWindow.setScene(scene);
        boxWindow.showAndWait();
    }

}

