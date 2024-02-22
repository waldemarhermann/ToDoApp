import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ToDo toDo1 = new ToDo("Erstes", "1.", false);
        ToDo toDo2 = new ToDo("Zweites ", "2.", false);
        ToDo toDo3 = new TimedToDo("ErstesT", "1.", false, LocalDateTime.now().plusMinutes(3));
        ToDo toDo4 = new TimedToDo("ZweitesT", "2.", false, LocalDateTime.now().minusMinutes(3));

        ToDoManager toDoManager = new ToDoManager();

        toDoManager.add(toDo1);
        toDoManager.add(toDo2);
        toDoManager.add(toDo3);
        toDoManager.add(toDo4);

        System.out.println(toDoManager.getNormalToDos());
        System.out.println(toDoManager.getTimedToDos());

        toDoManager.removeExpiredToDos();

        System.out.println(toDoManager.getTimedToDos());

    }

}