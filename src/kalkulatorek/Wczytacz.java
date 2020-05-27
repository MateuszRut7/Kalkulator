package kalkulatorek;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wczytacz {

    private Widok widok;

    public Wczytacz(Widok widok) {
        this.widok = widok;
    }

    public void Wczytaj(){
        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File plik = fileChooser.getSelectedFile();

            try {
                Scanner scanner = new Scanner(plik);

                widok.Swynik = scanner.next();
                widok.wyswietlWynik.setText(widok.Swynik);

                JOptionPane.showMessageDialog(null,"Wybrany plik to" + plik.getAbsolutePath());

                scanner.close();
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"nie ma takiego pliku, spróbuj jeszcze raz ");
            }
        }
    }
}
