import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ToDoWindow extends JFrame{

    private ToDoManager toDoManager;


    // Hier ist das erstellte Fenster
    public ToDoWindow(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;

        setTitle("ToDo App");
        setSize(720, 1080);

        toDoManager.add(new ToDo("test1", "beschreibung1", false));
        toDoManager.add(new ToDo("test2", "beschreibung2", true));
        toDoManager.add(new TimedToDo("test2", "beschreibung3", true, LocalDateTime.now().plusHours(4)));

        add(createToDoArea(), BorderLayout.CENTER);
        add(createButtonRow(), BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    // Hier ist der erstellte Container für den create-Block
    private JPanel createToDoBlock(ToDo toDo) {
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());

        jPanel.add(new JLabel(toDo.getTitel()), BorderLayout.NORTH);
        jPanel.add(new JLabel(toDo.getBeschreibung()), BorderLayout.CENTER);

        JCheckBox jCheckBox = new JCheckBox();
        jCheckBox.setSelected(toDo.getErledigt());
        jCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDo.setErledigt(jCheckBox.isSelected());
            }
        });

        jPanel.add(jCheckBox, BorderLayout.EAST);

        if (toDo instanceof TimedToDo) {
            jPanel.add(new JLabel(((TimedToDo) toDo).getEndet().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))), BorderLayout.SOUTH);
        }

        return jPanel;
    }


    private JPanel createButtonRow() { // Hier müssen keine Argumente angenommen werden, da diese Reihe immer gleich bleibt
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new FlowLayout());

        JButton add = new JButton("Hinzufügen");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String title = getInput("Bitte den Titel eingeben: ");
                if (title.isEmpty()) return;

                String description = getInput("Bitte die Beschreibung eingeben: ");
                if (description.isEmpty()) return;

                String hours = getInput("Wann soll die Aufgabe ablaufen? Bitte die Stundenanzahl eingeben: ");
                if (hours.isEmpty()) return;

                try {
                        int hoursInteger = Integer.parseInt(hours);
                        if (hoursInteger == 0) {
                            toDoManager.add(new ToDo(title, description, false));
                        } else {
                            toDoManager.add(new TimedToDo(title, description, false, LocalDateTime.now().plusHours(hoursInteger)));
                        }
                } catch (Exception exception) {
                    toDoManager.add(new ToDo(title, description, false));
                }

                System.out.println("Add");
                for (int i = 0; i < toDoManager.getToDos().size(); i++) {
                    System.out.println(toDoManager.getToDos().get(i));
                }
            }
        });

        JButton removeAll = new JButton("Alle Löschen");
        removeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoManager.getToDos().clear();

                System.out.println("Remove All");
                for (int i = 0; i < toDoManager.getToDos().size(); i++) {
                    System.out.println(toDoManager.getToDos().get(i));
                }
            }
        });


        JButton removeDone = new JButton(("Erledigte entfernen"));

        removeDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = toDoManager.getToDos().size() - 1; i >= 0 ; i--) {
                    if (toDoManager.getToDos().get(i).getErledigt()) {
                        toDoManager.getToDos().remove(i);
                    }

                    System.out.println("Remove Completed");
                    for (int k = 0; k < toDoManager.getToDos().size(); k++) {
                        System.out.println(toDoManager.getToDos().get(k));
                    }
                }
            }
        });


        jPanel.add(add);
        jPanel.add(removeAll);
        jPanel.add(removeDone);

        return jPanel;
    }

    private JPanel createToDoArea() {
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        ArrayList<ToDo> toDos = toDoManager.getToDos();
        for (int i = 0; i < toDos.size(); i++) {
            jPanel.add(createToDoBlock(toDos.get(i)));
        }

        return jPanel;
    }

    private String getInput(String prompt) {
        while(true) {
            String input = JOptionPane.showInputDialog(prompt);

            if (input == null) {
                return "";
            }

            if (!input.isEmpty()) {
                return input;
            }
        }

    }
}
