# Dudu :bear:
> "Dudu helps me keep track of my everyday tasks!" - David Vicedo

Here is a list of things that Dudu can help you with:
- **Add** a task
- **View** your current list of tasks
- **Mark** a task as completed
- **Delete** a task

There are three different types of Tasks that Dudu recognizes!
1. _Todo_ Tasks
2. _Deadline_ Tasks (Create a deadline for this task by using the /by keyword!)
3. _Event_ Tasks (Indicate a duration that this task will have by using the /from and /to keywords!)

What Dudu could possibly show you:
- [x] Read up on CS2103T Topics
- [x] CS2103T Tutorial (by: Feb 5 2025)
- [x] CS2103T ip Week 3 (from: Jan 28 2025, 3:00 pm to: Jan 31 2025, 4:00 pm)

Download [Dudu](https://github.com/vikeedough/ip/releases/tag/A-JavaDoc) and try him out yourself!


Here is the `main` method of Dudu:
```java
public static void main(String[] args) {
    Dudu dudu = new Dudu();
    try {
        dudu.run();
    } catch (DuduException | IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
