package control;

import model.Record;
import model.Register;
import services.ResourceManager;
import view.Viewer;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import static view.constants.IMenuConstants.*;
import static view.constants.IRegexConstants.*;

public class Controller {
    private Register register;
    private Viewer viewer;
    private Scanner scanner;
    private ResourceManager resourceManager;

    enum Command {
        ADD_RECORD, SHOW_ALL_RECORDS, CHANGE_LANGUAGE, EXIT
    }

    public Controller(Register register, Viewer viewer) {
        this.register = register;
        this.viewer = viewer;
        scanner = new Scanner(System.in);
        resourceManager = ResourceManager.INSTANCE;
    }

    public void run() {
        viewer.showMessage(START_MESSAGE);
        setLanguage();

        while (true) {
            viewer.showMenu(resourceManager);
            Command command = inputCommand();
            switch (command) {
                case ADD_RECORD:
                    addNewRecord();
                    break;
                case SHOW_ALL_RECORDS:
                    if (register.getSize() == 0) {
                        viewer.showMessage(resourceManager, REGISTER_EMPTY);
                    }
                    for (Record record : register.getRecords()) {
                        viewer.showMessage(record.toString());
                    }
                    break;
                case CHANGE_LANGUAGE:
                    viewer.showMessage(resourceManager, SELECT_LANGUAGE);
                    setLanguage();
                    break;
                case EXIT:
                    System.exit(0);
            }
        }
    }

    private void addNewRecord() {
        scanner.nextLine();
        viewer.showMessage(resourceManager, INPUT_NAME);
        String name = inputString(NAME_REGEXP);
        viewer.showMessage(resourceManager, INPUT_SURNAME);
        String surname = inputString(NAME_REGEXP);
        viewer.showMessage(resourceManager, INPUT_BIRTH_DATE);
        String birthDate = inputString(BIRTH_DATE_REGEXP);
        viewer.showMessage(resourceManager, INPUT_PHONE_NUMBER);
        String phoneNumber = inputString(PHONE_NUMBER_REGEXP);
        viewer.showMessage(resourceManager, INPUT_ADDRESS);
        String address = inputString(ADDRESS_REGEXP);
        register.addRecord(name, surname, birthDate, phoneNumber, address);
        viewer.showMessage(resourceManager, RECORD_ADDED);
    }

    private void setLanguage() {
        String language = inputLanguage();
        resourceManager.changeResource(getLocale(language));
    }

    private String inputLanguage() {
        String input;
        while (true) {
            input = scanner.next();
            switch (input) {
                case "ru":
                case "en":
                case "uk":
                    return input;
                default:
                    viewer.showMessage(resourceManager, WRONG_INPUT);
                    break;
            }
        }
    }

    private Locale getLocale(String language) {
        switch (language) {
            case "en":
                return new Locale("en", "GB");
            case "uk":
                return new Locale("uk", "UA");
            default:
                return Locale.getDefault();
        }
    }

    private String inputString(String regex) {
        String result;
        while (true) {
            result = scanner.nextLine();
            if (result.matches(resourceManager.getString(regex))) {
                return result;
            }
            viewer.showMessage(resourceManager, WRONG_INPUT);
        }
    }

    private Command inputCommand() {
        int command;
        while (true) {
            try {
                command = scanner.nextInt();
                if (command < 1 || command > 4) {
                    throw new IOException();
                }
            }
            catch (Exception e) {
                viewer.showMessage(resourceManager, WRONG_INPUT);
                scanner.nextLine();
                continue;
            }
            return Command.values()[command - 1];
        }
    }
}
