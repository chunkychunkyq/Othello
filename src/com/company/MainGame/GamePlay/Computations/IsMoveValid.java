package com.company.MainGame.GamePlay.Computations;

import com.company.Main;

public class IsMoveValid {

    public static boolean isMoveValid(int initRow, int initCol, char colour){

        boolean loopBreak = false;
        boolean mainReturn = false;
        int count = 0;
        int col = initCol;
        int row = initRow;

//LEFT

        while(!loopBreak){
            if(col != 0){
                col --;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }

        if (count != 0) {
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow][initCol - i] == colour || Main.mainBoard[initRow][initCol - i] == '*') {
                    loopBreak = false;
                }
            }
            if(loopBreak){
                mainReturn = true;
            }
        }

        loopBreak= false;
        count = 0;
        col = initCol;
        row = initRow;

//RIGHT

        while(!loopBreak){
            if(col != 7){
                col ++;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }
                else{
                    count++;
                }
            }else {
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0) {
            for (int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow][initCol + i] == colour || Main.mainBoard[initRow][initCol + i] == '*') {
                    loopBreak = false;
                }
            }
            if (loopBreak) {
                mainReturn = true;
            }
        }

// UP
        loopBreak= false;
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(row != 0){
                row --;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }
                else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0) {
            for (int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow - i][initCol] == colour || Main.mainBoard[initRow - i][initCol] == '*') {
                    loopBreak = false;
                }
            }
            if (loopBreak) { mainReturn = true;
            }
        }

// DOWN
        loopBreak= false;
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(row != 7){
                row ++;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }else{
                    count++;
                }

            }else{
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow + i][initCol] == colour || Main.mainBoard[initRow + i][initCol] == '*') {
                    loopBreak = false;
                }
            }
            if(loopBreak){
                mainReturn = true;
            }
        }

// UP LEFT

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 0 && row != 0){
                col --;
                row --;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow - i][initCol - i] == colour || Main.mainBoard[initRow - i][initCol - i] == '*') {  // initRow and initCol both - i
                    loopBreak = false;
                }
            }
            if(loopBreak){
                mainReturn = true;
            }
        }

// UP RIGHT

        loopBreak= false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 7 && row != 0){
                col ++;
                row --;
                if(Main.mainBoard[row][col] == colour){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow - i][initCol + i] == colour || Main.mainBoard[initRow - i][initCol + i] == '*') {
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }

// DOWN LEFT

        loopBreak= false;
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 0 && row != 7){
                col --;
                row ++;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }
                else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow + i][initCol - i] == colour || Main.mainBoard[initRow + i][initCol - i] == '*') {
                    loopBreak = false;
                }
            }
            if(loopBreak){ mainReturn = true;
            }
        }

// DOWN RIGHT

        loopBreak= false;
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 7 && row != 7){
                col ++;
                row ++;
                if(Main.mainBoard[row][col] == colour){
                    loopBreak = true;
                }
                else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (Main.mainBoard[initRow + i][initCol + i] == colour || Main.mainBoard[initRow + i][initCol + i] == '*') {
                    loopBreak = false;
                }
            }
            if(loopBreak){
                mainReturn = true;
            }
        }
        return mainReturn;
    }
}
