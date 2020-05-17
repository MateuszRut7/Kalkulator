package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Model {

    protected static void Wczytacz(){
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File plik = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null,"Wybrany plik to" + plik.getAbsolutePath());
            try {
                Scanner scanner = new Scanner(plik);
                char[] x = scanner.next().toCharArray();

                Widok.Swynik = new String(x);
                Widok.wyswietlWynik.setText(Widok.Swynik);

                scanner.close();
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }



    protected static String Oblicz(String liczymy) throws ScriptException {
        String Se = Double.toString(Math.E);
        String SPi = Double.toString(Math.PI);

        // odpowiada za podmiane pi w normalnym przypadku
        String actualValue = liczymy.replace("e", Se);
        liczymy = actualValue;
        String actualValue2 = liczymy.replace("π", SPi);
        liczymy = actualValue2;


        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        Object wynik = engine.eval(liczymy);
        liczymy = wynik.toString();
        return liczymy;
    }


}
