package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 *
 * @author eduar
 */
public class SingleplayerGameController {
    @FXML
    private HBox fx_tableros_intermedios;

    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 740, 530);
    }
}
