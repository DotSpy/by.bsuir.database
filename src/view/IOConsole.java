package view;

import controller.Commands;
import controller.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOConsole {
    private static IOConsole self = null;

    private IOConsole() {
        super();//init
        self = this;
    }

    public static synchronized IOConsole getInstance() {
        return (self == null) ? new IOConsole() : self;
    }

    Controller controller = new Controller();

    public void startConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter command");
        String s = "";
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String arr[] = s.split(" ", 2);
        String command = arr[0];
        try {
            controller.execute(Commands.valueOf(command), s);
        } catch (java.lang.IllegalArgumentException ex) {
            System.out.println("Wrong command");//TODO help list
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
