package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class GameSelectionController {
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }

    @FXML
    void switchToSingleplayerOptions(ActionEvent event) {
        App.switchScenes(event, "SinglePlayerOptions", 600, 660);
    }
    
    @FXML
    void switchToMultiplayerOptions(ActionEvent event) {
        App.switchScenes(event, "MultiplayerOptions", 600, 470);
    }

    @FXML
    void switchToAIOptions(ActionEvent event) {
        App.switchScenes(event, "AIPlayersOptions", 600, 470);
    }
}
