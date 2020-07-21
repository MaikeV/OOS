package Pattern.Observer;
import java.util.ArrayList;

abstract class AbstractObserver{
    abstract void update(Sub s);
}

public class Observer extends AbstractObserver {
    public static int nr = 1;
    private int no;

    public Observer() {
        this.no = nr++;
    }

    void update(Sub s) {
        System.out.println(this.no + " " + ((RealSub)s).getState());
    }

    public static void main(String[] args) {
        RealSub s = new RealSub();
        Observer o1 = new Observer(); Observer o2 = new Observer();
        Observer o3 = new Observer(); Observer o4 = new Observer();

        s.add(o1); s.add(o2);
        s.setState("123");
        s.add(o3); s.add(o4);
        s.setState("123");
    }
}

class Sub {
    public ArrayList<Observer> obs = new ArrayList<Observer>();

    void add(Observer o) {
        this.obs.add(o);
    }

    void remove(Observer o) {
        this.obs.remove(o);
    }

    void noti(RealSub sub) {
        for(Observer o : obs) {
            o.update(sub);
        }
    }
}

class RealSub extends Sub {
    String state;

    void setState(String s) {
        this.state = s;
        noti(this);
    }

    String getState() {
        return this.state;
    }
}
