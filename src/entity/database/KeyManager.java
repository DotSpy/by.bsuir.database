package entity.database;

import java.util.ArrayList;
import java.util.List;

public class KeyManager {
    private static KeyManager self = null;

    private KeyManager() {
        super();
        self = this;
    }

    public static synchronized KeyManager getInstance() {
        return (self == null) ? new KeyManager() : self;
    }

    private List<Key> keyList = new ArrayList<Key>();

    public void setKeyList(List<Key> keyList) {
        this.keyList = keyList;
    }

    public void setKeyList(Key key) {
        keyList.add(key);
    }

    public List<Key> getKeyList() {
        return keyList;
    }
}
