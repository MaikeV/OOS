import java.util.ArrayList;

public class Primfaktorzerlegung {
    public static void main(String[] args) {
        int number = 555;
        int teiler = 2;
        ArrayList<Integer> prims = new ArrayList<Integer>();

        while(number > 1) {
            if (number % teiler == 0) {
                number /= teiler;
                prims.add(teiler);
            } else {
                teiler++;
            }
        }

        for(int i = 0; i < prims.size() - 1; i++) {
            System.out.print(prims.get(i) + "*");
        }

        System.out.print(prims.get(prims.size() - 1));
    }

}
