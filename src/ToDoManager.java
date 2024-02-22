import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDoManager {

    // liste todos
    ArrayList<ToDo> toDos = new ArrayList<>();

    // add
    public void add(ToDo addToDo) {
        toDos.add(addToDo);
    }


    // remove
    public void remove(ToDo removeToDo) {
        toDos.remove(removeToDo);
    }


    // getTimedToDos
    public ArrayList<TimedToDo> getTimedToDos() {
        ArrayList<TimedToDo> timedToDos = new ArrayList<>();

        for (int i = 0; i < toDos.size(); i++) {
            ToDo toDo = toDos.get(i);

            if (toDo instanceof TimedToDo) {
                timedToDos.add((TimedToDo) toDo);
            }
        }

        return timedToDos;
    }


    // get(Normal)ToDos
    public ArrayList<ToDo> getNormalToDos() {
        ArrayList<ToDo> normalToDos = new ArrayList<>();

        for (int i = 0; i < toDos.size(); i++) {
            ToDo toDo = toDos.get(i);

            if (!(toDo instanceof TimedToDo)) {
                normalToDos.add(toDo);
            }
        }

        return normalToDos;
    }


    // entferne abgelaufene
    public void removeExpiredToDos() {
        ArrayList<TimedToDo> timedToDos = getTimedToDos();

        for (int i = 0; i < timedToDos.size(); i++) {
            TimedToDo timedToDo = timedToDos.get(i);

            if (timedToDo.getEndet().isBefore(LocalDateTime.now())) {
                remove(timedToDo);
            }
        }
    }
}
