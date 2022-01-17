package model.players;

import game.Symbol;

/**
 *
 * @author Fernando
 */

public class Player implements Comparable<Player> {
    
    private final String name;
    private Symbol playerSymbol;
    private int wins;
    private int ayudas;

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Player(String name, int wins) {
        this.name = name;
        this.wins = wins;
    }
    
    

    public Player(String name, Symbol playerSymbol) {
        this.name = name;
        this.playerSymbol = playerSymbol;
        this.wins = 0;
        this.ayudas = 2;
    }

    public Player(String name) {
        this(name, null);
    }
    
    
    public boolean equals(Player player2){ 
        return this.getName().equals(player2.getName());
    }

    public String getName() {
        return name;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }

    public void setPlayerSymbol(Symbol playerSymbol) {
        this.playerSymbol = playerSymbol;
    }
    
    public Symbol getOpponentSymbol(){
        if(this.playerSymbol.equals(Symbol.X))
            return Symbol.O;
        else
            return Symbol.X;
    }

    @Override
    public int compareTo(Player p) {
        if(wins < p.wins) return -1;
        if(wins > p.wins) return 1;
        return 0;
    }   

    @Override
    public String toString() {
        return "Nombre = " + name + "\tpuntaje = " + wins;
    }

    public int getAyudas() {
        return ayudas;
    }

    public void setAyudas(int ayudas) {
        this.ayudas = ayudas;
    }
    
    
}
