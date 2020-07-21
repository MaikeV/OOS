package Pattern.Template;

public abstract class Template {
    void template() {
        System.out.println(m1() + m2());
    }

    public abstract String m1();
    public abstract String m2();

    public static void main(String[] args) {
        Framework f = new Framework();
        f.template();
    }
}

class Framework extends Template {

    @Override
    public String m1() {
        return "m1";
    }

    @Override
    public String m2() {
        return "m2";
    }
}
