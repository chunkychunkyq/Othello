package com.company.IntroScreens;

import com.company.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InitScreen1 {

    public static Scene initScene1(){
        Image image = new Image("/Images/intro.jpg");  // the intro screen background is an image created in paint.net
        ImageView im = new ImageView(image);
        im.setFitHeight(700);
        im.setPreserveRatio(true);

        Button play = new Button("PLAY");
        play.setPrefSize(150, 40);
        Button instruct = new Button("INSTRUCTIONS");
        instruct.setPrefSize(150, 40);

        play.setOnAction(e -> {
            Scene init2 = InitScreen2.initScene2();
            Main.setScene(init2);
        });
        instruct.setOnAction(e -> {
            Scene init3 = InitScreen3.initScene3();
            Main.setScene(init3);
        });

        VBox vbox = new VBox(15);
        vbox.getChildren().addAll(play, instruct);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(150));

        StackPane stack1 = new StackPane();
        stack1.getChildren().addAll(im, vbox);
        Scene scene = new Scene(stack1, 700, 700);

        return scene;
    }

}
