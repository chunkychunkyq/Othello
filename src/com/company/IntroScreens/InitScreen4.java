package com.company.IntroScreens;

import com.company.MainGame.Graphics.CreateButton;
import com.company.MainGame.Graphics.MessageBoxes.DisplayMessage;
import com.company.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import java.util.ArrayList;

public class InitScreen4 {

    public static ArrayList<Button> buttonList = new ArrayList<>();
    public static Label blackScore, whiteScore;
    public static HBox scoreBoard;
    public static ImageView im1, im2;

    public static Scene initScreen4(){
        Image image = new Image("/Images/board.gif");   // The board is an image created in paint
        ImageView iv1 = new ImageView(image);
        iv1.setFitHeight(555);                      // Initializing the size of the board to 560x560
        iv1.setPreserveRatio(true);

        for(int i = 1; i <= 64; i++){
            buttonList.add(CreateButton.createButton(i));
        }

        VBox introv = new VBox();                            // The bottom layer of the game scene is the board, layout
        introv.getChildren().addAll(iv1);
        introv.setAlignment(Pos.TOP_CENTER);
        introv.setPadding(new Insets(5));

        Image blackTurn = new Image("/Images/blackTurn.png");    // The next layer is the scoreboard display
        Image whiteTurn = new Image("/Images/whiteTurn.png");    // There is a separate image representing whose turn it is

        im1 = new ImageView(blackTurn);   // bt is the image if it is black's turn
        im1.setFitHeight(120);
        im1.setPreserveRatio(true);
        im2 = new ImageView(whiteTurn);   // wt is the image if it is white's turn
        im2.setFitHeight(120);
        im2.setPreserveRatio(true);

        System.out.println(Main.blackCount);



        blackScore = new Label(Integer.toString(Main.blackCount));
        blackScore.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 40));

        whiteScore = new Label(Integer.toString(Main.whiteCount));
        whiteScore.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 40));

        scoreBoard = new HBox(5);
        scoreBoard.getChildren().addAll(blackScore, im1, whiteScore);
        scoreBoard.setAlignment(Pos.BOTTOM_CENTER);
        //scoreBoard.setPadding(new Insets(5));

        HBox h1 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h1.getChildren().add(buttonList.get(i));
        }
        h1.setAlignment(Pos.CENTER);

        HBox h2 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h2.getChildren().add(buttonList.get(i + 8));
        }
        h2.setAlignment(Pos.CENTER);

        HBox h3 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h3.getChildren().add(buttonList.get(i + 16));
        }
        h3.setAlignment(Pos.CENTER);

        HBox h4 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h4.getChildren().add(buttonList.get(i + 24));
        }
        h4.setAlignment(Pos.CENTER);

        HBox h5 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h5.getChildren().add(buttonList.get(i + 32));
        }
        h5.setAlignment(Pos.CENTER);

        HBox h6 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h6.getChildren().add(buttonList.get(i + 40));
        }
        h6.setAlignment(Pos.CENTER);

        HBox h7 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h7.getChildren().add(buttonList.get(i + 48));
        }
        h7.setAlignment(Pos.CENTER);

        HBox h8 = new HBox(9);
        for(int i = 0; i < 8; i++){
            h8.getChildren().add(buttonList.get(i + 56));
        }
        h8.setAlignment(Pos.CENTER);

        VBox v1 = new VBox(9);
        v1.getChildren().addAll(h1, h2, h3, h4, h5, h6, h7, h8);
        v1.setAlignment(Pos.TOP_CENTER);
        v1.setPadding(new Insets(10));

        Button quit = new Button("Quit");
        quit.setPrefSize(70, 30);
        quit.setOnAction(e -> {
            DisplayMessage.quitMessage();
            if(DisplayMessage.result){
                Main.setScene(InitScreen1.initScene1());
                //reset();
            }
        });

        VBox quitlayout = new VBox();
        quitlayout.getChildren().add(quit);
        quitlayout.setAlignment(Pos.BOTTOM_RIGHT);
        quitlayout.setPadding(new Insets(5));

        VBox button = new VBox(0);
        button.getChildren().addAll(v1, quitlayout);

        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(scoreBoard, introv, button);
        stack4.setAlignment(Pos.CENTER);

        Scene scene = new Scene(stack4, 700,700);

        return scene;
    }
}
