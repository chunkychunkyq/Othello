package com.company.MainGame.GamePlay;

import com.company.Main;

public class OverrideTile {

    public static void overrideTile(int row, int col, char colour){
        Main.mainBoard[row][col] = colour;
    }

    public static void resetBoard(){
        Main.turnNum = 0;
        for(int rows = 0; rows < 8; rows++){
            for(int cols = 0; cols < 8; cols++){
                Main.mainBoard[rows][cols] = '*';
            }
        }

        overrideTile(3, 3, 'B');
        overrideTile(3, 4, 'W');
        overrideTile(4, 4, 'B');
        overrideTile(4, 3, 'W');
    }
}
