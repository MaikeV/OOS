package Pattern.Proxy;

public class Proxy implements Ueber{
    private Subject sub;

    @Override
    public void operation() {
        if(sub == null)
            sub = new Subject();

        sub.operation();
    }

    public static void main(String[] args) {
        Ueber p = new Proxy();

        p.operation();
    }
}

class Subject implements Ueber {
    @Override
    public void operation() {
        System.out.println("ye");
    }
}

interface Ueber {
    void operation();
}
