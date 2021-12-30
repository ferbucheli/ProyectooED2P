package ec.edu.espol.grupo_03;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import game.Symbol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private Player PLAYER_1;
    private Player PLAYER_2;
    private int turns = 0;
    private Player currentPlayer;  /*Variable que permite el cambio de jugador*/

    
    /***** GAME VARIABLES ****/
    Symbol PLAYER1_SYMBOL = MultiplayerOptionsController.player1Symbol;
    Symbol PLAYER2_SYMBOL = MultiplayerOptionsController.player2Symbol;   
    boolean isPlayer1First =  MultiplayerOptionsController.isPlayer1Frist;       
    
    @FXML
    private HBox fx_tableros_intermedios;
    @FXML
    private BorderPane borderPane;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    public void setupGame(Player player1, Player player2){
        this.PLAYER_1 = player1;
        this.PLAYER_2 = player2;
        whoStartsGame(player1, player2);
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
                            changeTurn();
                        }
                    }
                    c.setImage();
                });
            }
        }
    }
    
    public void changeTurn(){
        if(currentPlayer.equals(PLAYER_1)){
            currentPlayer = PLAYER_2;
        } else{
            currentPlayer = PLAYER_1;
        }
    }
        
    @FXML
    private void initialize() {
        /*Se recuperan los valores de la siguiente manera*/
        System.out.println("El jugador_1 es primero? "  +isPlayer1First);
        System.out.println("Valor de jugador1: " + PLAYER1_SYMBOL);
        System.out.println("Valor de jugador2: " + PLAYER2_SYMBOL);
        
        Player p1 = new Player("PLAYER_1", PLAYER1_SYMBOL);
        Player p2 = new Player("PLAYER_2", PLAYER2_SYMBOL);
        
        
        setupGame(p1, p2);
    }
    
    
    /*Metodo para poner que jugador va a jugar primero*/
    void whoStartsGame(Player p1, Player p2){
        if(isPlayer1First){
            currentPlayer = p1;
        } else {
            currentPlayer = p2;
        }
    }
    
    
    /*Método que ayuda al movimiento del jugador ACTUAL (currentPlayer)*/
    @FXML
    void helpPlayer(ActionEvent event) {
        System.out.println(currentPlayer.getName() + " Ha necesitado ayuda");
    }
    
}
