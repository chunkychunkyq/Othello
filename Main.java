import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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

import javax.xml.bind.SchemaOutputResolver;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.WeakHashMap;


public class Main extends Application {

    static Stage window;
    public static String[][] mainBoard;

    Label l1;
    Scene init1, init2, instructions, gameBoard;
    Button play, instruct, twoP, cpu, goBack, goBack2, goBack3;

    static Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but10, but11, but12, but13, but14, but15, but16,
    but17, but18, but19, but20, but21, but22, but23, but24, but25, but26, but27, but28, but29, but30, but31, but32, but33,
    but34, but35, but36, but37, but38, but39, but40, but41, but42, but43, but44, but45, but46, but47, but48, but49, but50,
    but51, but52, but53, but54, but55, but56, but57, but58, but59, but60, but61, but62, but63, but64;
    static HBox h1, h2, h3, h4, h5, h6, h7, h8, scoreBoard;
    static VBox v1;
    static ImageView bt, wt;
    static Label blackScore, whiteScore;
    static int turnNum, blackCount, whiteCount, blankCount, computer;
    static boolean result;
    static String player;
    static ArrayList<ImageView> blackChips = new ArrayList<>();
    static ArrayList<ImageView> whiteChips = new ArrayList<>();
    static ArrayList<ImageView> blankChips = new ArrayList<>();
    static ArrayList<Button> buttonList = new ArrayList<>();


    public Main(){                                   // Creating the board array
        mainBoard = new String[8][8];
        for(int rows = 0; rows < 8; rows++){         // For each index of the list, set it as '*'
            for(int cols = 0; cols < 8; cols++){
                mainBoard[rows][cols] = "*"; } } }

    public static void displayBox(String title, String message){    // Displaying a message as an alert box
        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        boxWindow.setTitle(title);
        Label label = new Label(message);                           // Parameters determine the message it reads
        Button close = new Button("OK");                       // The button closes the window
        close.setOnAction(e -> boxWindow.close());

        VBox layout = new VBox(30);                         // Label and button are in a VBox
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        boxWindow.setScene(scene);
        boxWindow.showAndWait(); }                                  // Shows and waits for further command

    public static void commandBox(){                                // This method allows the user to select a computer difficulty level
        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please choose a difficulty");

        Button affirm = new Button("EASY");                    // 2 buttons, one for easy
        Button reject = new Button("HARD");                    // one for hard

        affirm.setOnAction(e -> {
            computer = 1;                                           // computer = 1 represents easy mode
            boxWindow.close(); });
        reject.setOnAction(e -> {
            computer = 2;                                           // computer = 2 represents hard mode
            boxWindow.close(); });

        VBox layout = new VBox(30);
        layout.getChildren().addAll(label, affirm, reject);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        boxWindow.setScene(scene);
        boxWindow.showAndWait(); }

    public static boolean quitBox(){                        // Method to confirm that user(s) want to quit game
        Stage boxWindow = new Stage();
        boxWindow.initModality(Modality.APPLICATION_MODAL);
        boxWindow.setTitle("");
        Label label = new Label("Are you sure you want to quit?");

        Button affirm = new Button("Yes");
        Button reject = new Button("Cancel");

        affirm.setOnAction(e -> {
            result = true;                                 // yes sets the result boolean to true
            boxWindow.close(); });
        reject.setOnAction(e -> {
            result = false;                                // cancel sets the result boolean to false
            boxWindow.close(); });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, affirm, reject);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 200, 200, Color.DARKMAGENTA);
        boxWindow.setScene(scene);
        boxWindow.showAndWait();
        return result; }                                   // returns the result boolean


    public static void setTile(int row, int col, String player){
        mainBoard[row][col] = player;
    }  // method to set any tile, used to initiate 4 starting tiles

    public static boolean isValid(int initRow, int initCol, String player){   // This method verifies if a move is legal, given coordinates and player

//LEFT
        boolean loopBreak = false;   // loopbreak is a boolean to count how many tiles between placed tile and next tile of the same colour in any direction
        boolean mainReturn = false;  // mainreturn is what this method returns
        int count = 0;               // A count for the nuber of tiles available in any given direction
        int col = initCol;
        int row = initRow;

        while(!loopBreak){          // This while loop determines how many potential tiles can be flipped
            if(col != 0){           // if col is not 0 (if it is still on the board moving left)
                col --;             // subtract 1 from col (move left 1 space)
                if(mainBoard[row][col] == player){ loopBreak = true; }  // if it is the same as the placed tile, break loop
                else{ count++; }                                        // otherwise add to count
            }else{
                loopBreak = true;    // if col is 0, break loop
                count = 0; } }       // no potential tiles to be flipped, so count is 0
        if (count != 0) {            // if there are potential tiles to be flipped
            for(int i = 1; i <= count; i++) {    // for each potential tile to be flipped
                if (mainBoard[initRow][initCol - i] == player || mainBoard[initRow][initCol - i] == "*") {  // if any potential tile is the same as player or is empty
                    loopBreak = false; } }    // then placing a tile here will not flip any tiles to the left
            if(loopBreak){ mainReturn = true; }}  // if loopBreak were true, this is a legal move, otherwise try next direction

// RIGHT
        loopBreak= false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){  // This is the same as the previous algorithm but for checking to the right
            if(col != 7){   // if col is not 7 (if col is still on the board moving right)
                col ++;     // add 1 to col (move right one space)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else {
                loopBreak = true;
                count = 0; } }
        if(count != 0) {
            for (int i = 1; i <= count; i++) {
                if (mainBoard[initRow][initCol + i] == player || mainBoard[initRow][initCol + i] == "*") {   // Notice [initCol + i] rather than [initCol - i], means it checks potential tiles to the right
                    loopBreak = false; } }
            if (loopBreak) { mainReturn = true; } }  // any direction that makes mainReturn true makes the move legal, once mainReturn is true nothing will make it become false

// UP
        loopBreak= false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(row != 0){   // if row is not 0 (still on the board moving up)
                row --;     // subtract 1 from row (move 1 space up)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0) {
            for (int i = 1; i <= count; i++) {
                if (mainBoard[initRow - i][initCol] == player || mainBoard[initRow - i][initCol] == "*") {  // [initRow - i] and initCol stays the same
                    loopBreak = false; } }
            if (loopBreak) { mainReturn = true; } }

// DOWN
        loopBreak= false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(row != 7){    // if row is not 7 (still on the board moving down)
                row ++;      // add 1 to row (move 1 space down)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (mainBoard[initRow + i][initCol] == player || mainBoard[initRow + i][initCol] == "*") {  //[initRow + i]
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }

// UP LEFT

        loopBreak = false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 0 && row != 0){  // if col and row are both not 0 (still on board moving up and left)
                col --;                // subtract 1 from col (move 1 space left)
                row --;                // subtract 1 from row (move 1 space up)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (mainBoard[initRow - i][initCol - i] == player || mainBoard[initRow - i][initCol - i] == "*") {  // initRow and initCol both - i
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }

// UP RIGHT

        loopBreak= false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 7 && row != 0){   // while col is not 7 and row is not 0 (still on board moving up and right)
                col ++;                 // add 1 to col (move 1 space right)
                row --;                 // subtract 1 from row (move 1 space up)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (mainBoard[initRow - i][initCol + i] == player || mainBoard[initRow - i][initCol + i] == "*") {  // initRow and initCol are now opposite
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }

// DOWN LEFT

        loopBreak= false; // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 0 && row != 7){   // if col is not 0 and row is not 7 (still on board moving down and left)
                col --;                 // subtract 1 from col (move 1 space left)
                row ++;                 // add 1 to row (move 1 space down)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{
                    count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (mainBoard[initRow + i][initCol - i] == player || mainBoard[initRow + i][initCol - i] == "*") {
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }

// DOWN RIGHT
        loopBreak= false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

        while(!loopBreak){
            if(col != 7 && row != 7){   // if col and row are both not 7 (still on board moving right and down)
                col ++;                 // add 1 to col (move 1 space right)
                row ++;                 // add 1 to row (move 1 space down)
                if(mainBoard[row][col] == player){ loopBreak = true; }
                else{ count++; }
            }else{
                loopBreak = true;
                count = 0; } }
        if(count != 0){
            for(int i = 1; i <= count; i++) {
                if (mainBoard[initRow + i][initCol + i] == player || mainBoard[initRow + i][initCol + i] == "*") {
                    loopBreak = false; } }
            if(loopBreak){ mainReturn = true; } }
        return mainReturn; }      // if any direction was true, mainReturn will be true, otherwise false


    public static int numTilesFlipped(int initRow, int initCol, String player) {    // This method determines how many tiles would be flipped by a move, takes in coordinates and player
        boolean loopBreak = false;   // Very similar to the isValid method, returns total number of tiles flipped instead of if it is legal or not
        int count = 0;
        int col = initCol;
        int row = initRow;
        int total = 0;          // total is the cumulative number of tiles which would be flipped

// NUM LEFT

        while (!loopBreak) {
            if (col != 0) {    // if still on board moving left
                col--;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow][initCol - i] == player || mainBoard[initRow][initCol - i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }  // instead of mainReturn = true, adds the count to the total

        loopBreak = false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM RIGHT

        while (!loopBreak) {
            if (col != 7) {    // if still on board moving right
                col++;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow][initCol + i] == player || mainBoard[initRow][initCol + i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;
        count = 0;
        col = initCol;
        row = initRow;

// NUM UP

        while (!loopBreak) {
            if (row != 0) {
                row --;      // if still on board moving up
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow - i][initCol] == player || mainBoard[initRow - i][initCol] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM DOWN

        while (!loopBreak) {
            if (row != 7) {   // if still on board moving down
                row ++;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow + i][initCol] == player || mainBoard[initRow + i][initCol] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;    // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM UP LEFT

        while (!loopBreak) {
            if (row != 0 && col != 0) {     // if still on board moving up and left
                row --;
                col --;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow - i][initCol - i] == player || mainBoard[initRow - i][initCol - i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;       // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM UP RIGHT

        while (!loopBreak) {
            if (row != 0 && col != 7) {    // if still on board moving up and right
                row --;
                col ++;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow - i][initCol + i] == player || mainBoard[initRow - i][initCol + i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM DOWN LEFT

        while (!loopBreak) {
            if (row != 7 && col != 0) {    // if still on board moving down and left
                row ++;
                col --;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow + i][initCol - i] == player || mainBoard[initRow + i][initCol - i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }

        loopBreak = false;    // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// NUM DOWN RIGHT

        while (!loopBreak) {
            if (row != 7 && col != 7) {    // if still on board moving down and right
                row ++;
                col ++;
                if (mainBoard[row][col] == player) { loopBreak = true; }
                else { count++; } }
            else {
                loopBreak = true;
                count = 0; } }
        for (int i = 1; i <= count; i++) {
            if (mainBoard[initRow + i][initCol + i] == player || mainBoard[initRow + i][initCol + i] == "*") {
                loopBreak = false; } }
        if (loopBreak) { total += count; }
        return total; }   // return the total tiles flipped in all directions


    public static void makeMove(int initRow, int initCol, String player){    // method to move tiles, also very similar to isValid and numTilesFlipped
        boolean loopBreak = false;     // but does not return anything, changes the tile values on the mainBoard
        int count = 0;
        int col = initCol;
        int row = initRow;
        mainBoard[row][col] = player;

// Changes left

        while(!loopBreak){
            if(col != 0){     // if still on board moving left
                col --;
                if(mainBoard[row][col] == player){
                    loopBreak = true; }
                else{ count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow][initCol - i] == player || mainBoard[initRow][initCol - i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow][initCol - i] = player; } }  // for each tile, change to player parameter value

        loopBreak = false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes right

        while(!loopBreak){
            if(col != 7){  // if still on board moving right
                col ++;
                if(mainBoard[row][col] == player){
                    loopBreak = true; }
                else{ count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow][initCol + i] == player || mainBoard[initRow][initCol + i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow][initCol + i] = player; } }

        loopBreak = false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes up

        while(!loopBreak){     // if still on board moving up
            if(row != 0){
                row --;
                if(mainBoard[row][col] == player){
                    loopBreak = true; }
                else{ count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow - i][initCol] == player || mainBoard[initRow - i][initCol] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow - i][initCol] = player; } }

        loopBreak = false; // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes down

        while(!loopBreak){    // if still on board moving down
            if(row != 7){
                row ++;
                if(mainBoard[row][col] == player){
                    loopBreak = true;
                }
                else{ count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow + i][initCol] == player || mainBoard[initRow + i][initCol] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow + i][initCol] = player; } }

        loopBreak = false; // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes up and left

        while(!loopBreak){
            if(row != 0 && col != 0){   //if still on board moving up and left
                row --;
                col --;
                if(mainBoard[row][col] == player){
                    loopBreak = true;
                } else { count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow - i][initCol - i] == player || mainBoard[initRow - i][initCol - i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow - i][initCol - i] = player; } }

        loopBreak = false;   // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes up and right

        while(!loopBreak){
            if(row != 0 && col != 7){     // if still on board moving right
                row --;
                col ++;
                if(mainBoard[row][col] == player){
                    loopBreak = true;
                }
                else{ count++; }
            } else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow - i][initCol + i] == player || mainBoard[initRow - i][initCol + i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow - i][initCol + i] = player; } }

        loopBreak = false;     // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes down and left

        while(!loopBreak){
            if(row != 7 && col != 0){    // if still on board moving down and left
                row ++;
                col --;
                if(mainBoard[row][col] == player){
                    loopBreak = true; }
                else{ count++;
                } }else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow + i][initCol - i] == player || mainBoard[initRow + i][initCol - i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow + i][initCol - i] = player; } }

        loopBreak = false;  // reset all vars
        count = 0;
        col = initCol;
        row = initRow;

// Changes down and right

        while(!loopBreak){
            if(row != 7 && col != 7){   // if still on board moving down and right
                row ++;
                col ++;
                if(mainBoard[row][col] == player){
                    loopBreak = true; }
                else{ count++;
                } }else{
                loopBreak = true;
                count = 0; } }
        for(int i = 1; i <= count; i++){
            if(mainBoard[initRow + i][initCol + i] == player || mainBoard[initRow + i][initCol + i] == "*"){
                loopBreak = false; } }
        if(loopBreak){
            for(int i = 0; i <= count; i++){ mainBoard[initRow + i][initCol + i] = player; } } }



    public static void displayGUI(){       // this method updates the GUI game board
        blackCount = 0;                    // keeps track of how many blank chips have been placed
        whiteCount = 0;                    // keeps track of how many white chips have been placed
        blankCount = 0;                    // keeps track of how many black chips have been placed

        for(int i = 0; i < 8; i ++){       // for each index of the first row of buttons:
            if(mainBoard[0][i] == "B"){    // condition is if the tile at specified index is black
                buttonList.get(i).setGraphic(blackChips.get(blackCount));  // takes index i of buttonList and sets graphic to black
                blackCount ++;                                             // refers to blackChips list of index blackCount, since ImageViews cannot be used twice
            }else if(mainBoard[0][i] == "W"){  // specified index is white --> same process for white
                buttonList.get(i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{                             // specified index is no tile --> same process for an empty chip
                buttonList.get(i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(i).setPrefSize(60, 60); }

        for(int i = 0; i < 8; i ++){                                       // Each for loop in this method does the same thing for the corresponding row of mainBoard
            if(mainBoard[1][i] == "B"){
                buttonList.get(8 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[1][i] == "W"){
                buttonList.get(8 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(8 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(8 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[2][i] == "B"){
                buttonList.get(16 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[2][i] == "W"){
                buttonList.get(16 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(16 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(16 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[3][i] == "B"){
                buttonList.get(24 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[3][i] == "W"){
                buttonList.get(24 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(24 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(24 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[4][i] == "B"){
                buttonList.get(32 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[4][i] == "W"){
                buttonList.get(32 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(32 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(32 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[5][i] == "B"){
                buttonList.get(40 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[5][i] == "W"){
                buttonList.get(40 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(40 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(40 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[6][i] == "B"){
                buttonList.get(48 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[6][i] == "W"){
                buttonList.get(48 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(48 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(48 + i).setPrefSize(60, 60);}

        for(int i = 0; i < 8; i ++){
            if(mainBoard[7][i] == "B"){
                buttonList.get(56 + i).setGraphic(blackChips.get(blackCount));
                blackCount ++;
            }else if(mainBoard[7][i] == "W"){
                buttonList.get(56 + i).setGraphic(whiteChips.get(whiteCount));
                whiteCount ++;
            }else{
                buttonList.get(56 + i).setGraphic(blankChips.get(blankCount));
                blankCount ++; }
            buttonList.get(56 + i).setPrefSize(60, 60);}

        scoreBoard.getChildren().clear();                   // Part of the display is the scoreboard on the bottom, clears the HBox scoreboard
        blackScore.setText(Integer.toString(blackCount));   // updates the black score to how many black tiles were placed in the above for loops
        whiteScore.setText(Integer.toString(whiteCount));   // updates for white

        if(turnNum%2 == 0){                                 // if it is white player's turn, the scoreboard has the Image indicating white turn
            scoreBoard.getChildren().addAll(blackScore, bt, whiteScore);  // the scoreboard consists of the black score, turn image, and white score
        }else{                                              // indicates black's turn
            scoreBoard.getChildren().addAll(blackScore, wt, whiteScore); }

 }

    public static void reset(){                   // Method to reset the game when the quit button is pressed
        turnNum = 0;                              // Number of turns is set back to 0
        for(int rows = 0; rows < 8; rows++){
            for(int cols = 0; cols < 8; cols++){
                mainBoard[rows][cols] = "*"; } }  // For each index of the board array, set it back to a '*'
        setTile(3, 3, "B");      // Setting the 4 basic tiles
        setTile(3, 4, "W");
        setTile(4, 4, "B");
        setTile(4, 3, "W"); }


    public static void gameOver(){    // After each move, this method checks is the game is over
        int black = 0;
        int white = 0;

        int numMovesB = 0;
        int numMovesW = 0;

        for(int i = 0; i < 8; i++){                                           // Nested for loops --> for each index of mainBoard
            for(int j = 0; j < 8; j++){
                if(isValid(i, j, "B") && mainBoard[i][j] == "*"){      // Tallies number of legal moves for black and white
                    numMovesB ++; }
                if(isValid(i, j, "W") && mainBoard[i][j] == "*"){
                    numMovesW ++; } } }

        if(blankCount == 0 || numMovesB + numMovesW == 0){            // if no more blank tiles or no legal moves, game is over
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(mainBoard[i][j] == "B"){    // tallies how many black chips are on the board using nested for loops
                        black++;
                    }else{ white++; } }            // for each chip that is not black, it adds to white tally
            }if(black > white){
                displayBox("", "BLACK WINS!");             // Message indicating black won, if there are more black chips than white
                window.close();                                          // After game is over, window closes
            }else if(white > black){
                displayBox("", "WHITE WINS!");             // Message indicating white won
                window.close();
            }else{
                displayBox("", "TIE GAME!");               // Message indicating tie game, if there are equal black and white chips
                window.close();} } }


    public static void buttonMove(int row, int col){     // This method converts the button input into a move, takes in coordinates of the button
        if(turnNum%2 == 0){
            player = "B";                                // This method controls the 'player' variable which converts turnNum into a String to make a move
        }else{ player = "W"; }
        if(mainBoard[row][col] == "*" && isValid(row, col, player)){     // Button coordinates must correspond to an empty tile and a valid move
            makeMove(row, col, player);                                  // Calls makeMove method if it is legal
            turnNum ++;                                                  // increases turnNum to update 'player' on next button click
            displayGUI();                                                // Calls the display method
            if(computer == 1) {                                          // computer = 1 means the user is playing against computer on easy mode, calls easy mode method
                computerEasyTurn();
            }else if(computer == 2){                                     // computer = 2 means the user is playing against computer on hard mode, calls hard more method
                computerHardTurn(); }
        }else{ displayBox("", "Not a legal move! Please go again.");  // If move is not legal, gives error message and does not update turnNum
        }
        gameOver(); }             // After each move, checks if the game is over

    public static void computerEasyTurn(){                // This is the algorithm that makes a move if difficulty is easy
        int numLegal = 0;
        ArrayList<Integer> xPos = new ArrayList<>();      // List to keep track of the X coordinate of each legal move
        ArrayList<Integer> yPos = new ArrayList<>();      // List for corresponding Y coordinates
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(isValid(i, j, "W") && mainBoard[i][j] == "*"){    // for each tile, if it is a valid move:
                    numLegal++;                                             // number of legal moves increases
                    xPos.add(i);                                            // add the x coordinate i to the X list
                    yPos.add(j); } } }                                      // add the y coordinate j to the Y list

        if(numLegal != 0){
            int index = (int)(Math.random()*(numLegal-1) + 1);     // picks a random move from the list of legal ones
            if(numLegal == 1){ index = 0; }                        // if there is only 1 legal move, index value can only be 0
            displayBox("", "Nice Move!");
            makeMove(xPos.get(index), yPos.get(index), "W"); }   // calls the makeMove method with the chosen coordinates
        try{ Thread.sleep(800); }                                // waits 800ms before displaying so the user has time to see the board first
        catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
        turnNum++;      // increase var turnNum to show it is user's turn again
        displayGUI();   // display updates board and scoreboard

        int oppCount = 0; // oppCount is the number of legal moves for user
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(isValid(i, j, "B")){
                    oppCount ++; } }           // counts how many legal moves user has
        }if(oppCount == 0){
            turnNum++;
            computerEasyTurn();  // If none, computers turn again
            } }

/*    public static void computerHardTurn(){
        int numLegal = 0;
        ArrayList<Integer> shuffle = new ArrayList<>();
        ArrayList<String> placehold = new ArrayList<>();
        ArrayList<Integer> xPos = new ArrayList<>();
        ArrayList<Integer> yPos = new ArrayList<>();
        ArrayList<Integer> numFlip = new ArrayList<>();
        ArrayList<Integer> xPos1 = new ArrayList<>();
        ArrayList<Integer> yPos1 = new ArrayList<>();
        ArrayList<Integer> numFlip1 = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                placehold.add(mainBoard[i][j]); } }


        if(isValid(0, 0, "W") && mainBoard[0][0] == "*"){
            makeMove(0, 0, "W");
            displayBox("", "NICE MOVE!");
            try { Thread.sleep(800); }
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

        }else if(isValid(0, 7, "W") && mainBoard[0][7] == "*"){
            makeMove(0, 7, "W");
            displayBox("", "NICE MOVE!");
            try { Thread.sleep(800); }
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

        }else if(isValid(7, 0, "W") && mainBoard[7][0] == "*"){
            makeMove(7, 0, "W");
            displayBox("", "NICE MOVE!");
            try { Thread.sleep(800); }
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

        }else if(isValid(7, 7, "W") && mainBoard[7][7] == "*"){
            makeMove(7, 7, "W");
            displayBox("", "NICE MOVE!");
            try { Thread.sleep(800); }
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

        }else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(isValid(i, j, "W") && mainBoard[i][j] == "*"){
                        numLegal++;
                        xPos.add(i);
                        yPos.add(j);
                        numFlip.add(numTilesFlipped(i, j, "W")); } } }
            if(numLegal != 0){

                for(int i = 0; i < numLegal; i++){
                    makeMove(xPos.get(i), yPos.get(i), "W");
                    if(!(isValid(0, 0, "B") && mainBoard[0][0] == "*") && !(isValid(0, 7, "B") && mainBoard[0][7] == "*")
                            && !(isValid(7, 0, "B") && mainBoard[7][0] == "*") && !(isValid(7, 7, "B") && mainBoard[7][7] == "*")){
                        xPos1.add(xPos.get(i));
                        yPos1.add(yPos.get(i));
                        numFlip1.add(numFlip.get(i));
                        shuffle.add(i);
                        System.out.println("a");}

                    for(int j = 0; j < 8; j++){
                        for(int k = 0; k < 8; k++){
                            mainBoard[j][k] = placehold.get(8 * j + k); } } }
                System.out.println("b");
                if(xPos1.size() == 1){
                    displayBox("", "NICE MOVE!");
                    makeMove(xPos1.get(0), yPos1.get(0), "W");
                }
                else if(xPos1.size() != 0){
                    System.out.println("c");
                    Collections.shuffle(shuffle);
                    xPos.clear();
                    yPos.clear();
                    numFlip.clear();
                    numFlip1.clone();
                    for(int i = 0; i < shuffle.size(); i++){
                        xPos.add(i, xPos1.get(shuffle.get(i)));
                        yPos.add(i, yPos1.get(shuffle.get(i)));
                        numFlip.add(i, numFlip1.get(shuffle.get(i)));
                    }
                }
                System.out.println("e");

                for(int i = 19; i > 0; i--){
                    if(numFlip.contains(i)){
                        displayBox("", "NICE MOVE!");
                        makeMove(xPos.get(numFlip.indexOf(i)), yPos.get(numFlip.indexOf(i)), "W");
                        break; } } } }
            try { Thread.sleep(800); }
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
            turnNum++;
            displayGUI();
            }*/


    public static void computerHardTurn(){
        int numLegal = 0;

        ArrayList<String> placehold = new ArrayList<>();   // placehold is a memory board for when the algorithm runs tests
        ArrayList<Integer> xPos = new ArrayList<>();       // represents x coordinates of all legal moves
        ArrayList<Integer> yPos = new ArrayList<>();       // y of all legal moves
        ArrayList<Integer> numFlip = new ArrayList<>();    // number of tiles flipped for all legal moves
        ArrayList<Integer> xPos1 = new ArrayList<>();      // x coordinates of all legal moves which do not allow opponent to place tile in corner on next move
        ArrayList<Integer> yPos1 = new ArrayList<>();      // y of all legal moves which do not allow corner
        ArrayList<Integer> numFlip1 = new ArrayList<>();   // number of tiles flipped for all that do not allow corner

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                placehold.add(mainBoard[i][j]); } }      // duplicates mainBoard at the beginning of the turn


        if(isValid(0, 0, "W") && mainBoard[0][0] == "*"){     // if top left corner is legal, take it
            makeMove(0, 0, "W");
            displayBox("", "NICE MOVE!");

        }else if(isValid(0, 7, "W") && mainBoard[0][7] == "*"){  // if top right corner is legal, take it
            makeMove(0, 7, "W");
            displayBox("", "NICE MOVE!");

        }else if(isValid(7, 0, "W") && mainBoard[7][0] == "*"){  // if bottom left corner is legal, take it
            makeMove(7, 0, "W");
            displayBox("", "NICE MOVE!");

        }else if(isValid(7, 7, "W") && mainBoard[7][7] == "*"){  // if bottom right corner is legal, take it
            makeMove(7, 7, "W");
            displayBox("", "NICE MOVE!");

        }else{
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(isValid(i, j, "W") && mainBoard[i][j] == "*"){    // for each tile, if legal, add to xPos, yPos and numFlip
                        numLegal++;    // keeps track of number of legal moves
                        xPos.add(i);
                        yPos.add(j);
                        numFlip.add(numTilesFlipped(i, j, "W")); } } }
            if(numLegal != 0){     // If there are legal moves, proceed
                for(int i = 0; i < numLegal; i++){
                    makeMove(xPos.get(i), yPos.get(i), "W");  // simulates each legal move the computer could make
                    if(!(isValid(0, 0, "B") && mainBoard[0][0] == "*") &&        // if black cannot take any corners after simlated move,
                            !(isValid(0, 7, "B") && mainBoard[0][7] == "*")      // add to xPos1, yPos1, numFlip1
                            && !(isValid(7, 0, "B") && mainBoard[7][0] == "*")
                            && !(isValid(7, 7, "B") && mainBoard[7][7] == "*")){
                        xPos1.add(xPos.get(i));
                        yPos1.add(yPos.get(i));
                        numFlip1.add(numFlip.get(i));
                    }


                    for(int j = 0; j < 8; j++){
                        for(int k = 0; k < 8; k++){
                            mainBoard[j][k] = placehold.get(8 * j + k); } } }    // reset mainBoard to the placehold for next simulation

                if(xPos1.size() != 0){               // if there are moves which do not allow opponent to take corner
                    for(int i = 19; i > 0; i--){     // loop which counts down from 19 (19 is the most tiles that can be flipped on an Othello board)
                        if(numFlip1.contains(i)){    // for each number counting down, stop when numFlip1 contains it; this finds the move which flips the most tiles
                            displayBox("", "NICE MOVE!");
                            makeMove(xPos1.get(numFlip1.indexOf(i)), yPos1.get(numFlip1.indexOf(i)), "W");   // makes move based on xPos1 and yPos1 coordinates of the move with most flipped
                            break; } }
                }else{     // if there are no moves which do not allow opponent to take corners
                    for(int i = 19; i > 0; i--){   // find the moe which flips the most based on the original xPos, yPos list
                        if(numFlip.contains(i)){
                            displayBox("", "NICE MOVE!");
                            makeMove(xPos.get(numFlip.indexOf(i)), yPos.get(numFlip.indexOf(i)), "W");
                            break; } } } }
            try { Thread.sleep(800); }    // wait 800ms so the user can see the board before the move
            catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
        }
        turnNum++;  // next turn
        displayGUI(); // displayGUI method to update board

        int oppCount = 0;   // oppCount is number of legal moves for user
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(isValid(i, j, "B")){
                    oppCount ++; } }    // keeps track of number of legal moves
        }if(oppCount == 0){
            turnNum++;
            computerHardTurn();   // if none, computer turn again
             }}


    public static void playGame(){             // create base board
        turnNum = 0;                           // set turnNum to 0
        setTile(3, 3, "B");   // set starting tiles
        setTile(3, 4, "W");
        setTile(4, 4, "B");
        setTile(4, 3, "W");

        displayGUI();  // display board
    }




    public static void main(String[] args) {
        launch(args);
    }   // psvm needed for GUI classes

    public void start(Stage primaryStage) throws Exception{    // initiating all game screen boards
        window = primaryStage;
        window.setTitle("OTHELLO");

// Initialzing Intro Screen 1:

        play = new Button("PLAY");
        play.setPrefSize(150, 40);
        instruct = new Button("INSTRUCTIONS");
        instruct.setPrefSize(150, 40);

        Image intro = new Image("intro.jpg");  // the intro screen background is an image created in paint.net
        ImageView int1 = new ImageView(intro);
        int1.setFitHeight(700);
        int1.setPreserveRatio(true);

        play.setOnAction(e -> window.setScene(init2));             // selecting play brings to the second intro screen
        instruct.setOnAction(e -> window.setScene(instructions));  // selecting instructions brings to the instructions screen

        VBox introv1 = new VBox(15);                     // introV1 is VBox intro layout 1
        introv1.getChildren().addAll(play, instruct);            // it contains the buttons
        introv1.setAlignment(Pos.BOTTOM_CENTER);                 // setting the allignment to the bottom
        introv1.setPadding(new Insets(150));    // insets 150 to space it the same as the next intro screen

        StackPane stack1 = new StackPane();                      // Stack1 combines the background and the buttons
        stack1.getChildren().addAll(int1, introv1);
        init1 = new Scene(stack1, 700, 700);

// Initializing Intro Screen 2:

        twoP = new Button("Vs. Friend");                   // button to indicate a 2P game
        twoP.setPrefSize(150, 40);
        cpu = new Button("Vs. Computer");                  // button to indicate a computer game
        cpu.setPrefSize(150, 40);
        goBack2 = new Button("Back");                      // button to take back to main screen
        goBack2.setPrefSize(150, 40);

        ImageView int2 = new ImageView(intro);                  // uses same image as background
        int2.setFitHeight(700);
        int2.setPreserveRatio(true);

        VBox vback = new VBox();                                // the back button is in its own VBox because the allignment is different
        vback.getChildren().add(goBack2);
        vback.setAlignment(Pos.CENTER_RIGHT);
        vback.setPadding(new Insets(30));

        VBox introv2 = new VBox(15);                   // introV2 is the second VBox intro layout
        introv2.getChildren().addAll(twoP, cpu);               // contains the two main buttons on this screen
        introv2.setAlignment(Pos.BOTTOM_CENTER);
        introv2.setPadding(new Insets(30));

        VBox vmain = new VBox(20);                     // vMain combines the 3 buttons into 1 layout
        vmain.getChildren().addAll(introv2, vback);
        vmain.setAlignment(Pos.BOTTOM_CENTER);

        StackPane stack2 = new StackPane(int2, vmain);         // Stack2 combines vMain and the background
        init2 = new Scene(stack2, 700, 700);

        twoP.setOnAction(e -> {                // twoP button sets computer variable to none (buttonMove method will overlook computer's turn)
            computer = 0;
            window.setScene(gameBoard);        // switch to game screen
            playGame(); });                    // playGame method is the initiator of the main game
        cpu.setOnAction(e -> {
            commandBox();                      // cpu button calls the command box to determine if computer variable is 1 or 2 (meaning easy or hard)
            window.setScene(gameBoard);        // switch to game screen
            playGame(); });                    // starts game
        goBack2.setOnAction(e -> window.setScene(init1));  // goBack button switches to main screen

// Initializing Instructions Screen:

        goBack = new Button("BACK");              // only one button on the instructions screen, can only go back to main screen
        goBack.setPrefSize(150, 40);

        ImageView int3 = new ImageView(intro);    // same background
        int3.setFitHeight(700);
        int3.setPreserveRatio(true);

        Image instructionsImage = new Image("instruct.jpg");   // instructions are a text file imported as an image
        ImageView int4 = new ImageView(instructionsImage);
        int4.setFitWidth(650);
        int4.setPreserveRatio(true);

        VBox introv3 = new VBox();                             // introV3 is the VBox layout for the back button, again it has a different allignment
        introv3.getChildren().add(goBack);
        introv3.setAlignment(Pos.BOTTOM_RIGHT);
        introv3.setPadding(new Insets(30));

        VBox introv4 = new VBox();                             // introV4 is the VBox layout for the intsructions image
        introv4.getChildren().add(int4);
        introv4.setAlignment(Pos.BOTTOM_CENTER);
        introv4.setPadding(new Insets(10, 10, 90, 10));

        StackPane stack3 = new StackPane();                    // Stack3 combines the background, instructions image and back button
        stack3.getChildren().addAll(int3, introv4, introv3);

        goBack.setOnAction(e -> window.setScene(init1));       // goBack button sets screen to main screen
        instructions = new Scene(stack3, 700, 700);

// Initializing Game Screen Board:

        Image image = new Image("board.gif");   // The board is an image created in paint
        ImageView iv1 = new ImageView(image);
        iv1.setFitHeight(560);                      // Initializing the size of the board to 560x560
        iv1.setPreserveRatio(true);

        Image blackChip = new Image("black.png");  // Each tile image is also an image
        Image whiteChip = new Image("white.png");
        Image blankchip = new Image("blank.png");

        ImageView b1 = new ImageView(blackChip);   // Since ImageViews cannot be used as a graphic for 2 different objects,
        ImageView b2 = new ImageView(blackChip);   // 64 black ImageViews are initialized with the blackChip image
        ImageView b3 = new ImageView(blackChip);
        ImageView b4 = new ImageView(blackChip);
        ImageView b5 = new ImageView(blackChip);
        ImageView b6 = new ImageView(blackChip);
        ImageView b7 = new ImageView(blackChip);
        ImageView b8 = new ImageView(blackChip);
        ImageView b9 = new ImageView(blackChip);
        ImageView b10 = new ImageView(blackChip);
        ImageView b11 = new ImageView(blackChip);
        ImageView b12 = new ImageView(blackChip);
        ImageView b13 = new ImageView(blackChip);
        ImageView b14 = new ImageView(blackChip);
        ImageView b15 = new ImageView(blackChip);
        ImageView b16 = new ImageView(blackChip);
        ImageView b17 = new ImageView(blackChip);
        ImageView b18 = new ImageView(blackChip);
        ImageView b19 = new ImageView(blackChip);
        ImageView b20 = new ImageView(blackChip);
        ImageView b21 = new ImageView(blackChip);
        ImageView b22 = new ImageView(blackChip);
        ImageView b23 = new ImageView(blackChip);
        ImageView b24 = new ImageView(blackChip);
        ImageView b25 = new ImageView(blackChip);
        ImageView b26 = new ImageView(blackChip);
        ImageView b27 = new ImageView(blackChip);
        ImageView b28 = new ImageView(blackChip);
        ImageView b29 = new ImageView(blackChip);
        ImageView b30 = new ImageView(blackChip);
        ImageView b31 = new ImageView(blackChip);
        ImageView b32 = new ImageView(blackChip);
        ImageView b33 = new ImageView(blackChip);
        ImageView b34 = new ImageView(blackChip);
        ImageView b35 = new ImageView(blackChip);
        ImageView b36 = new ImageView(blackChip);
        ImageView b37 = new ImageView(blackChip);
        ImageView b38 = new ImageView(blackChip);
        ImageView b39 = new ImageView(blackChip);
        ImageView b40 = new ImageView(blackChip);
        ImageView b41 = new ImageView(blackChip);
        ImageView b42 = new ImageView(blackChip);
        ImageView b43 = new ImageView(blackChip);
        ImageView b44 = new ImageView(blackChip);
        ImageView b45 = new ImageView(blackChip);
        ImageView b46 = new ImageView(blackChip);
        ImageView b47 = new ImageView(blackChip);
        ImageView b48 = new ImageView(blackChip);
        ImageView b49 = new ImageView(blackChip);
        ImageView b50 = new ImageView(blackChip);
        ImageView b51 = new ImageView(blackChip);
        ImageView b52 = new ImageView(blackChip);
        ImageView b53 = new ImageView(blackChip);
        ImageView b54 = new ImageView(blackChip);
        ImageView b55 = new ImageView(blackChip);
        ImageView b56 = new ImageView(blackChip);
        ImageView b57 = new ImageView(blackChip);
        ImageView b58 = new ImageView(blackChip);
        ImageView b59 = new ImageView(blackChip);
        ImageView b60 = new ImageView(blackChip);
        ImageView b61 = new ImageView(blackChip);
        ImageView b62 = new ImageView(blackChip);
        ImageView b63 = new ImageView(blackChip);
        ImageView b64 = new ImageView(blackChip);

        ImageView w1 = new ImageView(whiteChip);    // 64 white chip ImageViews
        ImageView w2 = new ImageView(whiteChip);
        ImageView w3 = new ImageView(whiteChip);
        ImageView w4 = new ImageView(whiteChip);
        ImageView w5 = new ImageView(whiteChip);
        ImageView w6 = new ImageView(whiteChip);
        ImageView w7 = new ImageView(whiteChip);
        ImageView w8 = new ImageView(whiteChip);
        ImageView w9 = new ImageView(whiteChip);
        ImageView w10 = new ImageView(whiteChip);
        ImageView w11 = new ImageView(whiteChip);
        ImageView w12 = new ImageView(whiteChip);
        ImageView w13 = new ImageView(whiteChip);
        ImageView w14 = new ImageView(whiteChip);
        ImageView w15 = new ImageView(whiteChip);
        ImageView w16 = new ImageView(whiteChip);
        ImageView w17 = new ImageView(whiteChip);
        ImageView w18 = new ImageView(whiteChip);
        ImageView w19 = new ImageView(whiteChip);
        ImageView w20 = new ImageView(whiteChip);
        ImageView w21 = new ImageView(whiteChip);
        ImageView w22 = new ImageView(whiteChip);
        ImageView w23 = new ImageView(whiteChip);
        ImageView w24 = new ImageView(whiteChip);
        ImageView w25 = new ImageView(whiteChip);
        ImageView w26 = new ImageView(whiteChip);
        ImageView w27 = new ImageView(whiteChip);
        ImageView w28 = new ImageView(whiteChip);
        ImageView w29 = new ImageView(whiteChip);
        ImageView w30 = new ImageView(whiteChip);
        ImageView w31 = new ImageView(whiteChip);
        ImageView w32 = new ImageView(whiteChip);
        ImageView w33 = new ImageView(whiteChip);
        ImageView w34 = new ImageView(whiteChip);
        ImageView w35 = new ImageView(whiteChip);
        ImageView w36 = new ImageView(whiteChip);
        ImageView w37 = new ImageView(whiteChip);
        ImageView w38 = new ImageView(whiteChip);
        ImageView w39 = new ImageView(whiteChip);
        ImageView w40 = new ImageView(whiteChip);
        ImageView w41 = new ImageView(whiteChip);
        ImageView w42 = new ImageView(whiteChip);
        ImageView w43 = new ImageView(whiteChip);
        ImageView w44 = new ImageView(whiteChip);
        ImageView w45 = new ImageView(whiteChip);
        ImageView w46 = new ImageView(whiteChip);
        ImageView w47 = new ImageView(whiteChip);
        ImageView w48 = new ImageView(whiteChip);
        ImageView w49 = new ImageView(whiteChip);
        ImageView w50 = new ImageView(whiteChip);
        ImageView w51 = new ImageView(whiteChip);
        ImageView w52 = new ImageView(whiteChip);
        ImageView w53 = new ImageView(whiteChip);
        ImageView w54 = new ImageView(whiteChip);
        ImageView w55 = new ImageView(whiteChip);
        ImageView w56 = new ImageView(whiteChip);
        ImageView w57 = new ImageView(whiteChip);
        ImageView w58 = new ImageView(whiteChip);
        ImageView w59 = new ImageView(whiteChip);
        ImageView w60 = new ImageView(whiteChip);
        ImageView w61 = new ImageView(whiteChip);
        ImageView w62 = new ImageView(whiteChip);
        ImageView w63 = new ImageView(whiteChip);
        ImageView w64 = new ImageView(whiteChip);

        ImageView g1 = new ImageView(blankchip);     // Initializing 60 green tiles (the maximum amount of greens is 60)
        ImageView g2 = new ImageView(blankchip);
        ImageView g3 = new ImageView(blankchip);
        ImageView g4 = new ImageView(blankchip);
        ImageView g5 = new ImageView(blankchip);
        ImageView g6 = new ImageView(blankchip);
        ImageView g7 = new ImageView(blankchip);
        ImageView g8 = new ImageView(blankchip);
        ImageView g9 = new ImageView(blankchip);
        ImageView g10 = new ImageView(blankchip);
        ImageView g11 = new ImageView(blankchip);
        ImageView g12 = new ImageView(blankchip);
        ImageView g13 = new ImageView(blankchip);
        ImageView g14 = new ImageView(blankchip);
        ImageView g15 = new ImageView(blankchip);
        ImageView g16 = new ImageView(blankchip);
        ImageView g17 = new ImageView(blankchip);
        ImageView g18 = new ImageView(blankchip);
        ImageView g19 = new ImageView(blankchip);
        ImageView g20 = new ImageView(blankchip);
        ImageView g21 = new ImageView(blankchip);
        ImageView g22 = new ImageView(blankchip);
        ImageView g23 = new ImageView(blankchip);
        ImageView g24 = new ImageView(blankchip);
        ImageView g25 = new ImageView(blankchip);
        ImageView g26 = new ImageView(blankchip);
        ImageView g27 = new ImageView(blankchip);
        ImageView g28 = new ImageView(blankchip);
        ImageView g29 = new ImageView(blankchip);
        ImageView g30 = new ImageView(blankchip);
        ImageView g31 = new ImageView(blankchip);
        ImageView g32 = new ImageView(blankchip);
        ImageView g33 = new ImageView(blankchip);
        ImageView g34 = new ImageView(blankchip);
        ImageView g35 = new ImageView(blankchip);
        ImageView g36 = new ImageView(blankchip);
        ImageView g37 = new ImageView(blankchip);
        ImageView g38 = new ImageView(blankchip);
        ImageView g39 = new ImageView(blankchip);
        ImageView g40 = new ImageView(blankchip);
        ImageView g41 = new ImageView(blankchip);
        ImageView g42 = new ImageView(blankchip);
        ImageView g43 = new ImageView(blankchip);
        ImageView g44 = new ImageView(blankchip);
        ImageView g45 = new ImageView(blankchip);
        ImageView g46 = new ImageView(blankchip);
        ImageView g47 = new ImageView(blankchip);
        ImageView g48 = new ImageView(blankchip);
        ImageView g49 = new ImageView(blankchip);
        ImageView g50 = new ImageView(blankchip);
        ImageView g51 = new ImageView(blankchip);
        ImageView g52 = new ImageView(blankchip);
        ImageView g53 = new ImageView(blankchip);
        ImageView g54 = new ImageView(blankchip);
        ImageView g55 = new ImageView(blankchip);
        ImageView g56 = new ImageView(blankchip);
        ImageView g57 = new ImageView(blankchip);
        ImageView g58 = new ImageView(blankchip);
        ImageView g59 = new ImageView(blankchip);
        ImageView g60 = new ImageView(blankchip);

        b1.setFitHeight(50);            // Each tile is 50x50
        b1.setPreserveRatio(true);
        b2.setFitHeight(50);
        b2.setPreserveRatio(true);
        b3.setFitHeight(50);
        b3.setPreserveRatio(true);
        b4.setFitHeight(50);
        b4.setPreserveRatio(true);
        b5.setFitHeight(50);
        b5.setPreserveRatio(true);
        b6.setFitHeight(50);
        b6.setPreserveRatio(true);
        b7.setFitHeight(50);
        b7.setPreserveRatio(true);
        b8.setFitHeight(50);
        b8.setPreserveRatio(true);
        b9.setFitHeight(50);
        b9.setPreserveRatio(true);
        b10.setFitHeight(50);
        b10.setPreserveRatio(true);
        b11.setFitHeight(50);
        b11.setPreserveRatio(true);
        b12.setFitHeight(50);
        b12.setPreserveRatio(true);
        b13.setFitHeight(50);
        b13.setPreserveRatio(true);
        b14.setFitHeight(50);
        b14.setPreserveRatio(true);
        b15.setFitHeight(50);
        b15.setPreserveRatio(true);
        b16.setFitHeight(50);
        b16.setPreserveRatio(true);
        b17.setFitHeight(50);
        b17.setPreserveRatio(true);
        b18.setFitHeight(50);
        b18.setPreserveRatio(true);
        b19.setFitHeight(50);
        b19.setPreserveRatio(true);
        b20.setFitHeight(50);
        b20.setPreserveRatio(true);
        b21.setFitHeight(50);
        b21.setPreserveRatio(true);
        b22.setFitHeight(50);
        b22.setPreserveRatio(true);
        b23.setFitHeight(50);
        b23.setPreserveRatio(true);
        b24.setFitHeight(50);
        b24.setPreserveRatio(true);
        b25.setFitHeight(50);
        b25.setPreserveRatio(true);
        b26.setFitHeight(50);
        b26.setPreserveRatio(true);
        b27.setFitHeight(50);
        b27.setPreserveRatio(true);
        b28.setFitHeight(50);
        b28.setPreserveRatio(true);
        b29.setFitHeight(50);
        b29.setPreserveRatio(true);
        b30.setFitHeight(50);
        b30.setPreserveRatio(true);
        b31.setFitHeight(50);
        b31.setPreserveRatio(true);
        b32.setFitHeight(50);
        b33.setPreserveRatio(true);
        b34.setFitHeight(50);
        b34.setPreserveRatio(true);
        b35.setFitHeight(50);
        b35.setPreserveRatio(true);
        b36.setFitHeight(50);
        b36.setPreserveRatio(true);
        b37.setFitHeight(50);
        b37.setPreserveRatio(true);
        b38.setFitHeight(50);
        b38.setPreserveRatio(true);
        b39.setFitHeight(50);
        b39.setPreserveRatio(true);
        b40.setFitHeight(50);
        b40.setPreserveRatio(true);
        b41.setFitHeight(50);
        b41.setPreserveRatio(true);
        b42.setFitHeight(50);
        b42.setPreserveRatio(true);
        b43.setFitHeight(50);
        b43.setPreserveRatio(true);
        b44.setFitHeight(50);
        b44.setPreserveRatio(true);
        b45.setFitHeight(50);
        b45.setPreserveRatio(true);
        b46.setFitHeight(50);
        b46.setPreserveRatio(true);
        b47.setFitHeight(50);
        b47.setPreserveRatio(true);
        b48.setFitHeight(50);
        b48.setPreserveRatio(true);
        b49.setFitHeight(50);
        b50.setPreserveRatio(true);
        b51.setFitHeight(50);
        b51.setPreserveRatio(true);
        b52.setFitHeight(50);
        b52.setPreserveRatio(true);
        b53.setFitHeight(50);
        b53.setPreserveRatio(true);
        b54.setFitHeight(50);
        b54.setPreserveRatio(true);
        b55.setFitHeight(50);
        b55.setPreserveRatio(true);
        b56.setFitHeight(50);
        b56.setPreserveRatio(true);
        b57.setFitHeight(50);
        b57.setPreserveRatio(true);
        b58.setFitHeight(50);
        b58.setPreserveRatio(true);
        b59.setFitHeight(50);
        b59.setPreserveRatio(true);
        b60.setFitHeight(50);
        b60.setPreserveRatio(true);
        b61.setFitHeight(50);
        b61.setPreserveRatio(true);
        b62.setFitHeight(50);
        b62.setPreserveRatio(true);
        b63.setFitHeight(50);
        b63.setPreserveRatio(true);
        b64.setFitHeight(50);
        b64.setPreserveRatio(true);

        w1.setFitHeight(50);
        w1.setPreserveRatio(true);
        w2.setFitHeight(50);
        w2.setPreserveRatio(true);
        w3.setFitHeight(50);
        w3.setPreserveRatio(true);
        w4.setFitHeight(50);
        w4.setPreserveRatio(true);
        w5.setFitHeight(50);
        w5.setPreserveRatio(true);
        w6.setFitHeight(50);
        w6.setPreserveRatio(true);
        w7.setFitHeight(50);
        w7.setPreserveRatio(true);
        w8.setFitHeight(50);
        w8.setPreserveRatio(true);
        w9.setFitHeight(50);
        w9.setPreserveRatio(true);
        w10.setFitHeight(50);
        w10.setPreserveRatio(true);
        w11.setFitHeight(50);
        w11.setPreserveRatio(true);
        w12.setFitHeight(50);
        w12.setPreserveRatio(true);
        w13.setFitHeight(50);
        w13.setPreserveRatio(true);
        w14.setFitHeight(50);
        w14.setPreserveRatio(true);
        w15.setFitHeight(50);
        w15.setPreserveRatio(true);
        w16.setFitHeight(50);
        w16.setPreserveRatio(true);
        w17.setFitHeight(50);
        w17.setPreserveRatio(true);
        w18.setFitHeight(50);
        w18.setPreserveRatio(true);
        w19.setFitHeight(50);
        w19.setPreserveRatio(true);
        w20.setFitHeight(50);
        w20.setPreserveRatio(true);
        w21.setFitHeight(50);
        w21.setPreserveRatio(true);
        w22.setFitHeight(50);
        w22.setPreserveRatio(true);
        w23.setFitHeight(50);
        w23.setPreserveRatio(true);
        w24.setFitHeight(50);
        w24.setPreserveRatio(true);
        w25.setFitHeight(50);
        w25.setPreserveRatio(true);
        w26.setFitHeight(50);
        w26.setPreserveRatio(true);
        w27.setFitHeight(50);
        w27.setPreserveRatio(true);
        w28.setFitHeight(50);
        w28.setPreserveRatio(true);
        w29.setFitHeight(50);
        w29.setPreserveRatio(true);
        w30.setFitHeight(50);
        w30.setPreserveRatio(true);
        w31.setFitHeight(50);
        w31.setPreserveRatio(true);
        w32.setFitHeight(50);
        w32.setPreserveRatio(true);
        w33.setFitHeight(50);
        w33.setPreserveRatio(true);
        w34.setFitHeight(50);
        w34.setPreserveRatio(true);
        w35.setFitHeight(50);
        w35.setPreserveRatio(true);
        w36.setFitHeight(50);
        w36.setPreserveRatio(true);
        w37.setFitHeight(50);
        w37.setPreserveRatio(true);
        w38.setFitHeight(50);
        w38.setPreserveRatio(true);
        w39.setFitHeight(50);
        w39.setPreserveRatio(true);
        w40.setFitHeight(50);
        w40.setPreserveRatio(true);
        w41.setFitHeight(50);
        w41.setPreserveRatio(true);
        w42.setFitHeight(50);
        w42.setPreserveRatio(true);
        w43.setFitHeight(50);
        w43.setPreserveRatio(true);
        w44.setFitHeight(50);
        w44.setPreserveRatio(true);
        w45.setFitHeight(50);
        w45.setPreserveRatio(true);
        w46.setFitHeight(50);
        w46.setPreserveRatio(true);
        w47.setFitHeight(50);
        w47.setPreserveRatio(true);
        w48.setFitHeight(50);
        w48.setPreserveRatio(true);
        w49.setFitHeight(50);
        w49.setPreserveRatio(true);
        w50.setFitHeight(50);
        w50.setPreserveRatio(true);
        w51.setFitHeight(50);
        w51.setPreserveRatio(true);
        w52.setFitHeight(50);
        w52.setPreserveRatio(true);
        w53.setFitHeight(50);
        w53.setPreserveRatio(true);
        w54.setFitHeight(50);
        w54.setPreserveRatio(true);
        w55.setFitHeight(50);
        w55.setPreserveRatio(true);
        w56.setFitHeight(50);
        w56.setPreserveRatio(true);
        w57.setFitHeight(50);
        w57.setPreserveRatio(true);
        w58.setFitHeight(50);
        w58.setPreserveRatio(true);
        w59.setFitHeight(50);
        w59.setPreserveRatio(true);
        w60.setFitHeight(50);
        w60.setPreserveRatio(true);
        w61.setFitHeight(50);
        w61.setPreserveRatio(true);
        w62.setFitHeight(50);
        w62.setPreserveRatio(true);
        w63.setFitHeight(50);
        w63.setPreserveRatio(true);
        w64.setFitHeight(50);
        w64.setPreserveRatio(true);

        g1.setFitHeight(50);
        g1.setPreserveRatio(true);
        g2.setFitHeight(50);
        g2.setPreserveRatio(true);
        g3.setFitHeight(50);
        g3.setPreserveRatio(true);
        g4.setFitHeight(50);
        g4.setPreserveRatio(true);
        g5.setFitHeight(50);
        g5.setPreserveRatio(true);
        g6.setFitHeight(50);
        g6.setPreserveRatio(true);
        g7.setFitHeight(50);
        g7.setPreserveRatio(true);
        g8.setFitHeight(50);
        g8.setPreserveRatio(true);
        g9.setFitHeight(50);
        g9.setPreserveRatio(true);
        g10.setFitHeight(50);
        g10.setPreserveRatio(true);
        g11.setFitHeight(50);
        g11.setPreserveRatio(true);
        g12.setFitHeight(50);
        g12.setPreserveRatio(true);
        g13.setFitHeight(50);
        g13.setPreserveRatio(true);
        g14.setFitHeight(50);
        g14.setPreserveRatio(true);
        g15.setFitHeight(50);
        g15.setPreserveRatio(true);
        g16.setFitHeight(50);
        g16.setPreserveRatio(true);
        g17.setFitHeight(50);
        g17.setPreserveRatio(true);
        g18.setFitHeight(50);
        g18.setPreserveRatio(true);
        g19.setFitHeight(50);
        g19.setPreserveRatio(true);
        g20.setFitHeight(50);
        g20.setPreserveRatio(true);
        g21.setFitHeight(50);
        g21.setPreserveRatio(true);
        g22.setFitHeight(50);
        g22.setPreserveRatio(true);
        g23.setFitHeight(50);
        g23.setPreserveRatio(true);
        g24.setFitHeight(50);
        g24.setPreserveRatio(true);
        g25.setFitHeight(50);
        g25.setPreserveRatio(true);
        g26.setFitHeight(50);
        g26.setPreserveRatio(true);
        g27.setFitHeight(50);
        g27.setPreserveRatio(true);
        g28.setFitHeight(50);
        g28.setPreserveRatio(true);
        g29.setFitHeight(50);
        g29.setPreserveRatio(true);
        g30.setFitHeight(50);
        g30.setPreserveRatio(true);
        g31.setFitHeight(50);
        g31.setPreserveRatio(true);
        g32.setFitHeight(50);
        g32.setPreserveRatio(true);
        g33.setFitHeight(50);
        g33.setPreserveRatio(true);
        g34.setFitHeight(50);
        g34.setPreserveRatio(true);
        g35.setFitHeight(50);
        g35.setPreserveRatio(true);
        g36.setFitHeight(50);
        g36.setPreserveRatio(true);
        g37.setFitHeight(50);
        g37.setPreserveRatio(true);
        g38.setFitHeight(50);
        g38.setPreserveRatio(true);
        g39.setFitHeight(50);
        g39.setPreserveRatio(true);
        g40.setFitHeight(50);
        g40.setPreserveRatio(true);
        g41.setFitHeight(50);
        g41.setPreserveRatio(true);
        g42.setFitHeight(50);
        g42.setPreserveRatio(true);
        g43.setFitHeight(50);
        g43.setPreserveRatio(true);
        g44.setFitHeight(50);
        g44.setPreserveRatio(true);
        g45.setFitHeight(50);
        g45.setPreserveRatio(true);
        g46.setFitHeight(50);
        g46.setPreserveRatio(true);
        g47.setFitHeight(50);
        g47.setPreserveRatio(true);
        g48.setFitHeight(50);
        g48.setPreserveRatio(true);
        g49.setFitHeight(50);
        g49.setPreserveRatio(true);
        g50.setFitHeight(50);
        g50.setPreserveRatio(true);
        g51.setFitHeight(50);
        g51.setPreserveRatio(true);
        g52.setFitHeight(50);
        g52.setPreserveRatio(true);
        g53.setFitHeight(50);
        g53.setPreserveRatio(true);
        g54.setFitHeight(50);
        g54.setPreserveRatio(true);
        g55.setFitHeight(50);
        g55.setPreserveRatio(true);
        g56.setFitHeight(50);
        g56.setPreserveRatio(true);
        g57.setFitHeight(50);
        g57.setPreserveRatio(true);
        g58.setFitHeight(50);
        g58.setPreserveRatio(true);
        g59.setFitHeight(50);
        g59.setPreserveRatio(true);
        g60.setFitHeight(50);
        g60.setPreserveRatio(true);

        blackChips.add(b1);    // The black chips are part of an ArrayList called blackChips
        blackChips.add(b2);    // to be called based on index in displayGUI method
        blackChips.add(b3);
        blackChips.add(b4);
        blackChips.add(b6);
        blackChips.add(b7);
        blackChips.add(b8);
        blackChips.add(b9);
        blackChips.add(b10);
        blackChips.add(b11);
        blackChips.add(b12);
        blackChips.add(b13);
        blackChips.add(b14);
        blackChips.add(b15);
        blackChips.add(b16);
        blackChips.add(b17);
        blackChips.add(b18);
        blackChips.add(b19);
        blackChips.add(b20);
        blackChips.add(b21);
        blackChips.add(b22);
        blackChips.add(b23);
        blackChips.add(b24);
        blackChips.add(b25);
        blackChips.add(b26);
        blackChips.add(b27);
        blackChips.add(b28);
        blackChips.add(b29);
        blackChips.add(b30);
        blackChips.add(b31);
        blackChips.add(b32);
        blackChips.add(b33);
        blackChips.add(b34);
        blackChips.add(b35);
        blackChips.add(b36);
        blackChips.add(b37);
        blackChips.add(b38);
        blackChips.add(b39);
        blackChips.add(b40);
        blackChips.add(b41);
        blackChips.add(b42);
        blackChips.add(b43);
        blackChips.add(b44);
        blackChips.add(b45);
        blackChips.add(b46);
        blackChips.add(b47);
        blackChips.add(b48);
        blackChips.add(b49);
        blackChips.add(b50);
        blackChips.add(b51);
        blackChips.add(b52);
        blackChips.add(b53);
        blackChips.add(b54);
        blackChips.add(b55);
        blackChips.add(b56);
        blackChips.add(b57);
        blackChips.add(b58);
        blackChips.add(b59);
        blackChips.add(b60);
        blackChips.add(b61);
        blackChips.add(b62);
        blackChips.add(b63);
        blackChips.add(b64);

        whiteChips.add(w1);  // The white chips are added to whiteChips ArrayList
        whiteChips.add(w2);
        whiteChips.add(w3);
        whiteChips.add(w4);
        whiteChips.add(w5);
        whiteChips.add(w6);
        whiteChips.add(w7);
        whiteChips.add(w8);
        whiteChips.add(w9);
        whiteChips.add(w10);
        whiteChips.add(w11);
        whiteChips.add(w12);
        whiteChips.add(w13);
        whiteChips.add(w14);
        whiteChips.add(w15);
        whiteChips.add(w16);
        whiteChips.add(w17);
        whiteChips.add(w18);
        whiteChips.add(w19);
        whiteChips.add(w20);
        whiteChips.add(w21);
        whiteChips.add(w22);
        whiteChips.add(w23);
        whiteChips.add(w24);
        whiteChips.add(w25);
        whiteChips.add(w26);
        whiteChips.add(w27);
        whiteChips.add(w28);
        whiteChips.add(w29);
        whiteChips.add(w30);
        whiteChips.add(w31);
        whiteChips.add(w32);
        whiteChips.add(w33);
        whiteChips.add(w34);
        whiteChips.add(w35);
        whiteChips.add(w36);
        whiteChips.add(w37);
        whiteChips.add(w38);
        whiteChips.add(w39);
        whiteChips.add(w40);
        whiteChips.add(w41);
        whiteChips.add(w42);
        whiteChips.add(w43);
        whiteChips.add(w44);
        whiteChips.add(w45);
        whiteChips.add(w46);
        whiteChips.add(w47);
        whiteChips.add(w48);
        whiteChips.add(w49);
        whiteChips.add(w50);
        whiteChips.add(w51);
        whiteChips.add(w52);
        whiteChips.add(w53);
        whiteChips.add(w54);
        whiteChips.add(w55);
        whiteChips.add(w56);
        whiteChips.add(w57);
        whiteChips.add(w58);
        whiteChips.add(w59);
        whiteChips.add(w60);
        whiteChips.add(w61);
        whiteChips.add(w62);
        whiteChips.add(w63);
        whiteChips.add(w64);

        blankChips.add(g1);   // Green chips are added to blankChips ArrayList
        blankChips.add(g2);
        blankChips.add(g3);
        blankChips.add(g4);
        blankChips.add(g5);
        blankChips.add(g6);
        blankChips.add(g7);
        blankChips.add(g8);
        blankChips.add(g9);
        blankChips.add(g10);
        blankChips.add(g11);
        blankChips.add(g12);
        blankChips.add(g13);
        blankChips.add(g14);
        blankChips.add(g15);
        blankChips.add(g16);
        blankChips.add(g17);
        blankChips.add(g18);
        blankChips.add(g19);
        blankChips.add(g20);
        blankChips.add(g21);
        blankChips.add(g22);
        blankChips.add(g23);
        blankChips.add(g24);
        blankChips.add(g25);
        blankChips.add(g26);
        blankChips.add(g27);
        blankChips.add(g28);
        blankChips.add(g29);
        blankChips.add(g30);
        blankChips.add(g31);
        blankChips.add(g32);
        blankChips.add(g33);
        blankChips.add(g34);
        blankChips.add(g35);
        blankChips.add(g36);
        blankChips.add(g37);
        blankChips.add(g38);
        blankChips.add(g39);
        blankChips.add(g40);
        blankChips.add(g41);
        blankChips.add(g42);
        blankChips.add(g43);
        blankChips.add(g44);
        blankChips.add(g45);
        blankChips.add(g46);
        blankChips.add(g47);
        blankChips.add(g48);
        blankChips.add(g49);
        blankChips.add(g50);
        blankChips.add(g51);
        blankChips.add(g52);
        blankChips.add(g53);
        blankChips.add(g54);
        blankChips.add(g55);
        blankChips.add(g56);
        blankChips.add(g57);
        blankChips.add(g58);
        blankChips.add(g59);
        blankChips.add(g60);

        but1 = new Button();   // Initializing each of the 64 static buttons on the board
        but2 = new Button();
        but3 = new Button();
        but4 = new Button();
        but5 = new Button();
        but6 = new Button();
        but7 = new Button();
        but8 = new Button();
        but9 = new Button();
        but10 = new Button();
        but11 = new Button();
        but12 = new Button();
        but13 = new Button();
        but14 = new Button();
        but15 = new Button();
        but16 = new Button();
        but17 = new Button();
        but18 = new Button();
        but19 = new Button();
        but20 = new Button();
        but21 = new Button();
        but22 = new Button();
        but23 = new Button();
        but24 = new Button();
        but25 = new Button();
        but26 = new Button();
        but27 = new Button();
        but28 = new Button();
        but29 = new Button();
        but30 = new Button();
        but31 = new Button();
        but32 = new Button();
        but33 = new Button();
        but34 = new Button();
        but35 = new Button();
        but36 = new Button();
        but37 = new Button();
        but38 = new Button();
        but39 = new Button();
        but40 = new Button();
        but41 = new Button();
        but42 = new Button();
        but43 = new Button();
        but44 = new Button();
        but45 = new Button();
        but46 = new Button();
        but47 = new Button();
        but48 = new Button();
        but49 = new Button();
        but50 = new Button();
        but51 = new Button();
        but52 = new Button();
        but53 = new Button();
        but54 = new Button();
        but55 = new Button();
        but56 = new Button();
        but57 = new Button();
        but58 = new Button();
        but59 = new Button();
        but60 = new Button();
        but61 = new Button();
        but62 = new Button();
        but63 = new Button();
        but64 = new Button();

        but1.setPrefSize(60, 60);   // Each button is 60x60, slightly larger than ImageViews
        but2.setPrefSize(60, 60);
        but3.setPrefSize(60, 60);
        but4.setPrefSize(60, 60);
        but5.setPrefSize(60, 60);
        but6.setPrefSize(60, 60);
        but7.setPrefSize(60, 60);
        but8.setPrefSize(60, 60);
        but9.setPrefSize(60, 60);
        but10.setPrefSize(60, 60);
        but11.setPrefSize(60, 60);
        but12.setPrefSize(60, 60);
        but13.setPrefSize(60, 60);
        but14.setPrefSize(60, 60);
        but15.setPrefSize(60, 60);
        but16.setPrefSize(60, 60);
        but17.setPrefSize(60, 60);
        but18.setPrefSize(60, 60);
        but19.setPrefSize(60, 60);
        but20.setPrefSize(60, 60);
        but21.setPrefSize(60, 60);
        but22.setPrefSize(60, 60);
        but23.setPrefSize(60, 60);
        but24.setPrefSize(60, 60);
        but25.setPrefSize(60, 60);
        but26.setPrefSize(60, 60);
        but27.setPrefSize(60, 60);
        but28.setPrefSize(60, 60);
        but29.setPrefSize(60, 60);
        but30.setPrefSize(60, 60);
        but31.setPrefSize(60, 60);
        but32.setPrefSize(60, 60);
        but33.setPrefSize(60, 60);
        but34.setPrefSize(60, 60);
        but35.setPrefSize(60, 60);
        but36.setPrefSize(60, 60);
        but37.setPrefSize(60, 60);
        but38.setPrefSize(60, 60);
        but39.setPrefSize(60, 60);
        but40.setPrefSize(60, 60);
        but41.setPrefSize(60, 60);
        but42.setPrefSize(60, 60);
        but43.setPrefSize(60, 60);
        but44.setPrefSize(60, 60);
        but45.setPrefSize(60, 60);
        but46.setPrefSize(60, 60);
        but47.setPrefSize(60, 60);
        but48.setPrefSize(60, 60);
        but49.setPrefSize(60, 60);
        but50.setPrefSize(60, 60);
        but51.setPrefSize(60, 60);
        but52.setPrefSize(60, 60);
        but53.setPrefSize(60, 60);
        but54.setPrefSize(60, 60);
        but55.setPrefSize(60, 60);
        but56.setPrefSize(60, 60);
        but57.setPrefSize(60, 60);
        but58.setPrefSize(60, 60);
        but59.setPrefSize(60, 60);
        but60.setPrefSize(60, 60);
        but61.setPrefSize(60, 60);
        but62.setPrefSize(60, 60);
        but63.setPrefSize(60, 60);
        but64.setPrefSize(60, 60);

        buttonList.add(but1);   // The buttons also belong to an ArrayList to be used in displayGUI method
        buttonList.add(but2);
        buttonList.add(but3);
        buttonList.add(but4);
        buttonList.add(but5);
        buttonList.add(but6);
        buttonList.add(but7);
        buttonList.add(but8);
        buttonList.add(but9);
        buttonList.add(but10);
        buttonList.add(but11);
        buttonList.add(but12);
        buttonList.add(but13);
        buttonList.add(but14);
        buttonList.add(but15);
        buttonList.add(but16);
        buttonList.add(but17);
        buttonList.add(but18);
        buttonList.add(but19);
        buttonList.add(but20);
        buttonList.add(but21);
        buttonList.add(but22);
        buttonList.add(but23);
        buttonList.add(but24);
        buttonList.add(but25);
        buttonList.add(but26);
        buttonList.add(but27);
        buttonList.add(but28);
        buttonList.add(but29);
        buttonList.add(but30);
        buttonList.add(but31);
        buttonList.add(but32);
        buttonList.add(but33);
        buttonList.add(but34);
        buttonList.add(but35);
        buttonList.add(but36);
        buttonList.add(but37);
        buttonList.add(but38);
        buttonList.add(but39);
        buttonList.add(but40);
        buttonList.add(but41);
        buttonList.add(but42);
        buttonList.add(but43);
        buttonList.add(but44);
        buttonList.add(but45);
        buttonList.add(but46);
        buttonList.add(but47);
        buttonList.add(but48);
        buttonList.add(but49);
        buttonList.add(but50);
        buttonList.add(but51);
        buttonList.add(but52);
        buttonList.add(but53);
        buttonList.add(but54);
        buttonList.add(but55);
        buttonList.add(but56);
        buttonList.add(but57);
        buttonList.add(but58);
        buttonList.add(but59);
        buttonList.add(but60);
        buttonList.add(but61);
        buttonList.add(but62);
        buttonList.add(but63);
        buttonList.add(but64);

        but1.setStyle("-fx-background-color: #009442");   // Hex value 009442 is the same green as the blank chip
        but2.setStyle("-fx-background-color: #009442");
        but3.setStyle("-fx-background-color: #009442");
        but4.setStyle("-fx-background-color: #009442");
        but5.setStyle("-fx-background-color: #009442");
        but6.setStyle("-fx-background-color: #009442");
        but7.setStyle("-fx-background-color: #009442");
        but8.setStyle("-fx-background-color: #009442");
        but9.setStyle("-fx-background-color: #009442");
        but10.setStyle("-fx-background-color: #009442");
        but11.setStyle("-fx-background-color: #009442");
        but12.setStyle("-fx-background-color: #009442");
        but13.setStyle("-fx-background-color: #009442");
        but14.setStyle("-fx-background-color: #009442");
        but15.setStyle("-fx-background-color: #009442");
        but16.setStyle("-fx-background-color: #009442");
        but17.setStyle("-fx-background-color: #009442");
        but18.setStyle("-fx-background-color: #009442");
        but19.setStyle("-fx-background-color: #009442");
        but20.setStyle("-fx-background-color: #009442");
        but21.setStyle("-fx-background-color: #009442");
        but22.setStyle("-fx-background-color: #009442");
        but23.setStyle("-fx-background-color: #009442");
        but24.setStyle("-fx-background-color: #009442");
        but25.setStyle("-fx-background-color: #009442");
        but26.setStyle("-fx-background-color: #009442");
        but27.setStyle("-fx-background-color: #009442");
        but28.setStyle("-fx-background-color: #009442");
        but29.setStyle("-fx-background-color: #009442");
        but30.setStyle("-fx-background-color: #009442");
        but31.setStyle("-fx-background-color: #009442");
        but32.setStyle("-fx-background-color: #009442");
        but33.setStyle("-fx-background-color: #009442");
        but34.setStyle("-fx-background-color: #009442");
        but35.setStyle("-fx-background-color: #009442");
        but36.setStyle("-fx-background-color: #009442");
        but37.setStyle("-fx-background-color: #009442");
        but38.setStyle("-fx-background-color: #009442");
        but39.setStyle("-fx-background-color: #009442");
        but40.setStyle("-fx-background-color: #009442");
        but41.setStyle("-fx-background-color: #009442");
        but42.setStyle("-fx-background-color: #009442");
        but43.setStyle("-fx-background-color: #009442");
        but44.setStyle("-fx-background-color: #009442");
        but45.setStyle("-fx-background-color: #009442");
        but46.setStyle("-fx-background-color: #009442");
        but47.setStyle("-fx-background-color: #009442");
        but48.setStyle("-fx-background-color: #009442");
        but49.setStyle("-fx-background-color: #009442");
        but50.setStyle("-fx-background-color: #009442");
        but51.setStyle("-fx-background-color: #009442");
        but52.setStyle("-fx-background-color: #009442");
        but53.setStyle("-fx-background-color: #009442");
        but54.setStyle("-fx-background-color: #009442");
        but55.setStyle("-fx-background-color: #009442");
        but56.setStyle("-fx-background-color: #009442");
        but57.setStyle("-fx-background-color: #009442");
        but58.setStyle("-fx-background-color: #009442");
        but59.setStyle("-fx-background-color: #009442");
        but60.setStyle("-fx-background-color: #009442");
        but61.setStyle("-fx-background-color: #009442");
        but62.setStyle("-fx-background-color: #009442");
        but63.setStyle("-fx-background-color: #009442");
        but64.setStyle("-fx-background-color: #009442");

        but1.setOnAction(e -> buttonMove(0, 0));    // Each button is programmed to call buttonMove method
        but2.setOnAction(e -> buttonMove(0, 1));    // and send its own coordinates as parameters
        but3.setOnAction(e -> buttonMove(0, 2));
        but4.setOnAction(e -> buttonMove(0, 3));
        but5.setOnAction(e -> buttonMove(0, 4));
        but6.setOnAction(e -> buttonMove(0, 5));
        but7.setOnAction(e -> buttonMove(0, 6));
        but8.setOnAction(e -> buttonMove(0, 7));
        but9.setOnAction(e -> buttonMove(1, 0));
        but10.setOnAction(e -> buttonMove(1, 1));
        but11.setOnAction(e -> buttonMove(1, 2));
        but12.setOnAction(e -> buttonMove(1, 3));
        but13.setOnAction(e -> buttonMove(1, 4));
        but14.setOnAction(e -> buttonMove(1, 5));
        but15.setOnAction(e -> buttonMove(1, 6));
        but16.setOnAction(e -> buttonMove(1, 7));
        but17.setOnAction(e -> buttonMove(2, 0));
        but18.setOnAction(e -> buttonMove(2, 1));
        but19.setOnAction(e -> buttonMove(2, 2));
        but20.setOnAction(e -> buttonMove(2, 3));
        but21.setOnAction(e -> buttonMove(2, 4));
        but22.setOnAction(e -> buttonMove(2, 5));
        but23.setOnAction(e -> buttonMove(2, 6));
        but24.setOnAction(e -> buttonMove(2, 7));
        but25.setOnAction(e -> buttonMove(3, 0));
        but26.setOnAction(e -> buttonMove(3, 1));
        but27.setOnAction(e -> buttonMove(3, 2));
        but28.setOnAction(e -> buttonMove(3, 3));
        but29.setOnAction(e -> buttonMove(3, 4));
        but30.setOnAction(e -> buttonMove(3, 5));
        but31.setOnAction(e -> buttonMove(3, 6));
        but32.setOnAction(e -> buttonMove(3, 7));
        but33.setOnAction(e -> buttonMove(4, 0));
        but34.setOnAction(e -> buttonMove(4, 1));
        but35.setOnAction(e -> buttonMove(4, 2));
        but36.setOnAction(e -> buttonMove(4, 3));
        but37.setOnAction(e -> buttonMove(4, 4));
        but38.setOnAction(e -> buttonMove(4, 5));
        but39.setOnAction(e -> buttonMove(4, 6));
        but40.setOnAction(e -> buttonMove(4, 7));
        but41.setOnAction(e -> buttonMove(5, 0));
        but42.setOnAction(e -> buttonMove(5, 1));
        but43.setOnAction(e -> buttonMove(5, 2));
        but44.setOnAction(e -> buttonMove(5, 3));
        but45.setOnAction(e -> buttonMove(5, 4));
        but46.setOnAction(e -> buttonMove(5, 5));
        but47.setOnAction(e -> buttonMove(5, 6));
        but48.setOnAction(e -> buttonMove(5, 7));
        but49.setOnAction(e -> buttonMove(6, 0));
        but50.setOnAction(e -> buttonMove(6, 1));
        but51.setOnAction(e -> buttonMove(6, 2));
        but52.setOnAction(e -> buttonMove(6, 3));
        but53.setOnAction(e -> buttonMove(6, 4));
        but54.setOnAction(e -> buttonMove(6, 5));
        but55.setOnAction(e -> buttonMove(6, 6));
        but56.setOnAction(e -> buttonMove(6, 7));
        but57.setOnAction(e -> buttonMove(7, 0));
        but58.setOnAction(e -> buttonMove(7, 1));
        but59.setOnAction(e -> buttonMove(7, 2));
        but60.setOnAction(e -> buttonMove(7, 3));
        but61.setOnAction(e -> buttonMove(7, 4));
        but62.setOnAction(e -> buttonMove(7, 5));
        but63.setOnAction(e -> buttonMove(7, 6));
        but64.setOnAction(e -> buttonMove(7, 7));

        VBox introv = new VBox();                            // The bottom layer of the game scene is the board, layout
        introv.getChildren().addAll(iv1);
        introv.setAlignment(Pos.TOP_CENTER);
        introv.setPadding(new Insets(20));

        Image blackTurn = new Image("blackTurn.png");    // The next layer is the scoreboard display
        Image whiteTurn = new Image("whiteTurn.png");    // There is a separate image representing whose turn it is

        bt = new ImageView(blackTurn);   // bt is the image if it is black's turn
        bt.setFitHeight(120);
        bt.setPreserveRatio(true);
        wt = new ImageView(whiteTurn);   // wt is the image if it is white's turn
        wt.setFitHeight(120);
        wt.setPreserveRatio(true);

        blackScore = new Label();                                                      // blackScore is a label for how many black tiles there are
        blackScore.setText(Integer.toString(blackCount));
        blackScore.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 40));    // changing the font
        whiteScore = new Label();                                                      // whiteScore is a label for number of white tiles
        whiteScore.setText(Integer.toString(whiteCount));
        whiteScore.setFont(Font.font("ALGERIAN", FontWeight.BOLD, 40));

        scoreBoard = new HBox(5);                             // scoreBoard is an HBox for the score stats on the bottom
        scoreBoard.getChildren().addAll(blackScore, bt, whiteScore);  // black's score, the image, white's score
        scoreBoard.setAlignment(Pos.BOTTOM_CENTER);
        scoreBoard.setPadding(new Insets(5));

        h1 = new HBox(4);                                                         // h1 contains the first row of buttons
        h1.getChildren().addAll(but1, but2, but3, but4, but5, but6, but7, but8);
        h1.setAlignment(Pos.CENTER);
        h2 = new HBox(4);                                                         // h2 contains the second row of buttons
        h2.getChildren().addAll(but9, but10, but11, but12, but13, but14, but15, but16);
        h2.setAlignment(Pos.CENTER);
        h3 = new HBox(4);                                                         // h3 contains the third row of buttons
        h3.getChildren().addAll(but17, but18, but19, but20, but21, but22, but23, but24);
        h3.setAlignment(Pos.CENTER);
        h4 = new HBox(4);                                                         // h4 contains the fourth row of buttons
        h4.getChildren().addAll(but25, but26, but27, but28, but29, but30, but31, but32);
        h4.setAlignment(Pos.CENTER);
        h5 = new HBox(4);                                                         // h5 contains the fifth row of buttons
        h5.getChildren().addAll(but33, but34, but35, but36, but37, but38, but39, but40);
        h5.setAlignment(Pos.CENTER);
        h6 = new HBox(4);                                                         // h6 contains the sixth row of buttons
        h6.getChildren().addAll(but41, but42, but43, but44, but45, but46, but47, but48);
        h6.setAlignment(Pos.CENTER);
        h7 = new HBox(4);                                                         // h7 contains the seventh row of buttons
        h7.getChildren().addAll(but49, but50, but51, but52, but53, but54, but55, but56);
        h7.setAlignment(Pos.CENTER);
        h8 = new HBox(4);                                                         // h8 contains the eighth row of buttons
        h8.getChildren().addAll(but57, but58, but59, but60, but61, but62, but63, but64);
        h8.setAlignment(Pos.CENTER);

        v1 = new VBox(9);                                                         // v1 contains the 8 HBox's in one big column
        v1.getChildren().addAll(h1, h2, h3, h4, h5, h6, h7, h8);
        v1.setAlignment(Pos.TOP_CENTER);
        v1.setPadding(new Insets(29));

        Button quit = new Button("Quit");            // the quit button allows the user to go back to the main screen
        quit.setPrefSize(70, 30);
        quit.setOnAction(e -> {
            quitBox();                                    // calls quitBox method which brings up the ARE YOU SURE dialog box
            if(result){
                window.setScene(init1);                   // If user answers yes, go to main screen
                reset(); }});                             // reset board in case the user wants to play another game

        VBox quitlayout = new VBox();                     // The quit button has its own layout because of the allignment
        quitlayout.getChildren().add(quit);
        quitlayout.setAlignment(Pos.BOTTOM_RIGHT);
        quitlayout.setPadding(new Insets(5));

        VBox button = new VBox(0);
        button.getChildren().addAll(v1, quitlayout);      // button combines the button layout anf the quit button

        StackPane stack4 = new StackPane();
        stack4.getChildren().addAll(scoreBoard, introv, button);  // Stack4 combines the scoreboard layout, background and the buttons
        stack4.setAlignment(Pos.CENTER);

        gameBoard = new Scene(stack4, 700,700);

        window.setScene(init1);   // on run, first board is main screen
        window.show();
    }}