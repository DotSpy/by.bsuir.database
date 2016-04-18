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
    private static File valueFile;

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

    public List<Record> readAllRecords() {
        File file = valueFile;
        List<Record> records = new ArrayList<>();
        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(valueFile));
                Record r;
                while (true) {
                    r = (Record) ois.readObject();
                    records.add(r);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return records;
    }

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
            for (Key k : keys) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(k);
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

    public static void writeRecordToBinary(Record record) {
        ObjectOutputStream out = null;
        try {
            if (!valueFile.exists()) out = new ObjectOutputStream(new FileOutputStream(valueFile));
            else out = new AppendingObjectOutputStream(new FileOutputStream(valueFile, true));
            out.writeObject(record);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeRecordToBinary(Record record, String filename) {
        File file = new File(filename);
        ObjectOutputStream out = null;
        try {
            if (!file.exists()) out = new ObjectOutputStream(new FileOutputStream(file));
            else out = new AppendingObjectOutputStream(new FileOutputStream(file, true));
            out.writeObject(record);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Record readRecordFromBinaryFile(int id) {
        Record r = null;
        if (valueFile.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(valueFile));
                int i = 0;
                while (id != i) {
                    r = (Record) ois.readObject();
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return r;
    }

    public void deleteRecord(int id) {
        String tmpName = valueFile + "temp";
        for (int i = 1; i <= 6; i++) {            //key.size
            if (i == id) {
                continue;
            }
            writeRecordToBinary(readRecordFromBinaryFile(i), tmpName);//write to tmp file
        }
        File oldFile = valueFile;
        oldFile.delete();
        File newFile = new File(tmpName);
        newFile.renameTo(oldFile);
    }
}
