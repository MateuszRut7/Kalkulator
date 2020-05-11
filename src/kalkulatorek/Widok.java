package kalkulatorek;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;


public class Widok extends JFrame  {

    // ustawiamy zmienne itp
    protected static String[] SLiczby = {"1","2","3","4","5","6","7","8","9","e","0","π"};
    protected static String[] Sznaki = { "+", "<=", "-","C ", "*", "= ", "/ ", "."};
    protected static Color[] kolorL = {Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.YELLOW,Color.BLACK,Color.YELLOW};
    protected static Color[] kolorZ = {Color.BLUE,Color.RED,Color.BLUE,Color.RED,Color.BLUE,Color.DARK_GRAY,Color.BLUE,Color.BLUE,};
    protected static JButton[] Bliczby = new JButton[12];
    protected static JButton[] Bznaki = new JButton[8];

    Kontroler k = new Kontroler();

    private Font font = new Font("System", Font.BOLD, 30);
    protected static String Swynik = "";
    protected static JTextField wyswietlWynik = new JTextField(Swynik);
    protected static JMenuBar menuBar = new JMenuBar();
    protected static JMenu menuPlik, menuNarzedzia, menuPomoc;
    protected static JMenuItem mOtworz,mNarzKolor, mNarzPrzypisz, mOprogramie;

    public void MenuB(){
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

        menuPlik.add(mOtworz);
        menuNarzedzia.add(mNarzKolor);
        menuNarzedzia.add(mNarzPrzypisz);
        menuPomoc.add(mOprogramie);

        k.AkcjaMenuB();
    }

    public void Guziki(){
        for (byte i = 0; i < SLiczby.length; i++) {
            Bliczby[i] = new JButton(SLiczby[i]);
            add(Bliczby[i]);
        }

        // guziki znaków
        for (byte i = 0; i < Sznaki.length; i++) {
            Bznaki[i] = new JButton(Sznaki[i]);
            add(Bznaki[i]);
        }
        k.AkcjaGuzik();


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
        for (byte y = 0; y < 4; y++){
            for (byte x = 0; x < 2; x++) {
                Bznaki[index2].setBounds(290 + (x * 85), 80 + (y * 75), 80, 70);
                Bznaki[index2].setFont(font);
                Bznaki[index2].setForeground(kolorZ[index2]);
                index2++;
            }

        }

        // Przypisz////////////////////////////////////////////////////////////////////////////
        OknoPrzypisz.Bprzypisz.setBounds(35,80+75*4,80*5+20,70);
        OknoPrzypisz.Bprzypisz.setFont(font);
        OknoPrzypisz.Bprzypisz.setForeground(Color.CYAN);
        add(OknoPrzypisz.Bprzypisz);
        //koniec guzikow
    }

    public Widok() {

        setSize(510, 530);
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

    protected static void wstawPrzypisz(){
        Swynik +=  OknoPrzypisz.Sprzypisz;
        wyswietlWynik.setText(Swynik);
    }

    protected static void wstawLiczby(int i) {
        if (Swynik == "0") Swynik = Bliczby[i].getText();
        else
            Swynik += Swynik = Bliczby[i].getText();
        wyswietlWynik.setText(Swynik);
    }


    protected static void wstawZnaki(int i) throws ScriptException {
        if (kolorZ[i] == Color.BLUE ) {
            if (Swynik == "0") Swynik = Bznaki[i].getText();
            else
                Swynik += Swynik = Bznaki[i].getText();
            wyswietlWynik.setText(Swynik);}
        else if (i == 3) {
            Swynik = "0";
            wyswietlWynik.setText(Swynik);
        }
        else if(i == 5){
            Swynik = Model.Oblicz(Swynik);
            wyswietlWynik.setText(Model.Oblicz(Swynik));
        }

        else if(i ==1){
            CharSequence actualValue = Swynik.subSequence(0, Swynik.length()-1);
            Swynik= (String) actualValue;
            wyswietlWynik.setText(Swynik);
        }
    }


}
