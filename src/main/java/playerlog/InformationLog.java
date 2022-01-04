package playerlog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.players.Player;

/**
 *
 * @author eduar
 */
public class InformationLog {
    private static String PATH = "src/main/resources/logs/players.txt";
    
    
    public static void createPlayerLog(Player player) {
        try(FileWriter fw = new FileWriter(PATH, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)){
            
            out.println(player.getName() + "," + player.getWins());
            
            
        } catch (IOException ioe) {
            System.out.println("Uh oh, something went wrong :(");
        }
    }
}
