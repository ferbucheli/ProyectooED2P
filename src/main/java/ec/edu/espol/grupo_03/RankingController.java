/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.grupo_03;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.players.Player;
import playerlog.InformationLog;

public class RankingController {
    
    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> nombre;

    @FXML
    private TableColumn<Player, Integer> puntaje;
    
    
    public void initialize(URL url, ResourceBundle resourceBundle){
        InformationLog i = new InformationLog();
        i.LeerArchivo();
        ArrayList listaF = new ArrayList<>(i.getLista());
        ObservableList<Player> lista = FXCollections.observableArrayList(listaF);
        nombre.setCellValueFactory(new PropertyValueFactory<Player, String>("Nombre"));
        puntaje.setCellValueFactory(new PropertyValueFactory<Player, Integer>("Puntaje"));
        table.setItems(lista);
    }

}

