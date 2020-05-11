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
        Boolean flagaEPi = true;
        char[] x = liczymy.toCharArray();
        char[] znaczki = {'+','-','*','/'};
        for (int i = 0; i <x.length ; i++) {
                if(x[i] == 'e'){

                        if (x[i-1] !='+' && x[i-1] !='-' && x[i-1] !='*' && x[i-1] !='/' && x[i-1] !='e' && x[i+1] !='+' && x[i+1] !='-' && x[i+1] !='*' && x[i+1] !='/' && x[i+1] !='e') {

                            flagaEPi = false;
                        }
                }

            }
        if(flagaEPi) {
            String actualValue = liczymy.replace("e", Se);
            liczymy = actualValue;
        }

        // odpowiada za podmiane e oraz pi w zlym przypadku
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                String actualValue3 = liczymy.replace(i+"e"+j, "blad prosze pamietac o znakach");

            }
        }

        for (int i = 0; i <10 ; i++) {
            String actualValue = liczymy.replace(i+"e", "0");
            String actualValue2 = liczymy.replace("e"+i, "0");

        }
        // odpowiada za podmiane pi w normalnym przypadku
        String actualValue2 = liczymy.replace("π", SPi);
        liczymy = actualValue2;


        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        Object wynik = engine.eval(liczymy);
        liczymy = wynik.toString();
        return liczymy;
    }


}
