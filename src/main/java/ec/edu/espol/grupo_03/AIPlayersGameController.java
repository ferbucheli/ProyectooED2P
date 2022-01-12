package ec.edu.espol.grupo_03;

import alerts.GameAlert;
import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.Minimax;
import game.Symbol;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.players.Player;
import validation.GameValidator;

/**
 *
 * @author eduar
 */
public class AIPlayersGameController {
    
    private boolean isGameOver;
    private Grid tablero;
    private int turns = 0;
    private Minimax tree;
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
        
    public void tree(){
        tree = new Minimax(tablero);
        tree.generateTree(currentPlayer);
    }
    
    @FXML
    void nextAIMovement(ActionEvent event) {
        tree();
        //borderPane.getChildren().clear();
        tree.minimax(true, currentPlayer);
        tablero = tree.minimax();
        setCellEvent();
        setImages(tablero);
        borderPane.setCenter(tablero);
        verifyGameStatus();
        changeTurn();
        verifyGameStatus();
    }
    
    public void setupGame(){
        setUpPlayers();
        whoStartsFirst(AIPlayersOptionsController.isLocalFirst);
        updateUIPlayerInformation();
        setGrid();
        setCellEvent();
    }
   
    public void setImages(Grid g){
        for(ArrayList<Cell> a : g.getGrid()){
            for(Cell c : a){
                if(c.getSymbol() != null){
                    c.setImage();
                }
            }
        }
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
        private void verifyGameStatus(){
        int status = GameValidator.gameValidation(tablero);
        if(status > 0){
            System.out.println("Se ha acabado el juego");
            Symbol winnerSymbol = GameValidator.getWinner();
            
            if(status == 1 && winnerSymbol.equals(localPlayer.getPlayerSymbol()) ){
                
                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Ha ganado " + localPlayer.getName());
                /*Actualizacion de puntaje*/
                int currentWins = localPlayer.getWins() + 1;
                localPlayer.setWins(currentWins);
                
            } else if (status == 1) {
                System.out.println("Ha ganado " + visitorPlayer.getName());
                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Ha ganado " + visitorPlayer.getName());
                
                 /*Actualizacion de puntaje*/
                int currentWins = visitorPlayer.getWins() + 1;
                visitorPlayer.setWins(currentWins);
            } if (status == 2){
                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Han quedado empate");
            }
            /*En caso de que WIN o TIE*/
            updateUIPlayersWins(); /*Se actualiza el puntaje*/
            resetGame();           /*Se actualiza el tablero*/
        }
    
}
       
        /*Se actualiza la UI que contiene los puntajes*/
    private void updateUIPlayersWins(){
        localPointsLabel.setText(String.valueOf(localPlayer.getWins()));
        visitorPointsLabel.setText(String.valueOf(visitorPlayer.getWins()));
    }

    /*Vuelve a cargar todos los datos*/
    private void resetGame(){
        setGrid();
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
