import controller.Command;
import controller.Parser;
import model.TaskList;
import utils.DuduException;
import utils.Ui;

public class Dudu {
    public static void main(String[] args) throws DuduException {
        TaskList tasks = new TaskList();
        Ui.printDudu();
        Ui.printGreeting();

        while (true) {
            try {
                String fullCommand = Ui.getInput();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks);
                if (command.isExit()) {
                    return;
                }
            } catch (DuduException e) {
                Ui.printContentWithoutLines(e.getMessage());
            }
        }
    }
}