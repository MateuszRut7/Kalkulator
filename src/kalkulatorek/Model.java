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
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        Object wynik = engine.eval(liczymy);
        liczymy = wynik.toString();
        return liczymy;
    }


}
