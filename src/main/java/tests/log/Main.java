package tests.log;

import game.Symbol;
import model.players.Player;
import playerlog.InformationLog;

/**
 *
 * @author eduar
 */
public class Main {
    public static void main(String[] args) {
        Player player = new Player("Eduardo", Symbol.X);
        player.setWins(10);
        InformationLog.createPlayerLog(player);   
    }
}
