package ec.edu.espol.grupo_03;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

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
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("MultiplayerGame");
            App.setRoot(event, fxmlloader, 1080, 700);
            MultiplayerGameController spgc = fxmlloader.getController();
            spgc.setGrid();
            spgc.setCellEvent();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
}
