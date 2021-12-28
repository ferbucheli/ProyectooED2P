package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class AIPlayersOptionsController {
    @FXML
    void switchToAIPlayersGame(ActionEvent event) {
        App.switchScenes(event, "AIPlayersGame", 1080, 700);
    }

    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }
}
