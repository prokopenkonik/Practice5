package view;

import services.ResourceManager;
import view.constants.IMenuConstants;

public class Viewer {

    public void showMenu(ResourceManager resourceManager) {
        System.out.println(resourceManager.getString(IMenuConstants.ADD_RECORD_COMMAND));
        System.out.println(resourceManager.getString(IMenuConstants.SHOW_ALL_RECORDS_COMMAND));
        System.out.println(resourceManager.getString(IMenuConstants.CHANGE_LANGUAGE_COMMAND));
        System.out.println(resourceManager.getString(IMenuConstants.EXIT_COMMAND));
        System.out.print(resourceManager.getString(IMenuConstants.INPUT_COMMAND));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMessage(ResourceManager resourceManager, String message) {
        System.out.println(resourceManager.getString(message));
    }
}
