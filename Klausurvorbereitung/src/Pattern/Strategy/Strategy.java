package Pattern.Strategy;

public abstract class Strategy {
    public abstract String toString();

    public static void main(String[] args) {
        ConcreteStrategy1 c1 = new ConcreteStrategy1();
        ConcreteStrategy2 c2 = new ConcreteStrategy2();
        System.out.println(c1);
        System.out.println(c2);
    }

}

class ConcreteStrategy1 extends Strategy {
    public String toString() {
        return "Conc1";
    }
}

class ConcreteStrategy2 extends Strategy {
    public String toString() {
        return "Conc2";
    }
}
