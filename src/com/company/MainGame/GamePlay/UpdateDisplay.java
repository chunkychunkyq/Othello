package com.company.MainGame.GamePlay;

import com.company.Main;
import com.company.IntroScreens.InitScreen4;
import com.company.MainGame.Graphics.GetTileIcon;
import com.sun.org.apache.xml.internal.security.Init;


public class UpdateDisplay {

    public static void updateDisplay(){
        Main.blackCount = 0;
        Main.whiteCount = 0;

        for(int i = 0; i < 64; i++){
            if(Main.mainBoard[Math.floorDiv(i, 8)][i % 8] == 'B'){
                InitScreen4.buttonList.get(i).setGraphic(GetTileIcon.getTileIcon('B'));
                Main.blackCount ++;
            }else if(Main.mainBoard[Math.floorDiv(i, 8)][i % 8] == 'W'){
                InitScreen4.buttonList.get(i).setGraphic(GetTileIcon.getTileIcon('W'));
                Main.whiteCount ++;
            }else{
                InitScreen4.buttonList.get(i).setGraphic(GetTileIcon.getTileIcon('*'));
            }
            InitScreen4.buttonList.get(i).setPrefSize(60, 60);
        }

        InitScreen4.scoreBoard.getChildren().clear();
        InitScreen4.blackScore.setText(Integer.toString(Main.blackCount));
        InitScreen4.whiteScore.setText(Integer.toString(Main.whiteCount));

        if(Main.turnNum % 2 == 0){
            InitScreen4.scoreBoard.getChildren().addAll(InitScreen4.blackScore, InitScreen4.im1, InitScreen4.whiteScore);
        }else{
            InitScreen4.scoreBoard.getChildren().addAll(InitScreen4.blackScore, InitScreen4.im2, InitScreen4.whiteScore);
        }


    }

}


