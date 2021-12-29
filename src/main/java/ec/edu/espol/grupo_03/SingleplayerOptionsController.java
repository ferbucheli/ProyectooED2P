package ec.edu.espol.grupo_03;

import game.Symbol;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author eduar
 */
public class SingleplayerOptionsController {

    @FXML
    private CheckBox checkbox_is_first;

    @FXML
    private CheckBox checkbox_recommendations;

    @FXML
    private Label label_selection_symbol;

    public static Symbol playerSymbol;
    public static Symbol aiSymbol;
    public static boolean isPlayerFrist;
    public static boolean needsRecommendations;

    @FXML
    void switchToSingleplayerGame(ActionEvent event) {
        if(checkbox_is_first.isSelected()){
            isPlayerFrist = true;
            System.out.println("Eres primero");
        } else {
            isPlayerFrist = false;
            System.out.println("Eres segundo");
        }
        
        if(checkbox_recommendations.isSelected()){
            needsRecommendations = true;
            System.out.println("Tienes recomendaciones");
        } else {
            needsRecommendations = false;
            System.out.println("No tienes recomendaciones");
        }
        
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("SingleplayerGame");
            App.setRoot(event, fxmlloader, 1080, 700);
            SingleplayerGameController spgc = fxmlloader.getController();
            spgc.setGrid();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    private void initialize() {
        /*Valores por defecto de inicialización*/
        isPlayerFrist = false;
        needsRecommendations = false;
        playerSymbol = Symbol.X;
        aiSymbol = Symbol.O;
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
