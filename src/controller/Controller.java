package controller;

import com.sun.istack.internal.NotNull;
import logic.Logic;
import view.IOConsole;

public class Controller {

    @NotNull
    public void execute(Commands command, String query) {
        Logic logic = new Logic();
        IOConsole ioConsole = IOConsole.getInstance();
        switch (command) {
            case SELECT:
                ioConsole.printMessage(logic.selectQuery(query));
                break;
            case DELETE:
                ioConsole.printMessage(logic.deleteQuery(query));
                break;
            case UPDATE:
                ioConsole.printMessage(logic.updateQuery(query));
                break;
            case CREATE:
                ioConsole.printMessage(logic.createQuery(query));
                break;
            case SET_ADDRESS:
                ioConsole.printMessage(logic.setAddress(query));
                break;
            default:
                ioConsole.printMessage("Command currently in development");
                break;
        }
    }
}
