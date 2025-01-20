import model.TaskList;
import utils.DuduException;
import utils.Utils;

public class Dudu {
    public static void main(String[] args) throws DuduException {
        TaskList taskList = new TaskList();
        Utils.printGreeting();
        Utils.loop(taskList);
    }
}