package com.company;

import com.company.IntroScreens.InitScreen1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        Scene init1 = InitScreen1.initScene1();

        window.setScene(init1);
        window.show();
    }

    public static void setScene(Scene scene){
        window.setScene(scene);
    }

    public static void close(){
        window.close();
    }
}
