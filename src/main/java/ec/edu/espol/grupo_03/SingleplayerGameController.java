package ec.edu.espol.grupo_03;

import alerts.GameAlert;
import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.MinimaxTree;
import game.Symbol;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.players.Player;
import playerlog.InformationLog;
import validation.GameValidator;

/**
 *
 * @author eduar
 */
public class SingleplayerGameController {

    private Grid tablero;
    private MinimaxTree tree;
    private boolean isGameOver;

    private Player humanPlayer;
    private Player AIplayer;
    private Player currentPlayer;
    private String difficulty;

    @FXML
    private HBox fx_tableros_intermedios;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label playerLabel;

    @FXML
    private Label playerPointsLabel;

    @FXML
    private Label aiPointsLabel;

    @FXML
    private ImageView playerImageSymbol;
    @FXML
    private Text tiempotxt;
    @FXML
    private Text tiempoContador;

    @FXML
    void switchToMainMenu(ActionEvent event) {
        generateLog();
        App.switchScenes(event, "MainMenu", 600, 400);

    }

    public void setUpPlayers() {
        /*Se recuperan los valores de la siguiente manera*/
        Symbol humanSymbol = SingleplayerOptionsController.playerSymbol;
        Symbol AISymbol = SingleplayerOptionsController.aiSymbol;
        String humanName = SingleplayerOptionsController.humanName;

        /*Se crean los jugadores para la partida*/
        this.humanPlayer = new Player(humanName, humanSymbol);
        this.AIplayer = new Player("AI", AISymbol);
    }

    @FXML
    private void initialize() {
        setUpGame();
    }

    private void loadPlayerImage() {
        String xPath = "src/main/resources/images/x1.png";
        String oPath = "src/main/resources/images/o.png";

        if (humanPlayer.getPlayerSymbol().equals(Symbol.X)) {
            try (FileInputStream input = new FileInputStream(xPath)) {
                Image img = new Image(input, 50, 50, false, false);
                playerImageSymbol.setImage(img);
            } catch (IOException ioe) {
                System.out.println("Error " + xPath + " image not found");
            }
        } else {
            try (FileInputStream input = new FileInputStream(oPath)) {
                Image img = new Image(input, 50, 50, false, false);
                playerImageSymbol.setImage(img);
            } catch (IOException ioe) {
                System.out.println("Error " + oPath + " image not found");
            }

        }
    }

    public void setUpGame() {
        setUpPlayers();
        updateUIPlayerInformation();
        loadPlayerImage();
        setGrid();
        setCellEvent();
        //tablerosIntermedios();
        whoStartsFirst(SingleplayerOptionsController.isPlayerFrist);
        loadPlayerImage();
//        tablerosIntermedios();

    }

    /*Pone la informacion de los jugadores en la parte grafica*/
    public void updateUIPlayerInformation() {
        difficulty = SingleplayerOptionsController.difficulty;
        playerLabel.setText(humanPlayer.getName());
        playerPointsLabel.setText(String.valueOf(humanPlayer.getWins()));
        aiPointsLabel.setText(String.valueOf(AIplayer.getWins()));
    }

    private void whoStartsFirst(boolean isHumanFirst) {
        /*Elige quien va a ser primero*/
        if (isHumanFirst) {
            this.currentPlayer = humanPlayer;
        } else {
            this.currentPlayer = AIplayer;
            aiMove();
        }
    }

    public void setGrid() {
        tablero = new Grid(3, 3, 300, 300);
        tablero.generateGrid();
        borderPane.setCenter(tablero);
    }

    public void setCellEvent() {
        for (ArrayList<Cell> l : this.tablero.getGrid()) {
            for (Cell c : l) {
                c.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (c.getSymbol() == null) {
                            c.setSymbol(this.currentPlayer.getPlayerSymbol());
                            c.setImage();
                            verifyGameStatus();
                            changeTurn();
                            verifyGameStatus();
                            //System.out.println(tablero.imprimirTablero());
                        } else {
                            GameAlert.mostrarAlerta(Alert.AlertType.ERROR, "Ya se encuentra una ficha en este lugar");
                        }
                    }
                });
            }
        }
    }

    /*Cambio de simbolo dependiendo del jugador que se encuentre en Current*/
    public void changeTurn() {
        if (currentPlayer.equals(humanPlayer)) {
            currentPlayer = AIplayer;
            aiMove();
            /*Movimiento de máquina*/
        } else {
            currentPlayer = humanPlayer;
        }
    }

    /*Metodo que ayuda al jugador a escoger el siguiente movimiento con el tablero actual*/
    @FXML
    void helpPlayer(ActionEvent event) {
        tree();
        tree.minimax(true, currentPlayer);
        tablerosIntermedios();
    }

    public void tree() {
        tree = new MinimaxTree(tablero);
        tree.generateTree(currentPlayer);
    }

    public void tablerosIntermedios() {
        this.fx_tableros_intermedios.getChildren().clear();
        for (MinimaxTree t : tree.getRoot().getChildren()) {
            Grid g = t.getRoot().getContent().copy(100, 100);
            g.setUtility(t.getRoot().getContent().getUtility());
            setIconos(g);
            VBox vbox = new VBox();
            vbox.getChildren().add(g);
            vbox.getChildren().add(new Label("Utilidad: " + g.getUtility()));
            fx_tableros_intermedios.getChildren().add(vbox);
        }
    }

    public void setImages(Grid g) {
        for (ArrayList<Cell> a : g.getGrid()) {
            for (Cell c : a) {
                if (c.getSymbol() != null) {
                    c.setImage();
                }
            }
        }
    }

    public void setIconos(Grid g) {
        for (ArrayList<Cell> a : g.getGrid()) {
            for (Cell c : a) {
                if (c.getSymbol() != null) {
                    c.setIcon();
                }
            }
        }
    }

    public void aiMove() {
        tree();
        //borderPane.getChildren().clear();
        if (this.difficulty.equals("DIFICIL")) {
            tree.minimax(true, currentPlayer);
        } else {
            tree.minimaxEasy(true, currentPlayer);
        }
        tablero = tree.minimax();
        setCellEvent();
        setImages(tablero);
        borderPane.setCenter(tablero);
        //System.out.println(tablero.imprimirTablero());
        tablerosIntermedios();
        changeTurn();
    }

    private void verifyGameStatus() {
        int status = GameValidator.gameValidation(tablero);
        if (status > 0) {
            System.out.println("Se ha acabado el juego");
            Symbol winnerSymbol = GameValidator.getWinner();

            if (status == 1 && winnerSymbol.equals(humanPlayer.getPlayerSymbol())) {

                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Ha ganado " + humanPlayer.getName());
                /*Actualizacion de puntaje*/
                int currentWins = humanPlayer.getWins() + 1;
                humanPlayer.setWins(currentWins);

            } else if (status == 1) {
                System.out.println("Ha ganado " + AIplayer.getName());
                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Ha ganado " + AIplayer.getName());

                /*Actualizacion de puntaje*/
                int currentWins = AIplayer.getWins() + 1;
                AIplayer.setWins(currentWins);
            }
            if (status == 2) {
                GameAlert.mostrarAlerta(Alert.AlertType.INFORMATION, "Han quedado empate");
            }
            /*En caso de que WIN o TIE*/
            updateUIPlayersWins();
            /*Se actualiza el puntaje*/
            resetGame();
            /*Se actualiza el tablero*/
        }

    }

    /*Se actualiza la UI que contiene los puntajes*/
    private void updateUIPlayersWins() {
        playerPointsLabel.setText(String.valueOf(humanPlayer.getWins()));
        aiPointsLabel.setText(String.valueOf(AIplayer.getWins()));
    }

    /*Vuelve a cargar todos los datos*/
    private void resetGame() {
        setGrid();
        setCellEvent();
    }

    private void generateLog() {
        InformationLog.createPlayerLog(humanPlayer);
        System.out.println("Se ha generado PlayerLog correctamente");
    }
}
