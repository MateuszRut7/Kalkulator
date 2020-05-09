package kalkulatorek;

import javax.script.ScriptException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Kontroler implements ActionListener {

    protected void AkcjaGuzik(){
        for (byte i = 0; i < Widok.SLiczby.length; i++) Widok.Bliczby[i].addActionListener(this);
        for (byte i = 0; i < Widok.Sznaki.length; i++) Widok.Bznaki[i].addActionListener(this);
    }

    protected void AkcjaMenuB(){
        Widok.mOtworz.addActionListener(this);
        Widok.mNarzKolor.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();


        if(source == Widok.mOtworz) Model.Wczytacz();

        if (source == Widok.mNarzKolor){
            KalkulatorMVC.pomocnicza = "kolor";

        }

        for (byte i = 0; i < Widok.Bliczby.length; i++) if (source == Widok.Bliczby[i]) Widok.wstawLiczby(i);
        for (byte i = 0; i < Widok.Bznaki.length; i++) if (source == Widok.Bznaki[i]) {
            try {
                Widok.wstawZnaki(i);
            } catch (ScriptException ex) {
                ex.printStackTrace();
            }
        }

    }
}



