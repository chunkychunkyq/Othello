package com.company.MainGame.Graphics;

import com.company.MainGame.ButtonControls.ButtonClick;

import javafx.scene.control.Button;

public class CreateButton {

    public static Button createButton(int id){
        Button button = new Button();
        button.setPrefSize(45, 45);
        button.setStyle("-fx-background-color: #009442");

        button.setOnMouseDragOver(e -> {
            button.setStyle("-fx-background-color: #00CC58");
        });

        button.setOnAction(e -> ButtonClick.ButtonClick(id));

        return button;
    }
}
