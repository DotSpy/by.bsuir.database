package initializer;

import java.io.*;

public class Initializer {

    private void initializeFromZero() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(new File(".").getAbsolutePath() + "/values")), "utf-8"))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(new File(".").getAbsolutePath() + "/keys")), "utf-8"))) {
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

}
