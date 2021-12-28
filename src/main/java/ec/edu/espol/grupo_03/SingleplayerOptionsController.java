package ec.edu.espol.grupo_03;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author eduar
 */
public class SingleplayerOptionsController {
    
     @FXML
    void switchToSingleplayerGame(ActionEvent event) {
         try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("SingleplayerGame");
            App.setRoot(event, fxmlloader, 1080, 700);
            SingleplayerGameController spgc = fxmlloader.getController();
            spgc.setGrid();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
       
        //App.switchScenes(event, "SingleplayerGame", 1080, 700);
    }
    
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }
}
