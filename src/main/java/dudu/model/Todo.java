package dudu.model;

public class Todo extends Task {

    /** Creates Todo object. **/
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns todo task in String format.
     *
     * @return Line with status, title of task.
     */
    @Override
    public String toString() {
            return "[T]" + super.toString();

    }
}
