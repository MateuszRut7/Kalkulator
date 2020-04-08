import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Okno extends JFrame implements ActionListener  {

    // ustawiamy zmienne itp
    long liczba1, liczba2;
    boolean plus, minus, razy, dziel;

    String[] Spola = {"1 ", " 2", " 3", " +", " <=", " 4", "5 ", " 6", " -","C ", " 7", " 8", "9 ", " *", "= ", "e ", " 0", " π", "/ ", "."};
    Color[] kolor = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.RED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.RED,
            Color.BLACK, Color.BLACK, Color.BLACK, Color.BLUE, Color.BLUE, Color.ORANGE, Color.BLACK, Color.ORANGE, Color.BLUE, Color.BLACK};
    JButton[] Bpola = new JButton[20];

    Font font = new Font("System", Font.BOLD, 30);
    String wynik = "0";
    JTextField wyswietlWynik = new JTextField(wynik);
    JMenuBar menuBar = new JMenuBar();
    JMenu menuPlik, menuNarzedzia, menuPomoc;
    JMenuItem mOtworz, mNarz1, mNarz2, mOprogramie;
    static Okno okno = new Okno();

    public static void main(String[] args) {
        // rzeczy odpowiadajace za glupoty w stylu uzytkownik nie moze sam zmieniac wielkosci okienka
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setVisible(true);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        okno.setLayout(null);
    }

    public Okno() {

        // okienko

        setSize(510, 450);
        setTitle("Kalkulator");
        setLayout(null);

        wyswietlWynik.setBounds(35,20,400,50);
        wyswietlWynik.setEditable(false);
        wyswietlWynik.setFont(font);
        add(wyswietlWynik);

        // guziki
        for (byte i = 0; i < Spola.length; i++) {
            Bpola[i] = new JButton(Spola[i]);
            add(Bpola[i]);
            Bpola[i].addActionListener(this);
        }


        // ustawia guziki
        byte index = 0;
        for (byte y = 0; y < 4; y++)
            for (byte x = 0; x < 5; x++) {
                Bpola[index].setBounds(35 + (x * 85), 80 + (y * 75), 80, 70);
                Bpola[index].setFont(font);
                Bpola[index].setForeground(kolor[index]);
                index++;
            }
        //koniec guzikow

        //menu

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

        // koniec menu
    }




    // tutaj ustawiamy to co ma robic dany guzik itp

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (byte i = 0; i < 20; i++) if (source == Bpola[i]) oblicz(i);

    }

    private void reset() {
        plus = false;
        minus = false;
        razy = false;
        dziel = false;
    }

    private void oblicz(int i) {
        if (kolor[i] == Color.BLACK && wynik.length() < 20) {
            if (wynik == "0") wynik = Bpola[i].getText();
            else
                wynik += wynik = Bpola[i].getText();
            wyswietlWynik.setText(wynik);
        } else if (i == 9) {
            wynik = "0";
            reset();
            wyswietlWynik.setText(wynik);
        } else if (i == 3) {
            if (plus) {
                liczba2 = Long.parseLong(wynik);
                liczba1 += liczba2;
                wynik = String.valueOf(liczba1);
                wyswietlWynik.setText(wynik);
                wynik = "0";
            } else {
                reset();
                plus = true;
                liczba1 = Long.parseLong(wynik);
                wynik = "0";
            }
        } else if (i == 8) {
            if (minus) {
                liczba2 = Long.parseLong(wynik);
                liczba1 -= liczba2;
                wynik = String.valueOf(liczba1);
                wyswietlWynik.setText(wynik);
                wynik = "0";
            } else {
                reset();
                minus = true;
                liczba1 = Long.parseLong(wynik);
                wynik = "0";
            }
        } else if (i == 13) {
            if (razy) {
                liczba2 = Long.parseLong(wynik);
                liczba1 *= liczba2;
                wynik = String.valueOf(liczba1);
                wyswietlWynik.setText(wynik);
                wynik = "0";
            } else {
                reset();
                razy = true;
                liczba1 = Long.parseLong(wynik);
                wynik = "0";
            }
        } else if (i == 18) {
            if (dziel) {
                liczba2 = Long.parseLong(wynik);
                liczba1 /= liczba2;
                wynik = String.valueOf(liczba1);
                wyswietlWynik.setText(wynik);
                wynik = "0";
            } else {
                reset();
                dziel = true;
                liczba1 = Long.parseLong(wynik);
                wynik = "0";
            }
        } else if (i == 14) {
            if (plus) {
                liczba2 = Long.parseLong(wynik);
                liczba1 += liczba2;
            } else if (minus) {
                liczba2 = Long.parseLong(wynik);
                liczba1 -= liczba2;
            } else if (razy) {
                liczba2 = Long.parseLong(wynik);
                liczba1 *= liczba2;
            } else if (dziel) {
                liczba2 = Long.parseLong(wynik);
                liczba1 /= liczba2;
            }
            wynik = String.valueOf(liczba1);
            reset();
            wyswietlWynik.setText(wynik);
        }

    }
}
