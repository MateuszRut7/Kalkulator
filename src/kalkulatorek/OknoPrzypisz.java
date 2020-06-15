package kalkulatorek;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;


public class OknoPrzypisz extends JFrame {
    private String Sprzypisz = "";
    private final JTextField wpisujemy = new JTextField(Sprzypisz);

    /**
     * konstruktor zajmujący się tworzeniem okienka odpowiadającego za możliwość przypisania
     */

    public OknoPrzypisz() {
        setSize(300, 300);
        setTitle("Przypisanie");
        setLayout(null);
        setVisible(true);
        setResizable(false);

        wpisujemy.setBounds(40, 20, 230, 30);
        add(wpisujemy);

        JLabel tekst = new JLabel("n=");
        tekst.setBounds(10, 10, 250, 50);
        Font font = new Font("System", Font.BOLD, 20);
        tekst.setFont(font);
        add(tekst);

        Button zatwierdz = new Button("zatwierdź");
        zatwierdz.setBounds(110, 130, 70, 60);
        zatwierdz.setBackground(Color.green);
        add(zatwierdz);
        zatwierdz.addActionListener(e -> {
            Sprzypisz = wpisujemy.getText();
            Sprzypisz = filtr(Sprzypisz);
        });
    }

    /**
     * Metoda get zajmująca się przypisaniem
     *
     * @return zwraca to co chcemy przypisać
     */


    public String getSprzypisz() {
        return Sprzypisz;
    }


    /**
     * Metoda zajmująca się sprawdzaniem czy to co chcemy przypisać ma poprawne znaki
     *
     * @return Zwraca to co chcemy przypisać pod warunkiem, iż nie zostały użyte niedozwolone znaki
     */


    private String filtr(String doFiltrowania) {

        final Pattern pattern = Pattern.compile("^[-]?((((\\d\\.)|([1-9]+\\.?))\\d*)|0)$");
        if (!pattern.matcher(doFiltrowania).matches()) {
            JOptionPane.showMessageDialog(null, "niestety używasz złych znaków sprawdź jeszcze raz czy wszystko zostało dobrze wpisane. Pamiętaj by używać kropki a nie przecinka oraz by nie uzywac spacji");
            doFiltrowania = "0";
        } else {
            doFiltrowania = wpisujemy.getText();
        }

        JOptionPane.showMessageDialog(null, "wartość n wynosi: " + doFiltrowania);

        return doFiltrowania;
    }

}
