package kalkulatorek;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;


public class Widok extends JFrame {

    // ustawiamy zmienne itp
    String[] SLiczby = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "e", "0", "π"};
    String[] Sznaki = {"+", "<=", "-", "C ", "*", "= ", "/ ", "."};
    Color[] kolorL = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.YELLOW, Color.BLACK, Color.YELLOW};
    Color[] kolorZ = {Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.DARK_GRAY, Color.BLUE, Color.BLUE,};
    JButton[] Bliczby = new JButton[12];
    JButton[] Bznaki = new JButton[8];

    Kontroler kontroler = new Kontroler(this);

    private Font font = new Font("System", Font.BOLD, 30);
    String Swynik = "";
    JTextField wyswietlWynik = new JTextField(Swynik);
    JMenuBar menuBar = new JMenuBar();
    JMenu menuPlik, menuNarzedzia, menuPomoc;
    JMenuItem mOtworz, mNarzKolor, mNarzPrzypisz, mNarzKwadrat, mOprogramie;

    public Widok() {

        setSize(510, 530);
        setTitle("Kalkulator");
        setLayout(null);

        // wuswietla wynik
        wyswietlWynik.setBounds(35, 20, 400, 50);
        wyswietlWynik.setEditable(false);
        wyswietlWynik.setFont(font);
        add(wyswietlWynik);


        // wywoluje metode Guziki
        Guziki();

        // wywoluje metode MenuB
        MenuB();

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    void MenuB() {
        menuPlik = new JMenu("Plik");
        menuNarzedzia = new JMenu("Narzędzia");
        menuPomoc = new JMenu("Pomoc");

        setJMenuBar(menuBar);
        menuBar.add(menuPlik);
        menuBar.add(menuPomoc);
        menuBar.add(menuNarzedzia);

        mOtworz = new JMenuItem("Otwórz plik ");
        mNarzKolor = new JMenuItem("zmiana koloru");
        mNarzPrzypisz = new JMenuItem("Przypisz");
        mOprogramie = new JMenuItem("O programie");
        mNarzKwadrat = new JMenuItem("liczenie dla funkcji kwadratowej");

        menuPlik.add(mOtworz);
        menuNarzedzia.add(mNarzKolor);
        menuNarzedzia.add(mNarzPrzypisz);
        menuNarzedzia.add(mNarzKwadrat);
        menuPomoc.add(mOprogramie);

        AkcjaMenuB();
    }

    void Guziki() {
        for (byte i = 0; i < SLiczby.length; i++) {
            Bliczby[i] = new JButton(SLiczby[i]);
            add(Bliczby[i]);
        }

        // guziki znaków
        for (byte i = 0; i < Sznaki.length; i++) {
            Bznaki[i] = new JButton(Sznaki[i]);
            add(Bznaki[i]);
        }
        AkcjaGuzik();


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
        for (byte y = 0; y < 4; y++) {
            for (byte x = 0; x < 2; x++) {
                Bznaki[index2].setBounds(290 + (x * 85), 80 + (y * 75), 80, 70);
                Bznaki[index2].setFont(font);
                Bznaki[index2].setForeground(kolorZ[index2]);
                index2++;
            }

        }

        // Przypisz////////////////////////////////////////////////////////////////////////////
        OknoPrzypisz.Bprzypisz.setBounds(35, 80 + 75 * 4, 80 * 5 + 20, 70);
        OknoPrzypisz.Bprzypisz.setFont(font);
        OknoPrzypisz.Bprzypisz.setForeground(Color.CYAN);
        add(OknoPrzypisz.Bprzypisz);
        //koniec guzikow
    }

    void wstawPrzypisz() {
        Swynik += OknoPrzypisz.Sprzypisz;
        wyswietlWynik.setText(Swynik);
    }

    void wstawLiczby(int i) {
        if (Swynik == "0") Swynik = Bliczby[i].getText();
        else
            Swynik += Swynik = Bliczby[i].getText();
        wyswietlWynik.setText(Swynik);
    }


    void wstawZnaki(int i) throws ScriptException {
        if (kolorZ[i] == Color.BLUE) {
            if (Swynik == "0") Swynik = Bznaki[i].getText();
            else
                Swynik += Swynik = Bznaki[i].getText();
            wyswietlWynik.setText(Swynik);
        } else if (i == 3) {
            Swynik = "";
            wyswietlWynik.setText(Swynik);
        } else if (i == 5) {
            Swynik = kontroler.Oblicz(Swynik);
            wyswietlWynik.setText(kontroler.Oblicz(Swynik));
        } else if (i == 1) {
            try {
                CharSequence actualValue = Swynik.subSequence(0, Swynik.length() - 1);
                Swynik = (String) actualValue;
                wyswietlWynik.setText(Swynik);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Nie ma już co usuwać");
            }
        }
    }

    void AkcjaGuzik() {
        for (int i = 0; i < SLiczby.length; i++) {
            int finalI = i;
            Bliczby[i].addActionListener(e -> wstawLiczby(finalI));
        }
        for (byte i = 0; i < Sznaki.length; i++) {
            byte finalI = i;
            Bznaki[i].addActionListener(e -> {
                try {
                    wstawZnaki(finalI);
                } catch (ScriptException scriptException) {
                    System.out.println(scriptException);
                    JOptionPane.showMessageDialog(null,"coś tu jest nie tak");
                }
            });
        }
        OknoPrzypisz.Bprzypisz.addActionListener(e -> wstawPrzypisz());
    }

    void AkcjaMenuB() {
        mOtworz.addActionListener(e -> kontroler.wczytaj());
        mNarzKolor.addActionListener(e -> {
            Color kolor2 = JColorChooser.showDialog(null, "", Color.WHITE);
            getContentPane().setBackground(kolor2);
        });
        mNarzPrzypisz.addActionListener(e -> new OknoPrzypisz());
        mNarzKwadrat.addActionListener(e -> new Kwadratowa());
    }


}