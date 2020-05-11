package kalkulatorek;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoPrzypisz extends JFrame implements ActionListener {
    protected static String Sprzypisz = "0";
    protected static JButton Bprzypisz = new JButton("Użyj: n");

    private JTextField wpisujemy = new JTextField(Sprzypisz);
    private JLabel tekst = new JLabel("n=");
    private  Font font = new Font("System", Font.BOLD, 20);
    private Button zatwierdz;



    public OknoPrzypisz(){
        setSize(300, 300);
        setTitle("Przypisanie");
        setLayout(null);
        setVisible(true);

        wpisujemy.setBounds(40,20,230,30);
        add(wpisujemy);

        tekst.setBounds(10,10,250,50);
        tekst.setFont(font);
        add(tekst);

        zatwierdz = new Button("zatwierdź");
        zatwierdz.setBounds(110,130,70,60);
        zatwierdz.setBackground(Color.green);
        add(zatwierdz);
        zatwierdz.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Sprzypisz = wpisujemy.getText();
        JOptionPane.showMessageDialog(null,"wartość n wynosi: " + Sprzypisz);
        this.setVisible(false);
    }
}
