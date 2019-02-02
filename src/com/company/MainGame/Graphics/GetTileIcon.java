package com.company.MainGame.Graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
;

public class GetTileIcon {

    public static ImageView getTileIcon(char colour){
        Image blackTile = new Image("/Images/black.png");
        Image whiteTile = new Image("/Images/white.png");
        Image blankTile = new Image("/Images/blank.png");

        ImageView im;

        if(colour == 'B'){
            im = new ImageView(blackTile);
        }else if(colour == 'W'){
            im = new ImageView(whiteTile);
        }else{
            im = new ImageView(blankTile);
        }

        im.setFitHeight(40);
        im.setPreserveRatio(true);

        return im;
    }
}
