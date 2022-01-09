package playerlog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
    public static void LeerArchivo(){
        try(BufferedReader lector = new BufferedReader(new FileReader(PATH))){
            String linea = "";
            lector.readLine();
            ArrayList<Player> lista = new ArrayList<>();
            while((linea = lector.readLine())!=null){
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int puntos = Integer.parseInt(datos[1]);
                Player p = new Player(nombre, puntos);
                lista.add(p);
                Collections.sort(lista);
                Collections.reverse(lista);
            }
            for(Player p : lista){
                System.out.println(p);
            }
        }catch(IOException e){
            System.out.println("Error al leer el archivo" +e);
        }  
    }
}
