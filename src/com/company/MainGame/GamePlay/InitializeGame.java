package com.company.MainGame.GamePlay;
import com.company.Main;

public class InitializeGame {

    public static void initializeGame(){
        createBoard();
        UpdateDisplay.updateDisplay();
    }

    public static void createBoard(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Main.mainBoard[i][j] = '*';
            }
        }
        OverrideTile.overrideTile(3, 3, 'B');
        OverrideTile.overrideTile(3, 4, 'W');
        OverrideTile.overrideTile(4, 3, 'W');
        OverrideTile.overrideTile(4, 4, 'B');
    }
}
