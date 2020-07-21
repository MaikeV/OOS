import java.util.ArrayList;

public abstract class BinBaum {
    Object eintrag;

    BinBaum() { }
    BinBaum(Object eintrag) {
        this.eintrag = eintrag;
    }

    abstract int anzahlKnoten();
    abstract String praefix();
}

class ZusBaum extends BinBaum{
    private ArrayList<BinBaum> tree = new ArrayList<BinBaum>();

    ZusBaum(Object eintrag) {
        super(eintrag);
    }

    @Override
    int anzahlKnoten() {
        int out = 1;

        for(BinBaum elem : tree) {
            out += elem.anzahlKnoten();
        }
        return out;
    }

    @Override
    String praefix() {
        String out = this.eintrag.toString();

        for (BinBaum elem : tree) {
            out += elem.praefix();
        }

        return out;
    }

    void addTeilbaum(BinBaum baum) {
        this.tree.add(baum);
    }

    void delTeilbaum(BinBaum baum) {
        this.tree.remove(baum);
    }

    BinBaum getChild(int i) {
        return this.tree.get(i);
    }
}

class Blatt extends BinBaum {

    Blatt(Object eintrag) {
        super(eintrag);
    }

    @Override
    int anzahlKnoten() {
        return 1;
    }

    @Override
    String praefix() {
        return this.eintrag.toString();
    }
}
