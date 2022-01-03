package ec.edu.espol.grupo_03;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.Minimax;
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
    private Minimax tree;
    private Player humanPlayer;
    private Player AIplayer;
    //private AI player2;
    private Player currentPlayer;
    private boolean isGameOver;
    
    
    @FXML
    private HBox fx_tableros_intermedios;
    
    @FXML
    private BorderPane borderPane;

    
    /*****GAME VARIABLES*****/
    Symbol PLAYER_SYMBOL = SingleplayerOptionsController.playerSymbol;
    Symbol AI_SYMBOL = SingleplayerOptionsController.aiSymbol;
    boolean humanFirst = SingleplayerOptionsController.isPlayerFrist;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }

    @FXML
    private void initialize() {
        /*Se recuperan los valores de la siguiente manera*/
        System.out.println("El humano es primero? "  +humanFirst);
        System.out.println("Valor de humano: " + PLAYER_SYMBOL);
        System.out.println("Valor de computadora: " + AI_SYMBOL);
        
        /*Se crean los jugadores para la partida*/
        setUpGame(new Player("YOU", PLAYER_SYMBOL), new Player("AI",AI_SYMBOL));
    }
    
    
    public void setUpGame(Player humanPlayer, Player AIplayer){
        setGrid();
        setCellEvent();
        this.humanPlayer = humanPlayer;
        this.AIplayer = AIplayer;
        if(humanFirst)  
            this.currentPlayer = humanPlayer;
        else{
            this.currentPlayer = AIplayer;
            aiMove();
        }
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
                    changeTurn();
                });
            }
        }
    }
    
    
    /*Cambio de simbolo dependiendo del jugador que se encuentre en Current*/
    public void changeTurn(){
        if(currentPlayer.equals(humanPlayer)){
            currentPlayer = AIplayer;
            aiMove();
        } else{
            currentPlayer = humanPlayer;
        }
    }
    
    
    /*Metodo que ayuda al jugador a escoger el siguiente movimiento con el tablero actual*/
    @FXML
    void helpPlayer(ActionEvent event) {
        System.out.println(currentPlayer.getName() + " Ha necesitado ayuda");
    }
    
    public void tree(){
        tree = new Minimax(tablero);
        tree.generateTree(currentPlayer);
    }
    
    public void tablerosIntermedios(){
        
        fx_tableros_intermedios.getChildren().add(tree.getRoot().getChildren().get(0).getRoot().getContent());

    }
    
    public void aiMove(){
        tree();
        tablero = tree.minimax(true, currentPlayer);
        setCellEvent();
        borderPane.setCenter(tablero);
        changeTurn();
    }
}
