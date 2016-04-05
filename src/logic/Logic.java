package logic;

public class Logic {

    private QueryExecuter queryExecuter = new QueryExecuter();

    public String selectQuery(String query) {
        String result = "Command failed";
        queryExecuter.execute(query);
//        parser.parse(query);
        return result;
    }

    public String createQuery(String query) {
        String result = "Command failed";
        return result;
    }

    public String deleteQuery(String query) {
        String result = "Command failed";
        return result;
    }

    public String updateQuery(String query) {
        String result = "Command failed";
        return result;
    }
}
