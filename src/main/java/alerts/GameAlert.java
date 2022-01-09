package alerts;

import javafx.scene.control.Alert;

/**
 *
 * @author eduar
 */
public class GameAlert {
    //metodo que permite crear un alerta facilmente
    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
