package logic;

public class QueryExecuter {

    FileManager fileManager = FileManager.getInstance();
    Parser parser = new Parser();

    public String execute(String query) {
        String status = "NF";
        String[] parsedQuery = parser.parse(query);
        parsedQuery[0]
        return status;
    }

}