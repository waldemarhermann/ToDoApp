import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomWindowAdapter extends WindowAdapter {

    private final ToDoManager toDoManager;

    public CustomWindowAdapter(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        toDoManager.saveToDos();
    }
}
