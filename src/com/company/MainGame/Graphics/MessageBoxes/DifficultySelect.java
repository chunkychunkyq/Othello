package com.company.MainGame.Graphics.MessageBoxes;

import com.company.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DifficultySelect {

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
