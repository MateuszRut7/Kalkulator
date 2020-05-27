package kalkulatorek;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wczytacz {

    /**
     * Metoda odpowiadająca za wczytanie z pliku.
     * @return Zwraca to co się znajdywało w pliku w postaci ciągu znaków
     */


    public String wczytaj() {
        JFileChooser fileChooser = new JFileChooser();
        String result = "";
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File plik = fileChooser.getSelectedFile();

            try {
                Scanner scanner = new Scanner(plik);

                result = scanner.next();

                JOptionPane.showMessageDialog(null, "Wybrany plik to" + plik.getAbsolutePath());

                scanner.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "nie ma takiego pliku, spróbuj jeszcze raz ");
            }
        }

        return result;
    }
}
