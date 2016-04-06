package logic;

import com.sun.istack.internal.NotNull;
import entity.database.Key;
import entity.database.Record;
import initializer.Initializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FileManager {

    @NotNull
    private File keyValueFile;
    @NotNull
    private File valueFile;

    private static FileManager self = new FileManager();

    private FileManager() {
        super();
        Initializer initializer = Initializer.getInstance();
        initializer.checkInitialization();
        setKeyValueFile(initializer.getKeysFilePath());
        setValueFile(initializer.getValuesFilePath());
    }

    public static FileManager getInstance() {
        return self;
    }

    private void setKeyValueFile(String path) {
        keyValueFile = new File(path);
    }

    private void setValueFile(String path) {
        valueFile = new File(path);
    }

    public void addKeyToFile(Key key) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(keyValueFile.getPath()))) {
            out.writeObject(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRecordToFile(Record record) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(valueFile.getPath()))) {
            out.writeObject(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
