import model.TaskList;
import utils.Utils;

public class Dudu {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Utils.printGreeting();
        Utils.loop(taskList);
    }
}