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

public class InitScreen3 {

    public static Scene initScene3(){

        Button goBack = new Button("BACK");
        goBack.setPrefSize(150, 40);

        Image intro = new Image("/Images/intro.jpg");
        ImageView im1 = new ImageView(intro);
        im1.setFitHeight(700);
        im1.setPreserveRatio(true);

        Image instructionsImage = new Image("/Images/instruct.jpg");
        ImageView im2 = new ImageView(instructionsImage);
        im2.setFitWidth(650);
        im2.setPreserveRatio(true);

        VBox v1 = new VBox();
        v1.getChildren().add(goBack);
        v1.setAlignment(Pos.BOTTOM_RIGHT);
        v1.setPadding(new Insets(30));

        VBox v2 = new VBox();
        v2.getChildren().add(im2);
        v2.setAlignment(Pos.BOTTOM_CENTER);
        v2.setPadding(new Insets(10, 10, 90, 10));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(im1, v2, v1);

        goBack.setOnAction(e -> {
            Scene init1 = InitScreen1.initScene1();
            Main.setScene(init1);

        });
        Scene scene = new Scene(stack, 700, 700);

        return scene;
    }
}
