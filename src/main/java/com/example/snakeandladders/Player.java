package com.example.snakeandladders;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static com.example.snakeandladders.SnakeLadder.tileSize;

public class Player {
    private Circle coin;
    private int currentPosition;

    private String name;

    private static Board gameBoard = new Board();
    Player(Circle coin, Color coinColour, String playerName){
        this.coin = coin;
        this.coin.setFill(coinColour);
        currentPosition = 0;
        movePlayer(1);
        this.name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currentPosition+diceValue <= 100) {
            currentPosition += diceValue;


            TranslateTransition secondMove= null, firstMove =translateAnimation(diceValue);

            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition!=-1){
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }
            if(secondMove == null){
                firstMove.play();
            }else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(100)),secondMove);
                sequentialTransition.play();
            }
        }
        /* int x= gameBoard.getXCoordinate(currentPosition);
        int y =gameBoard.getYCoordinate(currentPosition);
        coin.setTranslateY(y);
        coin.setTranslateX(x);*/

    }
    private TranslateTransition translateAnimation(int diceValue){
        TranslateTransition animation = new TranslateTransition(Duration.millis(1000),coin);
        animation.setToX(gameBoard.getXCoordinate(currentPosition));
        animation.setToY(gameBoard.getYCoordinate(currentPosition));
        animation.setAutoReverse(false);
        return animation;
    }
    public void startingPosition(){
        currentPosition =0;
        movePlayer(1);
    }
    boolean checkWinner(){
        if(currentPosition ==100){
            return true;
        }
        return false;
    }

    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }
}
