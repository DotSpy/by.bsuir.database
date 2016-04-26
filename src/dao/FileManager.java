package dao;

import com.sun.istack.internal.NotNull;
import entity.database.Key;
import entity.database.Record;
import initializer.Initializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    @NotNull
    private static File keyValueFile;
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
//                e.printStackTrace();
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
        if (keyValueFile.length() == 0 || !keyValueFile.exists()) {
            return keys;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(keyValueFile));
            while (true) {
                keys.add((Key) ois.readObject());
            }
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public void writeRecordToBinary(Record record, boolean toTmp) {
        File tmpValueFile;
        File tmpKeyFile;
        if (toTmp) {
            tmpValueFile = new File(valueFile + "temp");
            tmpKeyFile = new File(keyValueFile + "temp");
        } else {
            tmpValueFile = new File(valueFile.getPath());
            tmpKeyFile = new File(keyValueFile.getPath());
        }
        ObjectOutputStream out = null;
        try {
            if (!tmpValueFile.exists()) out = new ObjectOutputStream(new FileOutputStream(tmpValueFile));
            else out = new AppendableObjectOutputStream(new FileOutputStream(tmpValueFile, true));
            out.writeObject(record);
            out.flush();
            //        readFromBinaryFileRecord(tmpValueFile.getAbsolutePath());
            if (!tmpKeyFile.exists()) out = new ObjectOutputStream(new FileOutputStream(tmpKeyFile));
            else out = new AppendableObjectOutputStream(new FileOutputStream(tmpKeyFile, true));
            out.writeObject(record.getKey());
            out.flush();
            //        readFromBinaryFileKey(tmpKeyFile.getAbsolutePath());
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
        if (valueFile.exists() && keyValueFile.exists()) {
            if (id <= readKeyList().size()) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(new FileInputStream(valueFile));
                    int i = 0;
                    do {
                        r = (Record) ois.readObject();
                        i++;
                    } while (id != i);
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
        } else {
            System.out.print("No DB files");
        }
        return r;
    }

    public void deleteRecord(int id) {
        String tmpValueFile = valueFile + "temp";
        String tmpKeyFile = keyValueFile + "temp";
        Record r;
        Key k;
        for (int i = 1; i <= readKeyList().size(); i++) {            //key.size
            if (i < id) {
                writeRecordToBinary(readRecordFromBinaryFile(i), true);//write to tmp file
            } else if (i > id) {
                r = readRecordFromBinaryFile(i);
                k = r.getKey();
                k.setId(k.getId() - 1);
                r.setKey(k);
                writeRecordToBinary(readRecordFromBinaryFile(i), true);
            }
        }
        File oldFile = valueFile;
        oldFile.delete();
        File newFile = new File(tmpValueFile);
        newFile.renameTo(oldFile);
        oldFile = keyValueFile;
        oldFile.delete();
        newFile = new File(tmpKeyFile);
        newFile.renameTo(oldFile);
    }

    public static void readFromBinaryFileKey(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(filename));
                while (true) {
                    Key s = (Key) ois.readObject();
                    System.out.println(s.toString());
                }
            } catch (EOFException e) {

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
    }

    public static void readFromBinaryFileRecord(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(filename));
                while (true) {
                    Record s = (Record) ois.readObject();
                    System.out.println(s.toString());
                }
            } catch (EOFException e) {

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
    }

    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }
}
