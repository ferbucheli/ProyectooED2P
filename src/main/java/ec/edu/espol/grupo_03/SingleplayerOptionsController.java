package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class SingleplayerOptionsController {
    
     @FXML
    void switchToSingleplayerGame(ActionEvent event) {
        App.switchScenes(event, "SingleplayerGame", 1080, 700);
    }
    
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 740, 530);
    }
}
