package logic;

import entity.database.Key;
import entity.database.Record;

import java.util.List;

public class RecordEditor {

    private static RecordEditor self = new RecordEditor();
    private FileManager fm = FileManager.getInstance();

    private RecordEditor() {
        super();
    }

    private List<Key> keyList;
    private List<Record> recordList;

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public synchronized void addRecord(Record r) {
        this.recordList.add(r);
        keyList.add(new Key(keyList.size() + 1));
    }

    public List<Key> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<Key> keyList) {
        this.keyList = keyList;
    }

}
