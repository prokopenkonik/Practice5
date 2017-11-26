package execute;

import control.Controller;
import model.Register;
import view.Viewer;

public class Main {
    public static void main(String[] args) {
        Register register = Register.getInstance();
        Viewer viewer = new Viewer();
        Controller controller = new Controller(register, viewer);
        controller.run();
    }
}
