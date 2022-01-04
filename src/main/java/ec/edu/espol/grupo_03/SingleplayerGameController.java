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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private boolean isGameOver;
    
    private Player humanPlayer;
    private Player AIplayer;
    private Player currentPlayer;
    
    
    @FXML
    private HBox fx_tableros_intermedios;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Label playerLabel;

    @FXML
    private Label playerPointsLabel;
    
    @FXML
    private Label aiPointsLabel;
    
    @FXML
    private ImageView playerImageSymbol;
 
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }

    @FXML
    private void initialize() {
        /*Se recuperan los valores de la siguiente manera*/
        Symbol humanSymbol  = SingleplayerOptionsController.playerSymbol;
        Symbol AISymbol = SingleplayerOptionsController.aiSymbol;
        boolean ishumanFirst = SingleplayerOptionsController.isPlayerFrist;
        String humanName = SingleplayerOptionsController.humanName;
        
        /*Se crean los jugadores para la partida*/
        this.humanPlayer = new Player(humanName, humanSymbol);
        this.AIplayer = new Player("AI",AISymbol);
        
        
        setUpGame(ishumanFirst);
    }
    
    
    public void setUpGame(boolean isHumanFirst){
        
        setUpUIPlayerInformation();
        whoStartsFirst(isHumanFirst);
        setGrid();
        setCellEvent();
        
    }
    
    /*Pone la informacion de los jugadores en la parte grafica*/
    public void setUpUIPlayerInformation(){
        playerLabel.setText(humanPlayer.getName());
        playerPointsLabel.setText(String.valueOf(humanPlayer.getWins()));
        aiPointsLabel.setText(String.valueOf(AIplayer.getWins()));
    }
    
    private void whoStartsFirst(boolean isHumanFirst){
        /*Elige quien va a ser primero*/
        if(isHumanFirst)  
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
                        } else {
                            System.out.println("No puedes colocar en el mismo lugar");
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
            aiMove();   /*Movimiento de máquina*/
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
