
package TestGame;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.MinimaxTree;
import ec.edu.espol.model.MinimaxTreeNode;
import game.Symbol;
import java.util.ArrayList;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class TestGame {
    public static void main(String[] args) {
        Player humanPlayer = new Player("Eduardo", Symbol.X);
        Player AIplayer = new Player("AI",Symbol.O);
        
        Grid game =  new Grid(3, 3, 300, 300);
        /*Jugadas jugador*/
        ArrayList<ArrayList<Cell>> tableroQuemado = new ArrayList<>();
        
        for(int f = 0; f < 3; f++){
            tableroQuemado.add(new ArrayList<>());
        }
        
        /*Generate grid*/
        for(int f = 0; f < 3; f++){
            for(int c = 0; c < 3; c++){
                Cell cell = new Cell(c, f);   /*Se crea con la posicion en (y,x)*/
                cell.setLayout(300, 300, 3, 3);
                tableroQuemado.get(f).add(cell);
                game.getChildren().add(cell);
            }
        }
       
        tableroQuemado.get(1).get(1).setSymbol(Symbol.O);
        tableroQuemado.get(2).get(0).setSymbol(Symbol.X);
        tableroQuemado.get(2).get(2).setSymbol(Symbol.X);
        
        game.setGrid(tableroQuemado);
        
  
        
        /*tree()*/
        MinimaxTree tree = new MinimaxTree(game);
        tree.generateTree(AIplayer);
        /*fin tree*/
        
        /*Mostrar hijos*/
        MinimaxTreeNode<Grid> root = tree.getRoot();
        ArrayList<MinimaxTree> children = root.getChildren();
        tree.minimaxEasy(true, AIplayer);  /*de la maquina*/
        System.out.println("Minimax");
        for(MinimaxTree child: children){
            System.out.println("***************************");
            child.getRoot().getContent().showTablero();
            System.out.println(child.getRoot().getContent().getUtility());
        }
        /*Fin mostrar hijos*/
        /* CASO  1:
                                        | || || |
                                        | ||O|| |
                                        |X|| ||X|
        
        |O|| || |     | ||O|| |    | || ||O|    | || || |     | || || |    | || || |
        | ||O|| |     | ||O|| |    | ||O|| |    |O||O|| |     | ||O||O|    | ||O|| |
        |X|| ||X|     |X|| ||X|    |X|| ||X|    |X|| ||X|     |X|| ||X|    |X||O||X|
         
          Uo=-1         Uo=-1         Uo=-1       Uo=-2         Uo=-2        Uo=-2

            SIN GAME VALIDATOR                         CON GAME VALIDATOR
                |O|| || |                                   | || || |
                | ||O|| |                                   | ||O|| |
                |X|| ||X|                                   |X||O||X|
        
        Lo que se busca es que la maquina al ver los posibles tableros en los que pierde NO tomarlos
        en cuenta en la generacion del árbol
        
        */

        System.out.println("*****TABLERO FINAL*****");
        tree.minimax().showTablero();
        game = tree.minimax();
        System.out.println("*************");

       
        /*LAST CHILD*/
        Grid game2 =  new Grid(3, 3, 300, 300);
        /*Jugadas jugador*/
        ArrayList<ArrayList<Cell>> tableroQuemado2 = new ArrayList<>();
        
        for(int f = 0; f < 3; f++){
            tableroQuemado2.add(new ArrayList<>());
        }
        
        /*Generate grid*/
        for(int f = 0; f < 3; f++){
            for(int c = 0; c < 3; c++){
                Cell cell = new Cell(c, f);   /*Se crea con la posicion en (y,x)*/
                cell.setLayout(300, 300, 3, 3);
                tableroQuemado2.get(f).add(cell);
                game2.getChildren().add(cell);
            }
        }
        /*LAST CHILD
                ROOT
                    \
                     \
                        | || || |
                        | ||O|| |
                        |X||O||X|
                            \
                             \
                              \   | ||X|| |
                                  | ||O|| |     Uo = -2
                                  |X||O||X|
        */
        
       
        tableroQuemado2.get(0).get(1).setSymbol(Symbol.X);
        tableroQuemado2.get(1).get(1).setSymbol(Symbol.O);
        tableroQuemado2.get(2).get(0).setSymbol(Symbol.X);
        tableroQuemado2.get(2).get(1).setSymbol(Symbol.O);
        tableroQuemado2.get(2).get(2).setSymbol(Symbol.X);
        game2.setGrid(tableroQuemado2);
        
        game2.generateUtility(Symbol.O, Symbol.X);
        //game2.showTablero();
        //System.out.println(game2.getUtility());
        
        
        
        /*FIRST CHILD*/
        Grid game3 =  new Grid(3, 3, 300, 300);
        /*Jugadas jugador*/
        ArrayList<ArrayList<Cell>> tableroQuemado3 = new ArrayList<>();
        
        for(int f = 0; f < 3; f++){
            tableroQuemado3.add(new ArrayList<>());
        }
        
        /*Generate grid*/
        for(int f = 0; f < 3; f++){
            for(int c = 0; c < 3; c++){
                Cell cell = new Cell(c, f);   /*Se crea con la posicion en (y,x)*/
                cell.setLayout(300, 300, 3, 3);
                tableroQuemado3.get(f).add(cell);
                game3.getChildren().add(cell);
            }
        }
        
        
        /*FIRST CHILD
                    ROOT
                       \
                        \
                        |O|| || |
                        | ||O|| |
                        |X||O||X|
                      /       
                     /        
            |O||X|| |
            | ||O|| |     Uo = -1
            |X|| ||X|
        */
        tableroQuemado3.get(0).get(0).setSymbol(Symbol.O);
        tableroQuemado3.get(0).get(1).setSymbol(Symbol.X);
        tableroQuemado3.get(1).get(1).setSymbol(Symbol.O);
        tableroQuemado3.get(2).get(0).setSymbol(Symbol.X);
        tableroQuemado3.get(2).get(2).setSymbol(Symbol.X);
        game3.setGrid(tableroQuemado3);
        
        game3.generateUtility(Symbol.O, Symbol.X);
        System.out.println(game3.getUtility());
        //game3.showTablero();
        
    }
}
