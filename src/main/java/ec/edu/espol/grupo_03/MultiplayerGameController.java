package ec.edu.espol.grupo_03;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class MultiplayerGameController {
    
    private boolean isGameOver;
    private Grid tablero;
    private Player player1;
    private Player player2;
    private int turns = 0;
    private Player currentPlayer;

    @FXML
    private HBox fx_tableros_intermedios;
    @FXML
    private BorderPane borderPane;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    public void setupGame(){
        
    }
    
    public void setGrid(){
        tablero = new Grid(3, 3, 300, 300);
        tablero.generateGrid();
        borderPane.setCenter(tablero);
    }
    
    public void setCellEvent(){
        this.currentPlayer = new Player("Fer", Symbol.O);
        for(ArrayList<Cell> l: this.tablero.getGrid()){
            for(Cell c : l){
                c.setOnMouseClicked(e -> {
                    if(e.getButton() == MouseButton.PRIMARY){
                        if(c.getSymbol() == null){
                            c.setSymbol(this.currentPlayer.getPlayerSymbol());
                        }
                    }
                    c.setImage();
                });
            }
        }
    }
    
    public void changeTurn(){
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }
    
    
}
