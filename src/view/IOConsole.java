package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOConsole {

    public void startConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter String");
        String s = "";
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
