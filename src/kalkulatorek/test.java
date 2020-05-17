package kalkulatorek;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws ScriptException {
        String test = "Ala ma koty";
        char[] tak = test.toCharArray();
        for (int i = 0; i <tak.length ; i++) {
            System.out.println(tak[i]);
        }
    }

}
