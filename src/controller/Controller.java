package controller;

import com.sun.istack.internal.NotNull;
import logic.Logic;
import view.IOConsole;

public class Controller {

    private Logic logic = new Logic();

    @NotNull
    public void execute(Commands command, String query) {
        switch (command) {
            case SELECT:
                logic.selectQuery(query);
                break;
            case DELETE:
                logic.deleteQuery(query);
                break;
            case UPDATE:
                logic.updateQuery(query);
                break;
            case CREATE:
                logic.createQuery(query);
                break;
            default:
                IOConsole ioConsole = IOConsole.getInstance();
                ioConsole.printMessage("Command currently in development");
                break;
        }
    }
}
