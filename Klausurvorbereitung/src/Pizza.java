public abstract class Pizza {
    public abstract int getAnzahl();
    public abstract double getPreis();
    public abstract void setPreis();
    public abstract void setAnzahl();
}

class Margherita extends Pizza{

    @Override
    public int getAnzahl() {
        return 0;
    }

    @Override
    public double getPreis() {
        return 3.50;
    }

    @Override
    public void setPreis() {
        System.out.println(getPreis() + " EUR");
    }

    @Override
    public void setAnzahl() {
        System.out.println(getAnzahl() + " Zutaten");
    }
}

abstract class PizzaDecorator extends Pizza{
    public abstract int getAnzahl();
    public abstract double getPreis();
    public abstract void setAnzahl();
    public abstract void setPreis();
}

class Thunfisch extends PizzaDecorator{
    Pizza pizza;

    Thunfisch(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int getAnzahl() {
        return pizza.getAnzahl() + 1;
    }

    @Override
    public double getPreis() {
        return pizza.getPreis() + 2.00;
    }

    @Override
    public void setAnzahl() {
        System.out.println(getAnzahl() + " Zutaten");
    }

    @Override
    public void setPreis() {
        System.out.println(getPreis() + " EUR");
    }
}

class MainPizza {
    public static void main(String[] args) {
        Pizza meinePizza = new Thunfisch(new Margherita());

        meinePizza.setPreis();
        meinePizza.setAnzahl();
    }
}
