package kalkulatorek;

import javax.swing.*;
import java.awt.*;

public class KalkulatorMVC {

    public static String pomocnicza ="";

    public static void main(String[] args) {


        Widok widok = new Widok();
        widok.setVisible(true);
        widok.setLocationRelativeTo(null);
        widok.setResizable(false);
        widok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        widok.getContentPane().setBackground(Color.WHITE);

        while (true){
            if (pomocnicza == "kolor"){
                pomocnicza ="";
                Color kolor2 = JColorChooser.showDialog(null,"",Color.WHITE);
                widok.getContentPane().setBackground(kolor2);
            }
            System.out.println();

        }

    }

}


