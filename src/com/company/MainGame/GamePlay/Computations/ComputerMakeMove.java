package com.company.MainGame.GamePlay.Computations;

import com.company.Main;
import com.company.MainGame.GamePlay.UpdateDisplay;
import com.company.MainGame.Graphics.MessageBoxes.DisplayMessage;

import java.util.ArrayList;

public class ComputerMakeMove {

    public static void makeMoveEasy(){
        int numLegal = 0;
        ArrayList<Integer> xPos = new ArrayList<>();
        ArrayList<Integer> yPos = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(IsMoveValid.isMoveValid(i, j, 'W') && Main.mainBoard[i][j] == '*'){
                    numLegal++;
                    xPos.add(i);
                    yPos.add(j);
                }
            }
        }
        if(numLegal != 0){
            int index = (int)(Math.random()*(numLegal-1) + 1);
            if(numLegal == 1){
                index = 0;
            }
            DisplayMessage.displayMessage("", "Nice Move!");
            MakeMove.makeMove(xPos.get(index), yPos.get(index), 'W');
        }
        try{
            Thread.sleep(800);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        Main.turnNum++;
        UpdateDisplay.updateDisplay();

        int oppCount = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(IsMoveValid.isMoveValid(i, j, 'B')){
                    oppCount ++;
                }
            }
        }if(oppCount == 0){
            Main.turnNum++;
            makeMoveEasy();
        }
    }

    public static void makeMoveHard(){
        int numLegal = 0;

        ArrayList<Character> placehold = new ArrayList<>();   // placehold is a memory board for when the algorithm runs tests
        ArrayList<Integer> xPos = new ArrayList<>();       // represents x coordinates of all legal moves
        ArrayList<Integer> yPos = new ArrayList<>();       // y of all legal moves
        ArrayList<Integer> numFlip = new ArrayList<>();    // number of tiles flipped for all legal moves
        ArrayList<Integer> xPos1 = new ArrayList<>();      // x coordinates of all legal moves which do not allow opponent to place tile in corner on next move
        ArrayList<Integer> yPos1 = new ArrayList<>();      // y of all legal moves which do not allow corner
        ArrayList<Integer> numFlip1 = new ArrayList<>();   // number of tiles flipped for all that do not allow corner

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                placehold.add(Main.mainBoard[i][j]); } }      // duplicates mainBoard at the beginning of the turn


        if(IsMoveValid.isMoveValid(0, 0, 'W') && Main.mainBoard[0][0] == '*'){     // if top left corner is legal, take it
            MakeMove.makeMove(0, 0, 'W');
            DisplayMessage.displayMessage("", "NICE MOVE!");

        }else if(IsMoveValid.isMoveValid(0, 7, 'W') && Main.mainBoard[0][7] == '*'){  // if top right corner is legal, take it
            MakeMove.makeMove(0, 7, 'W');
            DisplayMessage.displayMessage("", "NICE MOVE!");

        }else if(IsMoveValid.isMoveValid(7, 0, 'W') && Main.mainBoard[7][0] == '*'){  // if bottom left corner is legal, take it
            MakeMove.makeMove(7, 0, 'W');
            DisplayMessage.displayMessage("", "NICE MOVE!");

        }else if(IsMoveValid.isMoveValid(7, 7, 'W') && Main.mainBoard[7][7] == '*'){  // if bottom right corner is legal, take it
            MakeMove.makeMove(7, 7, 'W');
            DisplayMessage.displayMessage("", "NICE MOVE!");

        }else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(IsMoveValid.isMoveValid(i, j, 'W') && Main.mainBoard[i][j] == '*'){    // for each tile, if legal, add to xPos, yPos and numFlip
                        numLegal++;    // keeps track of number of legal moves
                        xPos.add(i);
                        yPos.add(j);
                        numFlip.add(NumTilesToFlip.numTilesToFlip(i, j, 'W'));
                    }
                }
            }
            if(numLegal != 0){
                for(int i = 0; i < numLegal; i++){
                    MakeMove.makeMove(xPos.get(i), yPos.get(i), 'W');      // Don't allow opponent to take corner
                    if(!(IsMoveValid.isMoveValid(0, 0, 'B') && Main.mainBoard[0][0] == '*') &&
                            !(IsMoveValid.isMoveValid(0, 7, 'B') && Main.mainBoard[0][7] == '*')
                            && !(IsMoveValid.isMoveValid(7, 0, 'B') && Main.mainBoard[7][0] == '*')
                            && !(IsMoveValid.isMoveValid(7, 7, 'B') && Main.mainBoard[7][7] == '*')){
                        xPos1.add(xPos.get(i));
                        yPos1.add(yPos.get(i));
                        numFlip1.add(numFlip.get(i));
                    }


                    for(int j = 0; j < 8; j++){
                        for(int k = 0; k < 8; k++){
                            Main.mainBoard[j][k] = placehold.get(8 * j + k);
                        }
                    }
                }

                if(xPos1.size() != 0){
                    for(int i = 19; i > 0; i--){
                        if(numFlip1.contains(i)){
                            DisplayMessage.displayMessage("", "NICE MOVE!");
                            MakeMove.makeMove(xPos1.get(numFlip1.indexOf(i)), yPos1.get(numFlip1.indexOf(i)), 'W');
                            break;
                        }
                    }
                }else{     // if there are no moves which do not allow opponent to take corners
                    for(int i = 19; i > 0; i--){   // find the moe which flips the most based on the original xPos, yPos list
                        if(numFlip.contains(i)){
                            DisplayMessage.displayMessage("", "NICE MOVE!");
                            MakeMove.makeMove(xPos.get(numFlip.indexOf(i)), yPos.get(numFlip.indexOf(i)), 'W');
                            break;
                        }
                    }
                }
            }
            try {
                Thread.sleep(800);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Main.turnNum++;
        UpdateDisplay.updateDisplay();

        int oppCount = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(IsMoveValid.isMoveValid(i, j, 'B')){
                    oppCount ++; } }
        }if(oppCount == 0){
            Main.turnNum++;
            makeMoveHard();
        }
    }
}
