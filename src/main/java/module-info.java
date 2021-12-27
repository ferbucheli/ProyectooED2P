module ec.edu.espol.grupo_03 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.grupo_03 to javafx.fxml;
    exports ec.edu.espol.grupo_03;
}