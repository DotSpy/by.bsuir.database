package entity.database;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;

public class Record implements Serializable {
    @NotNull
    private Key id;
    @Nullable
    private String charField;
    @Nullable
    private Integer intField;

    public Record(String charField, Key id, Integer intField) {
        this.charField = charField;
        this.id = id;
        this.intField = intField;
    }

    public Record() {
    }
//TODO: обрыв связи hardbit в файл записать невыполненные комманды и при обрыве связи пинговать\
    public String getCharField() {

        return charField;
    }

    public void setCharField(String charField) {
        this.charField = charField;
    }

    public Integer getIntField() {
        return intField;
    }

    public void setIntField(Integer intField) {
        this.intField = intField;
    }

    public void setId(Key id) {
        this.id = id;
    }
}
