package model.players;

import game.Symbol;

/**
 *
 * @author Fernando
 */

public class Player {
    
    private final String name;
    private Symbol playerSymbol;
    private int wins;

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public Player(String name, Symbol playerSymbol) {
        this.name = name;
        this.playerSymbol = playerSymbol;
        this.wins = 0;
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
    
    public Symbol getOppoenentSymbol(){
        if(this.playerSymbol.equals(Symbol.X))
            return Symbol.O;
        else
            return Symbol.X;
    }

    
    
}
