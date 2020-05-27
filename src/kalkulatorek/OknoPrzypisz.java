package kalkulatorek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoPrzypisz extends JFrame implements ActionListener {
    protected static String Sprzypisz = "";
    protected static JButton Bprzypisz = new JButton("Użyj: n");
    private JTextField wpisujemy = new JTextField(Sprzypisz);
    private JLabel tekst = new JLabel("n=");
    private Font font = new Font("System", Font.BOLD, 20);
    private Button zatwierdz;


    public OknoPrzypisz() {
        setSize(300, 300);
        setTitle("Przypisanie");
        setLayout(null);
        setVisible(true);
        setResizable(false);

        wpisujemy.setBounds(40, 20, 230, 30);
        add(wpisujemy);

        tekst.setBounds(10, 10, 250, 50);
        tekst.setFont(font);
        add(tekst);

        zatwierdz = new Button("zatwierdź");
        zatwierdz.setBounds(110, 130, 70, 60);
        zatwierdz.setBackground(Color.green);
        add(zatwierdz);
        zatwierdz.addActionListener(this);
    }

    public void filtr(String doFiltrowania) {
        char[] pom = doFiltrowania.toCharArray();

        for (int i = 0; i < pom.length; i++) {
            if (pom[i] != '0' && pom[i] != '1' && pom[i] != '2' && pom[i] != '3' && pom[i] != '4' && pom[i] != '5' && pom[i] != '6' && pom[i] != '7' && pom[i] != '8' && pom[i] != '9' && pom[i] != 'e' && pom[i] != 'π' && pom[i] != '+' && pom[i] != '-' && pom[i] != '*' && pom[i] != '/' && pom[i] != '.') {
                JOptionPane.showMessageDialog(null, "niestety używasz złych znaków sprawdź jeszcze raz czy wszystko zostało dobrze wpisane. Pamiętaj by używać kropki a nie przecinka");
                doFiltrowania = "0";
                break;
            } else {
                doFiltrowania = wpisujemy.getText();
            }
        }
        JOptionPane.showMessageDialog(null, "wartość n wynosi: " + doFiltrowania);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Sprzypisz = wpisujemy.getText();
        filtr(Sprzypisz);
    }
}
