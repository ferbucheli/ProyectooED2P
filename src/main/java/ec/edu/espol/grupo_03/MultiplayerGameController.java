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
import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView symbolTurnImage;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    public void setUpPlayers(){
        String localPlayerName = MultiplayerOptionsController.localPlayerName;
        Symbol localPlayerSymbol = MultiplayerOptionsController.localPlayerSymbol;
        
        String visitorPlayerName = MultiplayerOptionsController.visitorPlayerName;
        Symbol visitorPlayerSymbol = MultiplayerOptionsController.visitorPlayerSymbol; 
        
        this.localPlayer = new Player(localPlayerName, localPlayerSymbol);
        this.visitorPlayer = new Player(visitorPlayerName, visitorPlayerSymbol);
    }
    
    
    @FXML
    private void initialize() {
        setupGame();
    }
    
    public void setupGame(){
        setUpPlayers();
        whoStartsFirst(MultiplayerOptionsController.isLocalFirst);
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
        updateCurrentSymbolImage();
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
                        } else {
                            //throws alert
                        }
                    }
                    c.setImage();
                });
            }
        }
    }
    
    /*Actualiza la imagen que se encuentra en la parte superior dependiendo del turno*/
    private void updateCurrentSymbolImage(){
        String xPath = "src/main/resources/images/x1.png";
        String oPath = "src/main/resources/images/o.png";
        
        if(currentPlayer.getPlayerSymbol().equals(Symbol.X)){
            try(FileInputStream input = new FileInputStream(xPath)){
                Image img = new Image(input, 70, 70, false, false);
                symbolTurnImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ xPath +" image not found");
            }
        } else {
            
            try(FileInputStream input = new FileInputStream(oPath)){
                Image img = new Image(input, 70, 70, false, false);
                symbolTurnImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ oPath +" image not found");
            }
        }

    }
    
    public void changeTurn(){
        if(currentPlayer.equals(localPlayer)){
            currentPlayer = visitorPlayer;
            updateCurrentSymbolImage();
        } else{
            currentPlayer = localPlayer;
            updateCurrentSymbolImage();
        }
    }
    
    /*Método que ayuda al movimiento del jugador ACTUAL (currentPlayer)*/
    @FXML
    void helpPlayer(ActionEvent event) {
        System.out.println(currentPlayer.getName() + " Ha necesitado ayuda");
    }
    
}
