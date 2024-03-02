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

    private final ToDoManager toDoManager;

    private JPanel toDoArea;


    // Hier ist das erstellte Fenster
    public ToDoWindow(ToDoManager toDoManager) {
        this.toDoManager = toDoManager;

        setTitle("ToDo App");
        setSize(720, 1080);

        add((toDoArea = createToDoArea()), BorderLayout.CENTER);
        add(createButtonRow(), BorderLayout.SOUTH);

        pack();

        addWindowListener(new CustomWindowAdapter(toDoManager));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    // Hier ist der erstellte Container für den create-Block
    private JPanel createToDoBlock(ToDo toDo) {
        final JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());

        jPanel.add(new JLabel(toDo.getTitel()), BorderLayout.NORTH);
        jPanel.add(new JLabel(toDo.getBeschreibung()), BorderLayout.CENTER);

        final JCheckBox jCheckBox = new JCheckBox();
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
        final JPanel jPanel = new JPanel();

        jPanel.setLayout(new FlowLayout());

        final JButton add = new JButton("Hinzufügen");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String title = getInput("Bitte den Titel eingeben: ");
                if (title.isEmpty()) return;

                final String description = getInput("Bitte die Beschreibung eingeben: ");
                if (description.isEmpty()) return;

                final String hours = getInput("Wann soll die Aufgabe ablaufen? Bitte die Stundenanzahl eingeben: ");
                if (hours.isEmpty()) return;

                try {
                    final int hoursInteger = Integer.parseInt(hours);
                    if (hoursInteger == 0) {
                        toDoManager.add(new ToDo(title, description, false));
                    } else {
                        toDoManager.add(new TimedToDo(title, description, false, LocalDateTime.now().plusHours(hoursInteger)));
                    }
                } catch (Exception exception) {
                    toDoManager.add(new ToDo(title, description, false));
                }

                remove(toDoArea);
                add((toDoArea = createToDoArea()), BorderLayout.CENTER);
                pack();
            }
        });

        final JButton removeAll = new JButton("Alle Löschen");
        removeAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoManager.getToDos().clear();

                remove(toDoArea);
                add((toDoArea = createToDoArea()), BorderLayout.CENTER);
                pack();
            }
        });


        final JButton removeDone = new JButton(("Erledigte entfernen"));

        removeDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = toDoManager.getToDos().size() - 1; i >= 0 ; i--) {
                    if (toDoManager.getToDos().get(i).getErledigt()) {
                        toDoManager.getToDos().remove(i);
                    }

                    remove(toDoArea);
                    add((toDoArea = createToDoArea()), BorderLayout.CENTER);
                    pack();
                }
            }
        });


        jPanel.add(add);
        jPanel.add(removeAll);
        jPanel.add(removeDone);

        return jPanel;
    }

    private JPanel createToDoArea() {
        final JPanel jPanel = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        final ArrayList<ToDo> toDos = toDoManager.getToDos();
        for (int i = 0; i < toDos.size(); i++) {
            jPanel.add(createToDoBlock(toDos.get(i)));
        }

        return jPanel;
    }

    private String getInput(String prompt) {
        while(true) {
            final String input = JOptionPane.showInputDialog(prompt);

            if (input == null) {
                return "";
            }

            if (!input.isEmpty()) {
                return input;
            }
        }

    }
}
