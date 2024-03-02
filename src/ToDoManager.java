import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class ToDoManager {

    // liste todos
    private final ArrayList<ToDo> toDos = new ArrayList<>();

    private final String path;

    public ToDoManager(String path) {
        this.path = path;
    }

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
        final ArrayList<TimedToDo> timedToDos = new ArrayList<>();

        for (int i = 0; i < toDos.size(); i++) {
            final ToDo toDo = toDos.get(i);

            if (toDo instanceof TimedToDo) {
                timedToDos.add((TimedToDo) toDo);
            }
        }

        return timedToDos;
    }


    // get(Normal)ToDos
    public ArrayList<ToDo> getNormalToDos() {
        final ArrayList<ToDo> normalToDos = new ArrayList<>();

        for (int i = 0; i < toDos.size(); i++) {
            final ToDo toDo = toDos.get(i);

            if (!(toDo instanceof TimedToDo)) {
                normalToDos.add(toDo);
            }
        }

        return normalToDos;
    }

    public ArrayList<ToDo> getToDos() {
        return this.toDos;
    }


    // entferne abgelaufene
    public void removeExpiredToDos() {
        final ArrayList<TimedToDo> timedToDos = getTimedToDos();

        for (int i = 0; i < timedToDos.size(); i++) {
            final TimedToDo timedToDo = timedToDos.get(i);

            if (timedToDo.getEndet().isBefore(LocalDateTime.now())) {
                remove(timedToDo);
            }
        }
    }

    public void saveToDos() {
        final File file = new File(this.path);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            final FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < toDos.size(); i++) {
                final ToDo toDo = toDos.get(i);

                String saveString = toDo.getTitel() + "_" + toDo.getBeschreibung() + "_" + toDo.getErledigt();
                if (toDo instanceof TimedToDo) {
                    saveString += "_" + ((TimedToDo) toDo).getEndet();
                }

                fileWriter.write(saveString + "\n");
            }

            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void loadToDos()       {
        final File file = new File(this.path);

        if (!file.exists()) return;

        try {
            final FileReader fileReader = new FileReader(file);

            String fullString = "";

            int currentChar = 0;
            while ((currentChar = fileReader.read()) != -1) {
                fullString += (char) currentChar;
            }

            fileReader.close();

            final String[] lines = fullString.split("\n");
            for (int i = 0; i < lines.length; i++) {
               final String line = lines[i];
               final String[] components = line.split("_");

               if (components.length == 3) {
                   toDos.add(new ToDo(components[0], components[1], Boolean.valueOf(components[2])));
               } else if (components.length == 4) {
                    toDos.add(new TimedToDo(components[0], components[1], Boolean.valueOf(components[2]), LocalDateTime.parse(components[3])));
               }
            }

        } catch (Exception eoException) {
            eoException.printStackTrace();
        }
    }
}


