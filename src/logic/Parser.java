package logic;

import controller.Commands;

public class Parser {

    private Commands command;

    public String[] parse(String input) {
        String[] words = input.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }
        return words;
    }

    public boolean checkStringFormat(String[] query) {
        boolean rightFormat = false;
        for (String s : query) {

        }
        return rightFormat;
    }

//    public Commands RunParser() {
//        scan_command_line();
//        split_command = scan_func.split_input(current_command, " ");
//        convert_lwr_case();
//        parse_cmmds();
//        return (action);
//    }
//
//    // Gets command from the user.
//    private void scan_command_line() {
//        out.output("> ");
//        current_command = scan_func.scan_in_line();
//    }
}
