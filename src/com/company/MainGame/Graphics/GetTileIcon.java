package com.company.MainGame.Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GetTileIcon {

    public static ImageView getTileIcon(char colour){

        ImageView im;

        if(colour == 'B'){
            Image blackTile = new Image("/Images/black.png");
            im = new ImageView(blackTile);
        }else if(colour == 'W'){
            Image whiteTile = new Image("/Images/white.png");
            im = new ImageView(whiteTile);
        }else{
            Image blankTile = new Image("/Images/blank.png");
            im = new ImageView(blankTile);
        }

        im.setFitHeight(40);
        im.setPreserveRatio(true);

        return im;
    }
}
