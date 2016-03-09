package entity.database;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private String table;
    private List<String> columnHeaders = new ArrayList<>();
    private List<Field> fieldList = new ArrayList<>();
    private int recKey;

}
