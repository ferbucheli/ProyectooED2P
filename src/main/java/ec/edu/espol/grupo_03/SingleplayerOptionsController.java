package ec.edu.espol.grupo_03;

import game.Symbol;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class SingleplayerOptionsController {
    
    private Player player;
    
     @FXML
    void switchToSingleplayerGame(ActionEvent event) {
         try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("SingleplayerGame");
            App.setRoot(event, fxmlloader, 1080, 700);
            SingleplayerGameController spgc = fxmlloader.getController();
            spgc.setupGame(player);
         } catch (IOException ex) {
             ex.printStackTrace();
         }
       
        //App.switchScenes(event, "SingleplayerGame", 1080, 700);
    }
    
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }

    @FXML
    private void setPlayerX(MouseEvent event) {
        this.player = new Player("Fer", Symbol.X);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Has escogio a las X");
        a.show();
    }

    @FXML
    private void setPlayerO(MouseEvent event) {
        this.player = new Player("Fer", Symbol.O);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Has escogio a las O");
        a.show();
    }
}
