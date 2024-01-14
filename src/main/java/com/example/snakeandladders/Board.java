package com.example.snakeandladders;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    ArrayList<Pair<Integer,Integer>> positionCoordinates;
    ArrayList<Integer> snakesLadders;
    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnakesLadders();
    }
    private void populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j < SnakeLadder.width; j++) {
                int xCord = 0;
                if(i%2 == 0){
                    xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize / 2;
                }else{
                    xCord = SnakeLadder.tileSize * SnakeLadder.height -(j*SnakeLadder.tileSize) - SnakeLadder.tileSize /2;
                }
                int yCord = SnakeLadder.tileSize * SnakeLadder.height -(i*SnakeLadder.tileSize) - SnakeLadder.tileSize /2;
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }
    private void populateSnakesLadders(){
        snakesLadders = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakesLadders.add(i);
        }
        snakesLadders.set(4,25);
        snakesLadders.set(13,46);
        snakesLadders.set(27,5);
        snakesLadders.set(33,49);
        snakesLadders.set(40,3);
        snakesLadders.set(42,63);
        snakesLadders.set(43,18);
        snakesLadders.set(50,69);
        snakesLadders.set(54,31);
        snakesLadders.set(62,81);
        snakesLadders.set(66,45);
        snakesLadders.set(76,58);
        snakesLadders.set(74,92);
        snakesLadders.set(89,53);
        snakesLadders.set(99,41);
    }

    public int getNewPosition(int currentPosition){
        if(currentPosition > 0 && currentPosition < 100){
            return snakesLadders.get(currentPosition);
        }
        return -1;
    }

    int getXCoordinate(int position){
        if(position >=1 && position <=100){
            return positionCoordinates.get(position).getKey();
        }else
            return -1;
    }
    int getYCoordinate(int position){
        if(position >=1 && position <=100){
            return positionCoordinates.get(position).getValue();
        }else
            return -1;
    }

}
