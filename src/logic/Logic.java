package logic;

import dao.FileManager;
import entity.database.Key;
import entity.database.Record;

import java.util.stream.Collectors;

public class Logic {

    private Parser parser = new Parser();
    private FileManager fm = FileManager.getInstance();

    public String selectQuery(String query) {
        String[] parsedQue = parser.parse(query);
        if (parsedQue[0].toUpperCase().equals("ALL")) {
            return String.join(", ", fm.readAllRecords().stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
        } else {
            try {
                return fm.readRecordFromBinaryFile(Integer.parseInt(parsedQue[0])).toString();
            } catch (NumberFormatException e) {
                return "ID must be a number";
            }
        }
    }

    public String createQuery(String query) {
        String result = "Command failed";
        Record r = new Record();
        Key k = new Key((fm.readKeyList().size() + 1));
        r.setId(k);
        r.setCharField(query);
        fm.writeRecordToBinary(r);
        return result;
    }

    public String deleteQuery(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
        return result;
    }

    public String updateQuery(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
        return result;
    }

    public String setAddress(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
        return result;//hostName, int portNumber
    }
}
