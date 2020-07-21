package Pattern.Decorator;

public class PizzaMaker {
    public static void main(String[] args) {
        Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));

        System.out.println("Ingeredients:" + basicPizza.getDescription());
        System.out.println("Price:" + basicPizza.getCost());
    }
}
