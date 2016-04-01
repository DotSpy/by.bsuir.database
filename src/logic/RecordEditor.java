package logic;

import entity.database.Record;

import java.util.List;

public class RecordEditor {

    private List<Record> recordList;

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public void addRecord(Record r) {
        this.recordList.add(r);
    }
}
