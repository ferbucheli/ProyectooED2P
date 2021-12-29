package ec.edu.espol.grupo_03;

import ec.edu.espol.model.AI;
import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class SingleplayerGameController {
    
    
    private Grid tablero;
    private Player player1;
    private AI player2;
    private Player currentPlayer;
    private boolean isGameOver;
    
    
    @FXML
    private HBox fx_tableros_intermedios;
    
    @FXML
    private BorderPane borderPane;

    
    /*****GAME VARIABLES*****/
    Symbol PLAYER_SYMBOL = SingleplayerOptionsController.playerSymbol;
    Symbol AI_SYMBOL = SingleplayerOptionsController.aiSymbol;
    boolean recommendations =  SingleplayerOptionsController.needsRecommendations;
    boolean humanFirst = SingleplayerOptionsController.isPlayerFrist;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }

    @FXML
    private void initialize() {
        /*Se recuperan los valores de la siguiente manera*/
        System.out.println("Tengo recomendaciones: " +recommendations);
        System.out.println("El humano es primero? "  +humanFirst);
        System.out.println("Valor de humano: " + PLAYER_SYMBOL);
        System.out.println("Valor de computadora: " + AI_SYMBOL);
    }
    
    
    public void setupGame(Player p){
        this.player1 = p;
        this.currentPlayer = player1;
        setGrid();
        setCellEvent();
    }
    
    public void setGrid(){
        tablero = new Grid(3, 3, 300, 300);
        tablero.generateGrid();
        borderPane.setCenter(tablero);
    }

    public void setCellEvent(){
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
        return;
    }
    
}
