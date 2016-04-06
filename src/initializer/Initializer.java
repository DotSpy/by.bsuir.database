package initializer;

import java.io.*;

//TODO add this class to installer
public class Initializer {
    private String valuesFilePath;
    private String keysFilePath;
    private static Initializer self = new Initializer();

    private Initializer() {
        super();
    }

    public static Initializer getInstance() {
        return self;
    }
    private void initializeFromZero() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(valuesFilePath = (new File(".").getAbsolutePath() + "/values"))), "utf-8"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(keysFilePath = (new File(".").getAbsolutePath() + "/keys"))), "utf-8"))) {
//            writer.write("something");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkInitialization() {
        if (!(new File(new File(".").getAbsolutePath() + "values").isFile()) && !(new File(new File(".").getAbsolutePath() + "keys").isFile())) {
            initializeFromZero();
        }//TODO return boolean and add interaction with it
    }

    private void setUpSocket(String hostName, int portNumber) {
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(new File(new File(".").getAbsolutePath() + "/address")), "utf-8"))) {
            writer.println(hostName);
            writer.println(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Socket kkSocket = new Socket(hostName, portNumber);//hostname 127.0.0.1 same machine
    }

    public String getKeysFilePath() {
        return keysFilePath;
    }

    public String getValuesFilePath() {
        return valuesFilePath;
    }
}
