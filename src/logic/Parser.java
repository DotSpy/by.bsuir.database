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
}
