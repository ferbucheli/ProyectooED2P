package ec.edu.espol.grupo_03;


import java.io.IOException;
import javafx.fxml.FXMLLoader;
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
public class MultiplayerOptionsController {
    @FXML
    private CheckBox cb_player1_is_first;

    

    @FXML
    private Label label_selection_symbol;
    
    public static Symbol player1Symbol;
    public static Symbol player2Symbol;
    public static boolean isPlayer1Frist;

    @FXML
    private void initialize() {
        
        /*Valores por defecto de inicialización*/
        isPlayer1Frist = false;
        player1Symbol = Symbol.X;
        player2Symbol = Symbol.O;
    }
    
    @FXML
    void set_player1_symbol_o(MouseEvent event) {
        player1Symbol = Symbol.O;
        player2Symbol = Symbol.X;
        label_selection_symbol.setText("player_1: O VS player_2: X");
    }

    @FXML
    void set_player1_symbol_x(MouseEvent event) {
        player1Symbol = Symbol.X;
        player2Symbol = Symbol.O;
        label_selection_symbol.setText("player_1: X VS player_2: O");
    }
    
    @FXML
    void switchToGameSelection(ActionEvent event) {
        App.switchScenes(event, "GameSelection", 820, 470);
    }

    @FXML
    void switchToMultiplayerGame(ActionEvent event) {
        
        
        if(cb_player1_is_first.isSelected())
            isPlayer1Frist = true;
           
        
        App.switchScenes(event, "MultiplayerGame", 1080, 700);
    }
}
