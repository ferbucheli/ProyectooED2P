package ec.edu.espol.grupo_03;

import game.Symbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author eduar
 */
public class AIPlayersOptionsController {
    
    @FXML
    private Label label_selection_symbol;

    @FXML
    private CheckBox aiFirstCheckBox;

    public static Symbol localPlayerSymbol;
    public static Symbol visitorPlayerSymbol;
    public static boolean isLocalFirst;
    
    
    @FXML
    void set_symbol_AI1_o(MouseEvent event) {
        label_selection_symbol.setText("CPU1:  O   VS   CPU2:  X");
        localPlayerSymbol = Symbol.O;
        visitorPlayerSymbol = Symbol.X;
    }

    @FXML
    void set_symbol_AI1_x(MouseEvent event) {
        label_selection_symbol.setText("CPU1:  X   VS   CPU2:  O");
        localPlayerSymbol = Symbol.X;
        visitorPlayerSymbol = Symbol.O;
    }
    
    @FXML
    private void initialize() {
        /*Valores por defecto de inicialización*/
        isLocalFirst = false;
        localPlayerSymbol = Symbol.X;
        visitorPlayerSymbol = Symbol.O;
    }
    
    @FXML
    void switchToAIPlayersGame(ActionEvent event) {
        if(aiFirstCheckBox.isSelected())
            isLocalFirst = true;
        
        App.switchScenes(event, "AIPlayersGame", 1080, 650);
    }

    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }
}
