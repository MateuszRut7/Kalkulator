package kalkulatorek;

import javax.swing.*;
import java.awt.*;

public class Kwadratowa extends JFrame {

    private final JTextField wpisujemyA = new JTextField("0");
    private final JTextField wpisujemyB = new JTextField("0");
    private final JTextField wpisujemyC = new JTextField("0");

    //konstruktor odpowiedzialny za wstawienie okiena od funkcji

    public Kwadratowa() {
        setSize(300, 300);
        setTitle("Przypisanie");
        setLayout(null);
        setVisible(true);

        wpisujemyA.setBounds(200, 20, 50, 30);
        add(wpisujemyA);

        wpisujemyB.setBounds(200, 20 + 35, 50, 30);
        add(wpisujemyB);

        wpisujemyC.setBounds(200, 20 + 35 * 2, 50, 30);
        add(wpisujemyC);

        JLabel tekstA = new JLabel("współczynnik a : ");
        tekstA.setBounds(20, 20 - 10, 250, 50);
        Font font = new Font("System", Font.BOLD, 20);
        tekstA.setFont(font);
        add(tekstA);

        JLabel tekstB = new JLabel("współczynnik b : ");
        tekstB.setBounds(20, 20 + 35 - 10, 250, 50);
        tekstB.setFont(font);
        add(tekstB);

        JLabel tekstC = new JLabel("współczynnik c : ");
        tekstC.setBounds(20, 20 + 35 * 2 - 10, 250, 50);
        tekstC.setFont(font);
        add(tekstC);

        Button zatwierdz = new Button("zatwierdź");
        zatwierdz.setBounds(110, 130, 70, 60);
        zatwierdz.setBackground(Color.green);
        add(zatwierdz);
        zatwierdz.addActionListener(e -> liczymy());
    }

    // obliczenia zwiazane z miejscami zerowymi funkcji kwadratowej
    private void liczymy() {
        float A = Float.parseFloat(wpisujemyA.getText());
        float B = Float.parseFloat(wpisujemyB.getText());
        float C = Float.parseFloat(wpisujemyC.getText());

        float delta = (B * B) - (4 * (A * C));

        double x1 = (-B - Math.sqrt(delta)) / (2 * A);
        double x2 = (-B + Math.sqrt(delta)) / (2 * A);

        double x0 = (-B / (2 * A));

        if (delta < 0)
            JOptionPane.showMessageDialog(null, "delta mniejsza od zera nie ma miejsc zerowych w liczbach rzeczywistych");
        else if (delta == 0)
            JOptionPane.showMessageDialog(null, "delta jest równa 0, więc jest jedno miejsce zerowe X0 = " + x0);
        else
            JOptionPane.showMessageDialog(null, "delta jest większa od zera, więc są dwa miejsca zerowe X1  = " + x1 + "           " + "Oraz X2 =   " + x2);
    }

}
