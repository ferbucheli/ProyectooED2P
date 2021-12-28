package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 *
 * @author eduar
 */
public class MultiplayerGameController {
    @FXML
    private HBox fx_tableros_intermedios;

    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 600, 400);
    }
}
