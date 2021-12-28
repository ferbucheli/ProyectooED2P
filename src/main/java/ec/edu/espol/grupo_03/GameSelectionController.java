/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.grupo_03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author eduar
 */
public class GameSelectionController {
    
    @FXML
    void switchToMainMenu(ActionEvent event) {
        App.switchScenes(event, "MainMenu", 740, 530);
    }

    @FXML
    void switchToSingleplayerOptions(ActionEvent event) {
        App.switchScenes(event, "SinglePlayerOptions", 600, 470);
    }

    
}
