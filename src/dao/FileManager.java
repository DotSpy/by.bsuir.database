package dao;

import com.sun.istack.internal.NotNull;
import entity.database.Key;
import entity.database.Record;
import initializer.Initializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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

//    public void addKeyToFile(Key key) {
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(keyValueFile.getPath()))) {
//            out.writeObject(key);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addRecordToFile(Record record) {
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(valueFile.getPath()))) {
//            out.writeObject(record);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Key> readKeyList() {
        List<Key> keys = new ArrayList<Key>();
        try {
            FileInputStream fileIn = new FileInputStream(keyValueFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            keys = (ArrayList<Key>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return keys;
    }

    public void writeKeys(List<Key> keys) {// TODO:http://stackoverflow.com/questions/4646272/appending-objects-to-a-binary-file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(keyValueFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(keys);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void writeRecords(List<Record> record) {// TODO:http://stackoverflow.com/questions/4646272/appending-objects-to-a-binary-file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(valueFile);
            for (Record r : record) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null)
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
