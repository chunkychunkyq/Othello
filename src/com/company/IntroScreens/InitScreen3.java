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

        Button goBack = new Button("BACK");              // only one button on the instructions screen, can only go back to main screen
        goBack.setPrefSize(150, 40);

        Image intro = new Image("/Images/intro.jpg");
        ImageView int3 = new ImageView(intro);    // same background
        int3.setFitHeight(700);
        int3.setPreserveRatio(true);

        Image instructionsImage = new Image("/Images/instruct.jpg");   // instructions are a text file imported as an image
        ImageView int4 = new ImageView(instructionsImage);
        int4.setFitWidth(650);
        int4.setPreserveRatio(true);

        VBox introv3 = new VBox();                             // introV3 is the VBox layout for the back button, again it has a different allignment
        introv3.getChildren().add(goBack);
        introv3.setAlignment(Pos.BOTTOM_RIGHT);
        introv3.setPadding(new Insets(30));

        VBox introv4 = new VBox();                             // introV4 is the VBox layout for the intsructions image
        introv4.getChildren().add(int4);
        introv4.setAlignment(Pos.BOTTOM_CENTER);
        introv4.setPadding(new Insets(10, 10, 90, 10));

        StackPane stack3 = new StackPane();                    // Stack3 combines the background, instructions image and back button
        stack3.getChildren().addAll(int3, introv4, introv3);

        goBack.setOnAction(e -> {                // goBack button sets screen to main screen
            Scene init1 = InitScreen1.initScene1();
            Main.setScene(init1);

        });
        Scene scene = new Scene(stack3, 700, 700);


        return scene;
    }
}
