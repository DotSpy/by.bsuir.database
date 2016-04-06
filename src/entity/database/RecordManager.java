package entity.database;

import java.util.ArrayList;
import java.util.List;

public class RecordManager {
    private static RecordManager self = null;
    private List<Record> recordList = new ArrayList<Record>();

    private RecordManager() {
        super();
        self = this;
    }

    public static synchronized RecordManager getInstance() {
        return (self == null) ? new RecordManager() : self;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public void setRecordList(Record record) {
        recordList.add(record);
    }

    public List<Record> getRecordList() {
        return recordList;
    }
}
