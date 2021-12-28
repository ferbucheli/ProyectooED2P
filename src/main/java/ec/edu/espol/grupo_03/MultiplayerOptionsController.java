package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class MultiplayerOptionsController {
    
     @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }

    @FXML
    void switchToMultiplayerGame(ActionEvent event) {
        App.switchScenes(event, "MultiplayerGame", 1080, 700);
    }
}
