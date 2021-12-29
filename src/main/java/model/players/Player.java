/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.players;

import game.Symbol;

public class Player {
    
    private final String name;
    private Symbol playerSymbol;

    public Player(String name, Symbol playerSymbol) {
        this.name = name;
        this.playerSymbol = playerSymbol;
    }

    public Player(String name) {
        this(name, null);
    }
    
    
    @Override 
    public boolean equals (Object obj){
        if(obj == this)
            return true;
        if(obj instanceof Player){
            Player p1 = (Player) obj;
            return p1.name.equals(this.name);
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }
    
    
}
