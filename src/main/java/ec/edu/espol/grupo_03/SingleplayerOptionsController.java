package ec.edu.espol.grupo_03;

import alerts.GameAlert;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    public static String difficulty;
    public static boolean isXtreme;
    @FXML
    private CheckBox checkbox_maxMode;
    @FXML
    private RadioButton rbFacil;
    @FXML
    private ToggleGroup dificultad;
    @FXML
    private RadioButton rbDificil;
    
    @FXML
    void switchToSingleplayerGame(ActionEvent event) {
        if(checkbox_is_first.isSelected())
            isPlayerFrist = true;
//        if(checkbox_maxMode.isSelected())
//            isXtreme = true;
        
        if(namefield.getText() != null && !namefield.getText().equals("") && (rbFacil.isSelected() || rbDificil.isSelected())){
            humanName = namefield.getText();
            App.switchScenes(event, "SingleplayerGame", 1100, 830);
        } else {
            GameAlert.mostrarAlerta(Alert.AlertType.ERROR, "Completa todos los campos!");
        }
        
        
        
        
    }
    
    @FXML
    private void initialize() {
        /*Valores por defecto de inicialización*/
        isPlayerFrist = false;
        isXtreme = false;
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

    @FXML
    private void facilSelected(ActionEvent event) {
        difficulty = "FACIL";
    }

    @FXML
    private void dificilSelected(ActionEvent event) {
        difficulty = "DIFICIL";
    }
}
