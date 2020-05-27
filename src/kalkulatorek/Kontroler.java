package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kontroler{

    private Widok widok;
    private Wczytacz wczytacz;

    public Kontroler(Widok widok) {
        this.widok = widok;
        wczytacz = new Wczytacz(widok);
    }

    protected String Oblicz(String liczymy) throws ScriptException {
        char[] pomocnicza = liczymy.toCharArray();

        if (pomocnicza[0] == '0' && pomocnicza[1] != '.'){
            liczymy = liczymy.substring(1);
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
        return liczymy;
    }

    public void wczytaj() {
        wczytacz.Wczytaj();
    }
}



