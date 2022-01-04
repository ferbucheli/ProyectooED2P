package ec.edu.espol.grupo_03;

import game.Symbol;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.players.Player;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author eduar
 */
public class SingleplayerOptionsController {
    
    @FXML
    private CheckBox checkbox_is_first;

    @FXML
    private Label label_selection_symbol;
    
    @FXML
    private TextField namefield;

    public static Symbol playerSymbol;
    public static Symbol aiSymbol;
    public static boolean isPlayerFrist;
    public static String humanName;
    
    @FXML
    void switchToSingleplayerGame(ActionEvent event) {
        if(checkbox_is_first.isSelected())
            isPlayerFrist = true;
        
        if(namefield.getText() != null)
            humanName = namefield.getText();
        
        
        App.switchScenes(event, "SingleplayerGame", 1100, 830);
    }
    
    @FXML
    private void initialize() {
        /*Valores por defecto de inicialización*/
        isPlayerFrist = false;
        playerSymbol = Symbol.X;
        aiSymbol = Symbol.O;
        humanName = "You";
    }

    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }

    @FXML
    void set_symbol_o(MouseEvent event) {
        label_selection_symbol.setText("Usted ha seleccionado O");
        playerSymbol = Symbol.O;
        aiSymbol = Symbol.X;
        System.out.println("Se ha seleccionado o");
    }

    @FXML
    void set_symbol_x(MouseEvent event) {
        playerSymbol = Symbol.X;
        aiSymbol = Symbol.O;
        label_selection_symbol.setText("Usted ha seleccionado X");
        System.out.println("Se ha seleccionado X");
    }
}
