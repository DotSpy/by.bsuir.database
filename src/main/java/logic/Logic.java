package logic;

import dao.FileManager;
import database.Key;
import database.Record;

import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Logic {

    static Logger log = Logger.getLogger(Logic.class.getName());
    private Parser parser = new Parser();
    private FileManager fm = FileManager.getInstance();

    public String selectQuery(String query) {
        String[] parsedQue = parser.parse(query);
        if (parsedQue[1].toUpperCase().equals("ALL")) {
            log.info(query);
            return String.join(", ", fm.readAllRecords().stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
        } else {
            try {
                if (fm.readRecordFromBinaryFile(Integer.parseInt(parsedQue[1])) == null)
                    return "No such key";
                log.info(query);
                return fm.readRecordFromBinaryFile(Integer.parseInt(parsedQue[1])).toString();
            } catch (NumberFormatException e) {
                return "ID must be a number";
            }
        }
    }

    public String createQuery(String query) {
        if (parser.getParams(query) == null || parser.getParams(query).equals("")) {
            return "Bad args";
        }
        log.info(query);
        Record r = new Record();
        Key k = new Key((fm.readKeyList().size() + 1));
        r.setKey(k);
        r.setCharField(parser.getParams(query));
        fm.writeRecordToBinary(r, false);
        return "Added: " + parser.getParams(query);
    }

    public String deleteQuery(String query) {
        String[] parsedQue = parser.parse(query); // TODO: DELETE ALL
        if (parsedQue[1].toUpperCase().equals("ALL")) {
            log.info(query);
            fm.deleteAll();
            return "Deleted all records";
        } else {
            try {
                log.info(query);
                fm.deleteRecord(Integer.parseInt(parsedQue[1]));
                return "Deleted record with id = " + Integer.parseInt(parsedQue[1]);
            } catch (NumberFormatException e) {
                return "ID must be a number";
            }
        }
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
