package ec.edu.espol.grupo_03;

import alerts.GameAlert;
import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.MinimaxTree;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.players.Player;
import playerlog.InformationLog;
import validation.GameValidator;

/**
 *
 * @author eduar
 */
public class MultiplayerGameController {
    
    private boolean isGameOver;
    private Grid tablero;
    private MinimaxTree tree;
    
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
    private ImageView localSymbolImage;
    
    @FXML
    private ImageView visitorSymbolImage;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        generateLog();
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
        loadPlayersImages();
        
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
    
    public void tree(){
        tree = new MinimaxTree(tablero);
        tree.generateTree(currentPlayer);
    }
    
    public void aiMove(){
        
        tree();
        //borderPane.getChildren().clear();
        tree.minimax(true, currentPlayer);
        tablero = tree.minimax();
        setCellEvent();
        setImages(tablero);
        borderPane.setCenter(tablero);
        //System.out.println(tablero.imprimirTablero());
        //changeTurn();
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
                            /*Se pone el simbolo y luego se hacen las validaciones*/
                            c.setImage();
                            verifyGameStatus();
                            changeTurn();
                        } else {
                            GameAlert.mostrarAlerta(Alert.AlertType.ERROR, "Ya se encuentra una ficha en este lugar");
                        }
                    }
                    
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
    
    private void loadPlayersImages(){
        String xPath = "src/main/resources/images/x1.png";
        String oPath = "src/main/resources/images/o.png";
    
        if(localPlayer.getPlayerSymbol().equals(Symbol.X)){
            try(FileInputStream input = new FileInputStream(xPath)){
                Image img = new Image(input, 50, 50, false, false);
                localSymbolImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ xPath +" image not found");
            }
            
            try(FileInputStream input = new FileInputStream(oPath)){
                Image img = new Image(input, 50, 50, false, false);
                visitorSymbolImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ oPath +" image not found");
            }
        } else {
            
            try(FileInputStream input = new FileInputStream(xPath)){
                Image img = new Image(input, 50, 50, false, false);
                visitorSymbolImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ xPath +" image not found");
            }
            
            try(FileInputStream input = new FileInputStream(oPath)){
                Image img = new Image(input, 50, 50, false, false);
                localSymbolImage.setImage(img);
            } catch (IOException ioe){
                System.out.println("Error "+ oPath +" image not found");
            }
            
        }
    }
    
    /*Metodo que permite el cambio del jugador*/
    public void changeTurn(){
        if(currentPlayer.equals(localPlayer)){
            currentPlayer = visitorPlayer;
            updateCurrentSymbolImage();  /*Actualiza la imagen que se encuentra en el centro*/
        } else{
            currentPlayer = localPlayer;
            updateCurrentSymbolImage(); /*Actualiza la imagen que se encuentra en el centro*/
        }
    }
    
    /*Método que ayuda al movimiento del jugador ACTUAL (currentPlayer)*/
    @FXML
    void helpPlayer(ActionEvent event) {
        System.out.println(currentPlayer.getName() + " Ha necesitado ayuda");
        aiMove();
        tablerosIntermedios();
        verifyGameStatus();    /*Se tiene que evaluar el juego*/
        changeTurn();
        
    }
    
    public void tablerosIntermedios(){
        this.fx_tableros_intermedios.getChildren().clear();
        for(MinimaxTree t : tree.getRoot().getChildren()){
            Grid g = t.getRoot().getContent().copy(100, 100);
            g.setUtility(t.getRoot().getContent().getUtility());
            setIconos(g);
            VBox vbox = new VBox();
            vbox.getChildren().add(g);
            vbox.getChildren().add(new Label("Utilidad: "+g.getUtility()));
            fx_tableros_intermedios.getChildren().add(vbox);
        }
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
        setCellEvent();
    }
    
    private void generateLog(){
        InformationLog.createPlayerLog(localPlayer);
        InformationLog.createPlayerLog(visitorPlayer);
        System.out.println("Se ha generado PlayerLog correctamente");
    }
    
    public void setIconos(Grid g){
        for(ArrayList<Cell> a : g.getGrid()){
            for(Cell c : a){
                if(c.getSymbol() != null){
                    c.setIcon();
                }
            }
        }
    }
    
    
    
}
