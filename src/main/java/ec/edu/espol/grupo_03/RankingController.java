package ec.edu.espol.grupo_03;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.players.Player;
import playerlog.InformationLog;

public class RankingController implements Initializable {

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, Integer> wins;
    
   InformationLog log = new InformationLog();
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        log.LeerArchivo();
        ArrayList listaF = new ArrayList<>(InformationLog.getLista()); 
        ObservableList<Player> lista = FXCollections.observableArrayList(listaF);
        name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
        wins.setCellValueFactory(new PropertyValueFactory<Player, Integer>("wins"));
        table.setItems(lista);
    }

}


