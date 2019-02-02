package com.company.MainGame.GamePlay.Computations;

import com.company.Main;

public class NumTilesToFlip {
    
    public static int numTilesToFlip(int initRow, int initCol, char colour){
        boolean loopBreak = false;   
        int count = 0;
        int col = initCol;
        int row = initRow;
        int total = 0;         
        
// LEFT

        while (!loopBreak) {
            if (col != 0) {    
                col--;
                if (Main.mainBoard[row][col] == colour) { 
                    loopBreak = true; 
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow][initCol - i] == colour || Main.mainBoard[initRow][initCol - i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// RIGHT

        while (!loopBreak) {
            if (col != 7) {
                col++;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow][initCol + i] == colour || Main.mainBoard[initRow][initCol + i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// UP

        while (!loopBreak) {
            if (row != 0) {
                row --;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow - i][initCol] == colour || Main.mainBoard[initRow - i][initCol] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// DOWN

        while (!loopBreak) {
            if (row != 7) {
                row ++;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow + i][initCol] == colour || Main.mainBoard[initRow + i][initCol] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// UP LEFT

        while (!loopBreak) {
            if (row != 0 && col != 0) {
                row --;
                col --;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }else{
                    count++;
                }
            }else{
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow - i][initCol - i] == colour || Main.mainBoard[initRow - i][initCol - i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// UP RIGHT

        while (!loopBreak) {
            if (row != 0 && col != 7) {
                row --;
                col ++;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }
                else {
                    count++;
                }
            }
            else {
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow - i][initCol + i] == colour || Main.mainBoard[initRow - i][initCol + i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// NUM DOWN LEFT

        while (!loopBreak) {
            if (row != 7 && col != 0) {
                row ++;
                col --;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }
                else {
                    count++;
                }
            }
            else {
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow + i][initCol - i] == colour || Main.mainBoard[initRow + i][initCol - i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// NUM DOWN RIGHT

        while (!loopBreak) {
            if (row != 7 && col != 7) {
                row ++;
                col ++;
                if (Main.mainBoard[row][col] == colour) {
                    loopBreak = true;
                }
                else {
                    count++;
                }
            }
            else {
                loopBreak = true;
                count = 0;
            }
        }
        for (int i = 1; i <= count; i++) {
            if (Main.mainBoard[initRow + i][initCol + i] == colour || Main.mainBoard[initRow + i][initCol + i] == '*') {
                loopBreak = false;
            }
        }
        if (loopBreak) {
            total += count;
        }
        return total;
    }
}
