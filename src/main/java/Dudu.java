import model.TaskList;
import utils.Utils;

public class Dudu {
    public static void main(String[] args) {
        TaskList entryList = new TaskList();
        Utils.printGreeting();
        Utils.loop(entryList);
    }
}