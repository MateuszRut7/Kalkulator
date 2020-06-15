package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.util.regex.Pattern;

public class Kontroler {

    private Widok widok;
    private final Wczytacz wczytacz;

    // konstruktor klasy kontroler
    public Kontroler(Widok widok) {
        this.widok = widok;
        wczytacz = new Wczytacz();
    }

    /**
     * Metoda zajująca sie glownymi obliczeniami
     *
     * @return Zwraca  sprawdzony String, w odpoweidniej formie zależnej od poprawnosci zapisu  e oraz pi
     */

    String oblicz(String liczymy) throws ScriptException {

        boolean czyDziala = true;

        String numberPattern = "([-]?((((\\d\\.)|([1-9]+\\.?))\\d*)|0|e|π))";
        final Pattern pattern = Pattern.compile("^" + numberPattern + "([+*/-]" + numberPattern + ")*$");
        if (!pattern.matcher(liczymy.trim()).matches()) {
            JOptionPane.showMessageDialog(null, "Błąd, pamiętaj, że przy 'e' oraz 'π' nie może być znaków innych niż '+ - * / . ' \n" +
                    "a przy '0' nie wolno używać tego co wyżej z wyjątkiem kropeczki");
            System.out.println(liczymy);
            czyDziala = false;
        } else {
            System.out.println("dziala");
            System.out.println(liczymy);
        }


        // oblicza jesc jest e oraz pi jest dobrze wstawione

        if (czyDziala == true) {


            String Se = Double.toString(Math.E);
            String SPi = Double.toString(Math.PI);

            // odpowiada za podmiane pi w normalnym przypadku
            String actualValue = liczymy.replace("e", Se);
            liczymy = actualValue;
            String actualValue2 = liczymy.replace("π", SPi);
            liczymy = actualValue2;


            // zamienia String na kod
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine engine = sem.getEngineByName("JavaScript");
            Object wynik = engine.eval(liczymy);
            liczymy = wynik.toString();

        }
        return liczymy;

    }

    /**
     * Metoda zajmująca się przekazaniem tego co chcemy wczytac
     *
     * @return Zwraca to co chcemy wczytac z metody wczytaj
     */
    public String wczytaj() {
        return wczytacz.wczytaj();
    }
}



