package tests.log;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.MinimaxTree;
import game.Symbol;
import java.util.ArrayList;
import model.players.Player;
import playerlog.InformationLog;

/**
 *
 * @author eduar
 */
public class Main {
    public static void main(String[] args) {
//        Player player = new Player("Eduardo", Symbol.X);
//        player.setWins(10);
//        InformationLog.createPlayerLog(player);  
          //InformationLog i = new InformationLog();
          //i.LeerArchivo();
          
        ArrayList<ArrayList<Cell>> arreglo = new ArrayList<>();


          
          Grid g = new Grid(3, 3 ,100, 100);
          g.generateGrid();
          g.getGrid().get(0).get(0).setSymbol(Symbol.X);
          g.getGrid().get(1).get(1).setSymbol(Symbol.X);
          g.getGrid().get(0).get(1).setSymbol(Symbol.O);
          Player p1 = new Player("Fer", Symbol.X);
          MinimaxTree tree = new MinimaxTree(g);
          tree.generateTree(p1);
          Grid g2 = tree.minimax();
          Grid g3 = new Grid();
          System.out.println(g.getUtility());
    }
}
