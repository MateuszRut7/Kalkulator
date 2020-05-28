package kalkulatorek;

import javax.swing.*;
import java.awt.*;


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
     * @return zwraca to co chcemy przypisać
     */


    public String getSprzypisz() {
        return Sprzypisz;
    }


    /**
     * Metoda zajmująca się sprawdzaniem czy to co chcemy przypisać ma poprawne znaki
     * @return Zwraca to co chcemy przypisać pod warunkiem, iż nie zostały użyte niedozwolone znaki
     */


    private String filtr(String doFiltrowania) {
        char[] pom = doFiltrowania.toCharArray();

        for (int i = 0; i < pom.length; i++) {
            if (pom[i] != '0' && pom[i] != '1' && pom[i] != '2' && pom[i] != '3' && pom[i] != '4' && pom[i] != '5' && pom[i] != '6' && pom[i] != '7' && pom[i] != '8' && pom[i] != '9' && pom[i] != 'e' && pom[i] != 'π' && pom[i] != '+' && pom[i] != '-' && pom[i] != '*' && pom[i] != '/' && pom[i] != '.') {
                JOptionPane.showMessageDialog(null, "niestety używasz złych znaków sprawdź jeszcze raz czy wszystko zostało dobrze wpisane. Pamiętaj by używać kropki a nie przecinka oraz by nie uzywac spacji");
                doFiltrowania = "0";
                break;
            } else {
                doFiltrowania = wpisujemy.getText();
            }
        }
        JOptionPane.showMessageDialog(null, "wartość n wynosi: " + doFiltrowania);

        return doFiltrowania;
    }

}
