import java.util.Arrays;

public class Reverse {
    int[] nums = new int[] {1, 2, 3, 4, 5};

    public void reverse(){
        for(int i = 0; i < (nums.length - 1)/2; i++) {
            int temp = nums[(nums.length - 1) - i];

            nums[(nums.length - 1) - i] = nums[i];
            nums[i] = temp;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Reverse reverse = new Reverse();
        reverse.reverse();

        System.out.println(Arrays.toString(reverse.nums));
    }
}
