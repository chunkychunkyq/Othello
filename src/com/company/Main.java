package com.company;

import com.company.IntroScreens.InitScreen1;


import com.company.IntroScreens.InitScreen2;
import com.company.IntroScreens.InitScreen4;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Main extends Application{

    public static Stage window;
    public static char[][] mainBoard = new char[8][8];
    public static int blackCount = 0, whiteCount = 0;
    public static int computerDifficulty, turnNum = 0;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("OTHELLO");



        //PUT EACH STAGE IN ITS OWN CLASS
        //
        //
        //
        //
        //
        //////////////////////////////////

        Scene init1 = InitScreen1.initScene1();
        Scene init2 = InitScreen2.initScene2();



// Initialzing Intro Screen 1:



        window.setScene(init1);   // on run, first board is main screen
        window.show();
    }

    public static void setScene(Scene scene){
        window.setScene(scene);
    }

    public static void close(){
        window.close();
    }

}
