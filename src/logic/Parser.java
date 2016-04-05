package logic;

import controller.Commands;

import java.util.Arrays;

class Parser {

    private Commands command;

    String[] parse(String input) {
        String[] words = input.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }
        return words;
    }

    public boolean checkStringFormat(String[] query) throws WrongStringFormatException {
        boolean rightFormat = false;
        for (String s : query) {
            if (false) {
                throw new WrongStringFormatException("Wrong syntax : " + Arrays.toString(query));
            }
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
