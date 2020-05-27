package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;

public class Kontroler {

    private Widok widok;
    private final Wczytacz wczytacz;

    public Kontroler(Widok widok) {
        this.widok = widok;
        wczytacz = new Wczytacz();
    }

    String oblicz(String liczymy) throws ScriptException {
        char[] liczToChar = liczymy.toCharArray();
        int  licznikZer = 0;

        boolean czyDziala = true;


        try {
            for (int i = 0; i <liczToChar.length ; i++) {
                    if (liczToChar[i] =='e' || liczToChar[i] =='π') {
                        if ((liczToChar[i-1] !='+' && liczToChar[i-1] !='-' && liczToChar[i-1] !='*' && liczToChar[i-1] !='/') ||
                                (liczToChar[i+1] !='+' && liczToChar[i+1] !='-' && liczToChar[i+1] !='*' && liczToChar[i+1] !='/')){
                            czyDziala = false;
                            JOptionPane.showMessageDialog(null, "blad jest w tym miejscu   " + liczToChar[i] + "   obok tego jest zły znak");
                        }
                    }
            }
        }

        catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }



        if (czyDziala == true){
            // naprawa błędu związanego z 0123, najpewniej problem polegał na tym, że eval rozumiał ten zapis jako ósemkowy
            try {
                if (liczToChar[0] == '0'){
                    for (int i = 0; i <liczToChar.length ; i++) {
                        if (liczToChar[i] == '0' && liczToChar[i+1] != '*' && liczToChar[i+1] != '/') {
                            licznikZer++;
                        }
                        if (liczToChar[i] != '0')
                            break;
                    }
                    liczymy = liczymy.substring(licznikZer);

                }
            }
            catch (ArrayIndexOutOfBoundsException ex){
                System.out.println(ex);

            }



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

        }
        return liczymy;

    }

    public String wczytaj() {
        return wczytacz.wczytaj();
    }
}



