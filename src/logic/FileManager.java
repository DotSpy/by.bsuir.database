package logic;

import java.io.File;

public class FileManager {

    private File keyValueFile;
    private File valueFile;

    private static FileManager self = new FileManager();

    private FileManager() {
        super();
    }

    public static FileManager getInstance() {
        return self;
    }

    public void setKeyValueFile(String path) {
        keyValueFile = new File(path);
    }

    public void setValueFile(String path) {
        valueFile = new File(path);
    }
}
