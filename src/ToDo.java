public class ToDo {

    // Attribute / Eigenschaften
    private String titel;
    private String beschreibung;
    private boolean erledigt;

    // Konstruktor
    public ToDo(String titel, String beschreibung, boolean erledigt) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erledigt = erledigt;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "titel='" + titel + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", erledigt=" + erledigt +
                '}';
    }

    // getter & setter
    public String getTitel() {
        return this.titel;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }
    public String getBeschreibung() {
        return this.beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public boolean getErledigt() {
        return this.erledigt;
    }
    public void setErledigt(boolean erledigt) {
        this.erledigt = erledigt;
    }
}
