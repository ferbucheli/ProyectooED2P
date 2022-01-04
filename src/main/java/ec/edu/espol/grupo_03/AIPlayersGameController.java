package ec.edu.espol.grupo_03;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import game.Symbol;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class AIPlayersGameController {
    
    private boolean isGameOver;
    private Grid tablero;
    private int turns = 0;
    
    private Player localPlayer;
    private Player visitorPlayer;
    private Player currentPlayer;  
    
    @FXML
    private HBox fx_tableros_intermedios;

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Label localNameLabel;

    @FXML
    private Label localPointsLabel;

    @FXML
    private Label visitorNameLabel;

    @FXML
    private Label visitorPointsLabel;
    
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    public void setUpPlayers(){
        Symbol localPlayerSymbol = AIPlayersOptionsController.localPlayerSymbol;
        Symbol visitorPlayerSymbol = AIPlayersOptionsController.visitorPlayerSymbol; 
        this.localPlayer = new Player("CPU1", localPlayerSymbol);
        this.visitorPlayer = new Player("CPU2", visitorPlayerSymbol);
    }
    
    @FXML
    void initialize(){
        setupGame();
    }
    
    @FXML
    void nextAIMovement(ActionEvent event) {
        
    }
    
    public void setupGame(){
        setUpPlayers();
        whoStartsFirst(AIPlayersOptionsController.isLocalFirst);
        updateUIPlayerInformation();
        setGrid();
        setCellEvent();
    }
    
    /*Pone la informacion de los jugadores en la parte grafica*/
    public void updateUIPlayerInformation(){
        
        localNameLabel.setText(localPlayer.getName());
        localPointsLabel.setText(String.valueOf(localPlayer.getWins()));
        visitorNameLabel.setText(visitorPlayer.getName());
        visitorPointsLabel.setText(String.valueOf(visitorPlayer.getWins()));
        
    }
    
    
     /*Metodo para poner que jugador va a jugar primero*/
    void whoStartsFirst(boolean isLocalFirst){
        if(isLocalFirst){
            currentPlayer = localPlayer;
        } else {
            currentPlayer = visitorPlayer;
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
                            changeTurn();
                        }
                    }
                    c.setImage();
                });
            }
        }
    }
    
    public void changeTurn(){
        if(currentPlayer.equals(localPlayer)){
            currentPlayer = visitorPlayer;
        } else{
            currentPlayer = localPlayer;
        }
    }
    
}
