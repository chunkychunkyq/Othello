package com.company.MainGame.GamePlay.Computations;

import com.company.Main;
import com.company.MainGame.Graphics.MessageBoxes.DisplayMessage;

public class IsGameOver {
    
    public static void isGameOver(){
            
        int black = 0;
        int white = 0;

        int numMovesB = 0;
        int numMovesW = 0;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(IsMoveValid.isMoveValid(i, j, 'B') && Main.mainBoard[i][j] == '*'){
                    numMovesB ++;
                }
                if(IsMoveValid.isMoveValid(i, j, 'W') && Main.mainBoard[i][j] == '*'){
                    numMovesW ++;
                }
            }
        }

        if(Main.turnNum == 60 || numMovesB + numMovesW == 0){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(Main.mainBoard[i][j] == 'B'){
                        black++;
                    }else{
                        white++;
                    }
                }
            }if(black > white){
                DisplayMessage.displayMessage("", "BLACK WINS!");
                Main.close();
            }else if(white > black){
                DisplayMessage.displayMessage("", "WHITE WINS!");
                Main.close();
            }else{
                DisplayMessage.displayMessage("", "TIE GAME!");
                Main.close();
            }
        } 
    }
}
