package com.company.MainGame.ButtonControls;

import com.company.Main;
import com.company.MainGame.GamePlay.Computations.IsGameOver;
import com.company.MainGame.GamePlay.Computations.IsMoveValid;
import com.company.MainGame.GamePlay.Computations.MakeMove;
import com.company.MainGame.GamePlay.Computations.ComputerMakeMove;
import com.company.MainGame.GamePlay.UpdateDisplay;
import com.company.MainGame.Graphics.MessageBoxes.DisplayMessage;


public class ButtonClick {

    public static void ButtonClick(int buttonId){

        int row = Math.floorDiv(buttonId - 1, 8);
        int col = ((buttonId - 1) % 8);
        char colour;

        if(Main.turnNum % 2 == 0){
            colour = 'B';
        }else{
            colour = 'W';
        }

        if(Main.mainBoard[row][col] == '*' && IsMoveValid.isMoveValid(row, col, colour)) {
            MakeMove.makeMove(row, col, colour);
            Main.turnNum ++;
            UpdateDisplay.updateDisplay();
            if(Main.computerDifficulty == 1){
                ComputerMakeMove.makeMoveEasy();
            }else if(Main.computerDifficulty == 2){
                ComputerMakeMove.makeMoveHard();
            }
        }else{
            DisplayMessage.displayMessage("", "Not a legal move! Please try again");
        }
        IsGameOver.isGameOver();
    }
}
