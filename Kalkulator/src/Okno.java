import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Okno extends JFrame implements ActionListener  {

    // ustawiamy zmienne itp
    long liczba1, liczba2;
    String[] SLiczby = {"1","2","3","4","5","6","7","8","9","e","0","π"};
    String[] Sznaki = { " +", " <=", " -","C ", " *", "= ", "/ ", "."};
    Color[] kolorL = {Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.YELLOW,Color.BLACK,Color.YELLOW};
    Color[] kolorZ = {Color.BLUE,Color.RED,Color.BLUE,Color.RED,Color.BLUE,Color.BLUE,Color.BLUE,Color.BLUE,};
    JButton[] Bliczby = new JButton[12];
    JButton[] Bznaki = new JButton[8];

    Font font = new Font("System", Font.BOLD, 30);
    String wynik = "0";
    JTextField wyswietlWynik = new JTextField(wynik);
    JMenuBar menuBar = new JMenuBar();
    JMenu menuPlik, menuNarzedzia, menuPomoc;
    JMenuItem mOtworz, mNarz1, mNarz2, mOprogramie;
    static Okno okno = new Okno();

    public void MenuB(){
        menuPlik = new JMenu("Plik");
        menuNarzedzia = new JMenu("Narzędzia");
        menuPomoc = new JMenu("Pomoc");

        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        menuBar.add(menuPomoc);
        menuBar.add(menuNarzedzia);

        mOtworz = new JMenuItem("Otwórz plik ");
        mNarz1 = new JMenuItem("Narz1");
        mNarz2 = new JMenuItem("Narz2");
        mOprogramie = new JMenuItem("O programie");

        menuPlik.add(mOtworz);
        menuNarzedzia.add(mNarz1);
        menuNarzedzia.add(mNarz2);
        menuPomoc.add(mOprogramie);
    }

    public void Guziki(){
        for (byte i = 0; i < SLiczby.length; i++) {
            Bliczby[i] = new JButton(SLiczby[i]);
            add(Bliczby[i]);
            Bliczby[i].addActionListener(this);
        }

        // guziki znaków
        for (byte i = 0; i < Sznaki.length; i++) {
            Bznaki[i] = new JButton(Sznaki[i]);
            add(Bznaki[i]);
            Bznaki[i].addActionListener(this);
        }


        // ustawia guziki liczb
        byte index1 = 0;
        for (byte y = 0; y < 4; y++)
            for (byte x = 0; x < 3; x++) {
                Bliczby[index1].setBounds(35 + (x * 85), 80 + (y * 75), 80, 70);
                Bliczby[index1].setFont(font);
                Bliczby[index1].setForeground(kolorL[index1]);
                index1++;
            }


        // ustawia guziki znaków
        byte index2 = 0;
        for (byte y = 0; y < 4; y++)
            for (byte x = 0; x < 2; x++) {
                Bznaki[index2].setBounds(290 + (x * 85), 80 + (y * 75), 80, 70);
                Bznaki[index2].setFont(font);
                Bznaki[index2].setForeground(kolorZ[index2]);
                index2++;
            }
        //koniec guzikow
    }

    public static void main(String[] args) {
        // rzeczy odpowiadajace za glupoty w stylu uzytkownik nie moze sam zmieniac wielkosci okienka
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        okno.setLayout(null);
    }

    public Okno() {

        setSize(510, 450);
        setTitle("Kalkulator");
        setLayout(null);

        // wuswietla wynik
        wyswietlWynik.setBounds(35,20,400,50);
        wyswietlWynik.setEditable(false);
        wyswietlWynik.setFont(font);
        add(wyswietlWynik);



        // wywoluje metode Guziki
        Guziki();

        // wywoluje metode MenuB
        MenuB();
    }
    
    // tutaj ustawiamy to co ma robic dany guzik itp

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();


    }


}
