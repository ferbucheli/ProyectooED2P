package ec.edu.espol.grupo_03;


import alerts.GameAlert;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import game.Symbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author eduar
 */
public class MultiplayerOptionsController {
    @FXML
    private CheckBox cb_player1_is_first;

    @FXML
    private TextField nameLocalLabel;

    @FXML
    private TextField nameVisitorLabel;


    @FXML
    private Label label_selection_symbol;
    
    public static Symbol localPlayerSymbol;
    public static Symbol visitorPlayerSymbol;
    public static String localPlayerName;
    public static String visitorPlayerName;
    public static boolean isLocalFirst;

    
    @FXML
    private void initialize() {
        
        /*Valores por defecto de inicialización*/
        isLocalFirst = false;
        localPlayerSymbol = Symbol.X;
        visitorPlayerSymbol = Symbol.O;
    }
    
    @FXML
    void set_player1_symbol_o(MouseEvent event) {
        localPlayerSymbol = Symbol.O;
        visitorPlayerSymbol = Symbol.X;
        label_selection_symbol.setText("Local: O VS Visitante: X");
    }

    @FXML
    void set_player1_symbol_x(MouseEvent event) {
        localPlayerSymbol = Symbol.X;
        visitorPlayerSymbol = Symbol.O;
        label_selection_symbol.setText("Local: X VS Visitante: O");
    }
    
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }

    @FXML
    void switchToMultiplayerGame(ActionEvent event) {
        
        
        if(cb_player1_is_first.isSelected())
            isLocalFirst = true;
        
        if(nameLocalLabel.getText() != null && nameVisitorLabel.getText() != null 
                && !(nameVisitorLabel.getText().equals("") || nameLocalLabel.getText().equals("")) ){
            
            localPlayerName = nameLocalLabel.getText();
            visitorPlayerName = nameVisitorLabel.getText();
            
            App.switchScenes(event, "MultiplayerGame", 1080, 700);
        } else {
            GameAlert.mostrarAlerta(Alert.AlertType.ERROR,  "Completa todos los campos!");
        }

        
        
        
    }
}
