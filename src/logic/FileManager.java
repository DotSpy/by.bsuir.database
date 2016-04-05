package logic;

import com.sun.istack.internal.NotNull;

import java.io.File;

public class FileManager {

    @NotNull
    private File keyValueFile;
    @NotNull
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
