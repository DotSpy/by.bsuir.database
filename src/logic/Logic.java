package logic;

public class Logic {

    private Parser parser = new Parser();

    public String selectQuery(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
        if (parsedQue[0].toLowerCase().equals("all")) {
//            result = fm.
        }
//        parser.parse(query);
        return result;
    }

    public String createQuery(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
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
