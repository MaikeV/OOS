import java.util.ArrayList;

public interface Terminkalender {
    public void add(Teilnehmer teilnehmer);
    public void remove(Teilnehmer teilnehmer);
    public void publish();
}

class Server implements Terminkalender{
    ArrayList<Teilnehmer> alleTeilnehmer = new ArrayList<Teilnehmer>();
    private String eintrag;

    public void Kalendereintrag(String s) {
        this.eintrag = s;
        publish();
    }

    @Override
    public void add(Teilnehmer teilnehmer) {
        alleTeilnehmer.add(teilnehmer);
    }

    @Override
    public void remove(Teilnehmer teilnehmer) {
        alleTeilnehmer.remove(teilnehmer);
    }

    @Override
    public void publish() {
        for (Teilnehmer elem : alleTeilnehmer) {
            elem.synch(eintrag);
        }
    }
}

interface Teilnehmer {
    public void synch(String neuerEintrag);
}

class MusterTeilnehmer implements Teilnehmer{
    private String name;

    MusterTeilnehmer(String name) {
        this.name = name;
    }

    @Override
    public void synch(String neuerEintrag) {
        System.out.println(name + ": " + neuerEintrag);
    }
}

class MainKalender {
    public static void main(String[] args) {
        Server server = new Server();

        MusterTeilnehmer fritz = new MusterTeilnehmer("Fritz");

        server.add(fritz);

        server.Kalendereintrag("Neuer Termin!");
    }
}
