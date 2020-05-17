package kalkulatorek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kwadratowa extends JFrame implements ActionListener {

    private JTextField wpisujemyA = new JTextField("0");
    private JTextField wpisujemyB = new JTextField("0");
    private JTextField wpisujemyC = new JTextField("0");

    private JLabel tekstA = new JLabel("współczynnik a : ");
    private JLabel tekstB = new JLabel("współczynnik b : ");
    private JLabel tekstC = new JLabel("współczynnik c : ");


    private Font font = new Font("System", Font.BOLD, 20);

    private Button zatwierdz;



    public Kwadratowa(){
        setSize(300, 300);
        setTitle("Przypisanie");
        setLayout(null);
        setVisible(true);

        wpisujemyA.setBounds(200,20,50,30);
        add(wpisujemyA);

        wpisujemyB.setBounds(200,20+35,50,30);
        add(wpisujemyB);

        wpisujemyC.setBounds(200,20+35*2,50,30);
        add(wpisujemyC);

        tekstA.setBounds(20,20-10,250,50);
        tekstA.setFont(font);
        add(tekstA);

        tekstB.setBounds(20,20+35-10,250,50);
        tekstB.setFont(font);
        add(tekstB);

        tekstC.setBounds(20,20+35*2-10,250,50);
        tekstC.setFont(font);
        add(tekstC);

        zatwierdz = new Button("zatwierdź");
        zatwierdz.setBounds(110,130,70,60);
        zatwierdz.setBackground(Color.green);
        add(zatwierdz);
        zatwierdz.addActionListener(this);
    }

    private void Liczymy(){

        float A =  Float.parseFloat(wpisujemyA.getText());
        float B =  Float.parseFloat(wpisujemyB.getText());
        float C =  Float.parseFloat(wpisujemyC.getText());

        float delta = (B*B) - (4*(A*C));

        double x1 = (-B - Math.sqrt(delta)) / (2 * A);
        double x2 = (-B + Math.sqrt(delta)) / (2 * A);

        double x0 = (-B/(2*A));

        if (delta<0)
            JOptionPane.showMessageDialog(null,"delta mniejsza od zera nie ma miejsc zerowych w liczbach rzeczywistych");
        else if(delta ==0)
            JOptionPane.showMessageDialog(null,"delta jest równa 0, więc jest jedno miejsce zerowe X0 = "+ x0);
        else
            JOptionPane.showMessageDialog(null,"delta jest większa od zera, więc są dwa miejsca zerowe X1  = "+ x1 + "           " + "Oraz X2 =   " + x2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Liczymy();

        }
}
