/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.log;

import ec.edu.espol.model.Cell;
import ec.edu.espol.model.Grid;
import ec.edu.espol.model.MinimaxTree;
import ec.edu.espol.model.MinimaxTreeNode;
import game.Symbol;
import java.util.ArrayList;
import model.players.Player;

/**
 *
 * @author Fernando
 */
public class tests {
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
        tableroQuemado.get(0).get(0).setSymbol(Symbol.X);
        tableroQuemado.get(0).get(1).setSymbol(Symbol.O);
        
        tableroQuemado.get(1).get(0).setSymbol(Symbol.O);
        tableroQuemado.get(1).get(1).setSymbol(Symbol.X);
        tableroQuemado.get(1).get(2).setSymbol(Symbol.O);
        
        tableroQuemado.get(2).get(0).setSymbol(Symbol.X);
        tableroQuemado.get(2).get(1).setSymbol(Symbol.X);
        
        System.out.println(tableroQuemado.get(1).size());
        game.setGrid(tableroQuemado);
        
        
        //game.generateGrid();
        game.showTablero();
        game.showTablero();
        
        /*tree()*/
        MinimaxTree tree = new MinimaxTree(game);
        tree.generateTree(AIplayer);
        /*fin tree*/
        
        /*Mostrar hijos*/
        MinimaxTreeNode<Grid> root = tree.getRoot();
        ArrayList<MinimaxTree> children = root.getChildren();
        System.out.println(children.size());
        System.out.println("CHILDREN");
        for(MinimaxTree child: children){
            System.out.println("***************************");
            child.getRoot().getContent().showTablero();
        }
        System.out.println("END CHILDREN");
        
        /*Fin mostrar hijos*/
               
        
        
        tree.minimax(true, AIplayer);  /*de la maquina*/
        System.out.println("Minimax");
        tree.minimax().showTablero();
        System.out.println("**********");
        game = tree.minimax();
        System.out.println("*************");
        game.showTablero();
    }
}
