package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Okno extends JFrame implements ActionListener  {

    // ustawiamy zmienne itp
    String[] SLiczby = {"1","2","3","4","5","6","7","8","9","e","0","π"};
    String[] Sznaki = { " +", " <=", " -","C ", " *", "= ", "/ ", "."};
    Color[] kolorL = {Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK,Color.YELLOW,Color.BLACK,Color.YELLOW};
    Color[] kolorZ = {Color.BLUE,Color.RED,Color.BLUE,Color.RED,Color.BLUE,Color.DARK_GRAY,Color.BLUE,Color.BLUE,};
    JButton[] Bliczby = new JButton[12];
    JButton[] Bznaki = new JButton[8];
    JButton Bprzypisz = new JButton();

    Font font = new Font("System", Font.BOLD, 30);
    String Swynik = "0";
    String Sprzypisz = "0";
    JTextField wyswietlWynik = new JTextField(Swynik);
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
        mOtworz.addActionListener(this);
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
        okno.getContentPane().setBackground(Color.RED);
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

    private void wstawLiczby(int i) {
        if (kolorL[i] == Color.BLACK ) {
            if (Swynik == "0") Swynik = Bliczby[i].getText();
            else
                Swynik += Swynik = Bliczby[i].getText();
            wyswietlWynik.setText(Swynik);
        }
        else if (i == 11) {
            String SPi = Double.toString(Math.PI);
            if (Swynik == "0"){
                Swynik =SPi;
                wyswietlWynik.setText(Swynik);
            }
            else{
                Swynik += Swynik = SPi;
                wyswietlWynik.setText(Swynik);
            }
        }

        else if (i == 9) {
            String Se = Double.toString(Math.E);
            if (Swynik == "0"){
                Swynik =Se;
                wyswietlWynik.setText(Swynik);
            }
            else{
                Swynik += Swynik = Se;
                wyswietlWynik.setText(Swynik);
            }
        }
    }


    private void wstawZnaki(int i) throws ScriptException {
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
            Swynik = Oblicz(Swynik);
            wyswietlWynik.setText(Oblicz(Swynik));
        }
    }

    private String Oblicz(String liczymy) throws ScriptException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("JavaScript");
        Object wynik = engine.eval(Swynik);
        liczymy = wynik.toString();
        return liczymy;
    }
    // tutaj ustawiamy to co ma robic dany guzik itp

    private void Wczytacz(){
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File plik = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(null,"Wybrany plik to" + plik.getAbsolutePath());
            try {
                Scanner scanner = new Scanner(plik);
                char[] x = scanner.next().toCharArray();
                for (int i = 0; i <x.length ; i++) {
                    if(x[i] != ' '){
                        Swynik = new String(x);
                        System.out.println(Swynik);
                        wyswietlWynik.setText(Swynik);
                        wyswietlWynik.setText(Swynik);
                        System.out.println(scanner);
                        System.out.println(Swynik);
                    }
                    else break;
                }
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == mOtworz) Wczytacz();

        for (byte i = 0; i < Bliczby.length; i++) if (source == Bliczby[i]) wstawLiczby(i);
        for (byte i = 0; i < Bznaki.length; i++) if (source == Bznaki[i]) {
            try {
                wstawZnaki(i);
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }

    }

}
