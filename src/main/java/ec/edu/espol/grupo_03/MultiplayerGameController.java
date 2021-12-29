package ec.edu.espol.grupo_03;

import game.Symbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 *
 * @author eduar
 */
public class MultiplayerGameController {
    
    private boolean isGameOver;  

    
    /***** GAME VARIABLES ****/
    Symbol PLAYER1_SYMBOL = MultiplayerOptionsController.player1Symbol;
    Symbol PLAYER2_SYMBOL = MultiplayerOptionsController.player2Symbol;   
    boolean recommendations = MultiplayerOptionsController.player1NeedsRecommendations;
    boolean isPlayer1First =  MultiplayerOptionsController.isPlayer1Frist;       
    
    @FXML
    private HBox fx_tableros_intermedios;

    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    @FXML
    private void initialize() {
        /*Se recuperan los valores de la siguiente manera*/
        System.out.println("Tengo recomendaciones: " +recommendations);
        System.out.println("El jugador_1 es primero? "  +isPlayer1First);
        System.out.println("Valor de jugador1: " + PLAYER1_SYMBOL);
        System.out.println("Valor de jugador2: " + PLAYER2_SYMBOL);
    }
    
    
}
