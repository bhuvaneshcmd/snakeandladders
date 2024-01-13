package com.example.snakeandladders;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SnakeLadder extends Application {

    public static final int tileSize = 40, width = 10, height = 10;
    public static final int buttonLine = height*tileSize + 50,infoLine = buttonLine - 30;
    private static Dice dice = new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStarted = false,playerOneTurn = false,playerTwoTurn = false;
    private Pane createContent() throws FileNotFoundException {
        Pane root = new Pane();
        root.setPrefSize(tileSize*width,tileSize*height+100);
        //tiles in game Board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
        // game Board from stock image
        InputStream inputStream = new FileInputStream("C:\\Users\\ACER\\IdeaProjects\\snakeandladders\\src\\main\\resources\\img.png");
        Image image = new Image(inputStream);
        ImageView board = new ImageView();
        board.setImage(image);
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        //player Buttons and start Button
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(170);

        //Info Line

        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start Your Game");

        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(160);

        //players
        playerOne = new Player(new Circle(tileSize/2),Color.BLACK,"Bhuvanesh");
        playerTwo = new Player(new Circle((tileSize/2)-5),Color.WHITE,"Keerthana");

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (playerOneTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerOne.movePlayer(diceValue);
                        //winning condition
                        if(playerOne.checkWinner()){
                            diceLabel.setText(playerOne.getName()+" You are the Winner");
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        }else {
                            //player one disabled;
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");
                            //player two enabled
                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn : "+ playerTwo.getName());
                        }
                    }
                }
            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted) {
                    if (playerTwoTurn) {
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+ diceValue);
                        playerTwo.movePlayer(diceValue);
                        if(playerTwo.checkWinner()){
                            diceLabel.setText(playerTwo.getName()+" You are the Winner");
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                            //player two enabled
                            playerOneTurn = false;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("");
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gameStarted = false;
                        }else {
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn : "+ playerOne.getName());
                            //player two disabled
                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }
                    }
                }
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn "+ playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();
                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        root.getChildren().addAll(board,playerOneButton,playerTwoButton,startButton,playerOneLabel,playerTwoLabel,diceLabel,
        playerOne.getCoin(),playerTwo.getCoin());

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
