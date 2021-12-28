package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class MainMenuController {
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }
}
