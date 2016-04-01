package entity.database;

import java.io.Serializable;
import java.util.List;

public class Record implements Serializable {

    private List<String> charField;
    private List<Integer> intField;

    public Record() {
    }

    public List<String> getCharField() {
        return charField;
    }

    public void setCharField(List<String> charField) {
        this.charField = charField;
    }

    public List<Integer> getIntField() {
        return intField;
    }

    public void setIntField(List<Integer> intField) {
        this.intField = intField;
    }

    public void addCharField(String s) {
        this.charField.add(s);
    }

    public void addIntField(Integer i) {
        this.intField.add(i);
    }
}
