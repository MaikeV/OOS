import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Verzeichnissystem {
    String datei;

    Verzeichnissystem(String datei) {
        this.datei = datei;
    }

    public String getDatei() {
        return datei;
    }

    abstract void dateiConcat();
}

class Verzeichnis extends Verzeichnissystem {
    private ArrayList<Verzeichnissystem> verzeichnissysteme = new ArrayList<Verzeichnissystem>();

    Verzeichnis(String datei) {
        super(datei);
    }

    @Override
    void dateiConcat() {
            for (Verzeichnissystem elem : verzeichnissysteme) {
                elem.dateiConcat();
            }
    }

    void addDatei(Verzeichnissystem verzeichnissystem) {
        verzeichnissysteme.add(verzeichnissystem);
    }

    void removeDatei(Verzeichnissystem verzeichnissystem) {
        verzeichnissysteme.remove(verzeichnissystem);
    }
}

class Datei extends Verzeichnissystem {

    Datei(String datei) {
        super(datei);
    }

    @Override
    void dateiConcat() {
        System.out.println(this.getDatei());
    }
}
