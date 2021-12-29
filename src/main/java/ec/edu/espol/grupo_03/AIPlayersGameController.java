package ec.edu.espol.grupo_03;

import game.Symbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 *
 * @author eduar
 */
public class AIPlayersGameController {
    @FXML
    private HBox fx_tableros_intermedios;

    
    /*****GAME VARIABLES***/
    Symbol PLAYER_1 = AIPlayersOptionsController.ai1Symbol;
    Symbol PLAYER_2 = AIPlayersOptionsController.ai2Symbol;
    boolean isPlayer1First = AIPlayersOptionsController.isAI1First;
    
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
    
    @FXML
    void initialize(){
        System.out.println("La AI_1 empieza?: " + isPlayer1First);
        System.out.println("La AI_1 tiene la ficha: " + PLAYER_1);
        System.out.println("La AI_2 tiene la ficha: " + PLAYER_2);
    }
    
    @FXML
    void nextAIMovement(ActionEvent event) {

    }

    
}
