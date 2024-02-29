import javax.swing.*;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ToDoManager toDoManager = new ToDoManager("todoapp_savefile.txt");

        toDoManager.loadToDos();
        toDoManager.removeExpiredToDos();

        new ToDoWindow(toDoManager);

    }

}