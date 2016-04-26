package entity.database;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;

public class Key implements Serializable {
    @NotNull
    private int id;

    public Key(int id) {
        this.id = id;
    }

    public Key() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Key{" +
                "id=" + id +
                '}';
    }
}
