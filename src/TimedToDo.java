import java.time.LocalDateTime;

public class TimedToDo extends ToDo {

    // Attribute / Eigenschaften
    private LocalDateTime endet;

    public TimedToDo(String titel, String beschreibung, boolean erledigt, LocalDateTime endet) {
        super(titel, beschreibung, erledigt);
        this.endet = endet;
    }

    @Override
    public String toString() {
        return "TimedToDo{" +
                "endet=" + endet +
                "} " + super.toString();
    }

    public LocalDateTime getEndet() {
        return this.endet;
    }

    public void setEndet(LocalDateTime endet) {
        this.endet = endet;
    }
}
