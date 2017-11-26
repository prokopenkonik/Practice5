package model;

import java.util.Arrays;

public class Register {
    private static final int MAX_GROUP_SIZE = 30;

    private static Register instance = new Register();
    private Record[] records = new Record[MAX_GROUP_SIZE];
    private int currentRecordIndex = 0;

    public static Register getInstance() {
        return instance;
    }

    private Register() {
    }

    public void addRecord(String name, String surname, String birthDate, String phoneNumber, String address) {
        records[currentRecordIndex++] = new Record(name, surname, birthDate, phoneNumber, address);
    }

    public Record[] getRecords() {
        return Arrays.copyOf(records, currentRecordIndex);
    }

    public int getSize() {
        return currentRecordIndex;
    }
}
