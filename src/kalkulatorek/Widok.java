package kalkulatorek;


import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;


public class Widok extends JFrame {

    // ustawiamy zmienne itp
    private final JButton[] Bliczby = new JButton[12];
    private final JButton[] Bznaki = new JButton[8];
    private OknoPrzypisz oknoPrzypisz;

    private final Kontroler kontroler = new Kontroler(this);

    private String Swynik = " ";
    private final JTextField wyswietlWynik = new JTextField(Swynik);


    // konstruktor odpowiedzialny za glowne okienko tego programu
    public Widok() {
        setSize(510, 530);
        setTitle("Kalkulator");
        setLayout(null);

        // wuswietla wynik
        setUpWynik();

        // wywoluje metode Guziki
        guziki();

        // wywoluje metode MenuB
        MenuB();

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
    }

    private void setUpWynik() {
        wyswietlWynik.setBounds(35, 20, 400, 50);
        wyswietlWynik.setEditable(false);
        wyswietlWynik.setFont(Utils.font);
        add(wyswietlWynik);
    }

    private void setOtworzButton(JMenu menuPlik) {
        JMenuItem mOtworz = new JMenuItem("Otwórz plik ");

        mOtworz.addActionListener(e -> {
            String s = kontroler.wczytaj();
            Swynik = s;
            wyswietlWynik.setText(Swynik);
        });

        menuPlik.add(mOtworz);
    }

    private void setNarzKolor(JMenu menuNarzedzia) {
        JMenuItem mNarzKolor = new JMenuItem("zmiana koloru");

        mNarzKolor.addActionListener(e -> {
            Color kolor2 = JColorChooser.showDialog(null, "", Color.WHITE);
            getContentPane().setBackground(kolor2);
        });

        menuNarzedzia.add(mNarzKolor);
    }


    private void setNarzKwadrat(JMenu menuNarzedzia) {
        JMenuItem mNarzKwadrat = new JMenuItem("liczenie dla funkcji kwadratowej");
        mNarzKwadrat.addActionListener(e -> new Kwadratowa());
        menuNarzedzia.add(mNarzKwadrat);
    }

    private void setNarzPrzypisz(JMenu menuNarzedzia) {
        JMenuItem mNarzPrzypisz = new JMenuItem("Przypisz");
        mNarzPrzypisz.addActionListener(e -> oknoPrzypisz = new OknoPrzypisz());
        menuNarzedzia.add(mNarzPrzypisz);
    }

    private void setPomProg(JMenu menuPomoc) {
        JMenuItem mOprogramie = new JMenuItem("O programie");
        mOprogramie.addActionListener(e -> JOptionPane.showMessageDialog(null, "Jest to kalkulator który potrafi uzywac znakow specjalnych takich jak e oraz π \n" +
                "można przypisać własną wartość do 'n' \n " +
                "obliczyc miejsca zerowe funkcji kwadratowej\n"+
                "wczytac dane z pliku  \n" +
                " a nawet zmienić kolor na wybrany przez użytkownika ;) \n "+
                "został on wykonany przez  zespół 6 grupa 102"));
        menuPomoc.add(mOprogramie);
    }

    private void MenuB() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuPlik = new JMenu("Plik");
        JMenu menuNarzedzia = new JMenu("Narzędzia");
        JMenu menuPomoc = new JMenu("Pomoc");

        menuBar.add(menuPlik);
        menuBar.add(menuPomoc);
        menuBar.add(menuNarzedzia);

        setOtworzButton(menuPlik);

        setNarzKolor(menuNarzedzia);
        setNarzKwadrat(menuNarzedzia);
        setNarzPrzypisz(menuNarzedzia);;


        setPomProg(menuPomoc);
    }

    private void guziki() {
        for (byte i = 0; i < Utils.SLiczby.length; i++) {
            Bliczby[i] = new JButton(Utils.SLiczby[i]);
            add(Bliczby[i]);
        }

        // guziki znaków
        for (byte i = 0; i < Utils.Sznaki.length; i++) {
            Bznaki[i] = new JButton(Utils.Sznaki[i]);
            add(Bznaki[i]);
        }
        akcjaGuzik();


        // ustawia guziki liczb
        byte index1 = 0;
        for (byte y = 0; y < 4; y++)
            for (byte x = 0; x < 3; x++) {
                Bliczby[index1].setBounds(35 + (x * 85), 80 + (y * 75), 80, 70);
                Bliczby[index1].setFont(Utils.font);
                Bliczby[index1].setForeground(Utils.kolorL[index1]);
                index1++;
            }


        // ustawia guziki znaków
        byte index2 = 0;
        for (byte y = 0; y < 4; y++) {
            for (byte x = 0; x < 2; x++) {
                Bznaki[index2].setBounds(290 + (x * 85), 80 + (y * 75), 80, 70);
                Bznaki[index2].setFont(Utils.font);
                Bznaki[index2].setForeground(Utils.kolorZ[index2]);
                index2++;
            }

        }

        // Przypisz////////////////////////////////////////////////////////////////////////////
        JButton Bprzypisz = new JButton("Użyj: n");
        Bprzypisz.setBounds(35, 80 + 75 * 4, 80 * 5 + 20, 70);
        Bprzypisz.setFont(Utils.font);
        Bprzypisz.setForeground(Color.CYAN);
        add(Bprzypisz);
        Bprzypisz.addActionListener(e -> wstawPrzypisz());
        //koniec guzikow
    }

    private void wstawPrzypisz() {
        try{ Swynik += oknoPrzypisz.getSprzypisz();
            wyswietlWynik.setText(Swynik);}
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Wartość 'n' nie jest jeszcze ustalona");
            System.out.println(e);
        }
    }



    private void wstawLiczby(int i) {
        if (Swynik == "0") Swynik = Bliczby[i].getText();
        else
            Swynik += Swynik = Bliczby[i].getText();
        wyswietlWynik.setText(Swynik);
    }


    private void wstawZnaki(int i) throws ScriptException {
        if (Utils.kolorZ[i] == Color.BLUE) {
            if (Swynik == "0") Swynik = Bznaki[i].getText();
            else
                Swynik += Swynik = Bznaki[i].getText();
            wyswietlWynik.setText(Swynik);
        } else if (i == 3) {
            Swynik = " ";
            wyswietlWynik.setText(Swynik);
        } else if (i == 5) {
            Swynik = kontroler.oblicz(Swynik);
            wyswietlWynik.setText(kontroler.oblicz(Swynik));
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

    private void akcjaGuzik() {
        for (int i = 0; i < Utils.SLiczby.length; i++) {
            int finalI = i;
            Bliczby[i].addActionListener(e -> wstawLiczby(finalI));
        }
        for (byte i = 0; i < Utils.Sznaki.length; i++) {
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
    }

}