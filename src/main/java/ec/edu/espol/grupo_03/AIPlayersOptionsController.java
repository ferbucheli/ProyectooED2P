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
    private CheckBox xIsFirst;

    public static Symbol ai1Symbol;
    public static Symbol ai2Symbol;
    public static boolean isAI1First;
    
    
    @FXML
    void set_symbol_AI1_o(MouseEvent event) {
        label_selection_symbol.setText("AI_1: O VS AI_2: X");
        ai1Symbol = Symbol.O;
        ai2Symbol = Symbol.X;
    }

    @FXML
    void set_symbol_AI1_x(MouseEvent event) {
        label_selection_symbol.setText("AI_1: X VS AI_2: O");
        ai1Symbol = Symbol.X;
        ai2Symbol = Symbol.O;
    }
    
    @FXML
    private void initialize() {
        /*Valores por defecto de inicialización*/
        isAI1First = false;
        ai1Symbol = Symbol.X;
        ai2Symbol = Symbol.O;
    }
    
    @FXML
    void switchToAIPlayersGame(ActionEvent event) {
        if(xIsFirst.isSelected())
            isAI1First = true;
        App.switchScenes(event, "AIPlayersGame", 1080, 700);
    }

    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }
}
