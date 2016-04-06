package entity.database;

import logic.FileManager;

import java.util.ArrayList;
import java.util.List;

public class KeyManager {
    private static KeyManager self = null;
    private List<Key> keyList = new ArrayList<Key>();

    private KeyManager() {
        super();
        self = this;
    }

    public static synchronized KeyManager getInstance() {
        return (self == null) ? new KeyManager() : self;
    }

    public void setKeyList(List<Key> keyList) {
        this.keyList = keyList;
    }

    public void setKeyList(Key key) {
        keyList.add(key);
    }

    public List<Key> getKeyList() {
        return keyList;
    }

    public void fillKeyList() {
        FileManager fileManager = FileManager.getInstance();
        fileManager.
    }
}
