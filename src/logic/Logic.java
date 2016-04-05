package logic;

public class Logic {

    private Parser parser = new Parser();

    public String selectQuery(String query) {
        String result = "Command failed";
        String[] parsedQue = parser.parse(query);
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
