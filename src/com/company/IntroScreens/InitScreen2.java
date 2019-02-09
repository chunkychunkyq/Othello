package com.company.IntroScreens;

import com.company.Main;
import com.company.MainGame.Graphics.MessageBoxes.DisplayMessage;
import com.company.MainGame.GamePlay.InitializeGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InitScreen2 {



    public static Scene initScene2(){
        Button twoP = new Button("Vs. Friend");
        twoP.setPrefSize(150, 40);
        Button cpu = new Button("Vs. Computer");
        cpu.setPrefSize(150, 40);
        Button goBack = new Button("Back");
        goBack.setPrefSize(150, 40);

        Image image = new Image("/Images/intro.jpg");
        ImageView im = new ImageView(image);
        im.setFitHeight(700);
        im.setPreserveRatio(true);

        VBox vbox = new VBox();
        vbox.getChildren().add(goBack);
        vbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.setPadding(new Insets(30));

        VBox vbox1 = new VBox(15);
        vbox1.getChildren().addAll(twoP, cpu);
        vbox1.setAlignment(Pos.BOTTOM_CENTER);
        vbox1.setPadding(new Insets(30));

        VBox vMain = new VBox(20);
        vMain.getChildren().addAll(vbox1, vbox);
        vMain.setAlignment(Pos.BOTTOM_CENTER);

        StackPane stack = new StackPane(im, vMain);
        Scene scene = new Scene(stack, 700, 700);

        twoP.setOnAction(e -> {
            Main.computerDifficulty = 0;
            Main.setScene(InitScreen4.initScreen4());
            InitializeGame.initializeGame();
        });
        cpu.setOnAction(e -> {
            DisplayMessage.difficultySelect();
            Main.setScene(InitScreen4.initScreen4());
            InitializeGame.initializeGame();
        });


        goBack.setOnAction(e -> {
            Scene init1 = InitScreen1.initScene1();
            Main.setScene(init1);
        });

        return scene;

    }

}
