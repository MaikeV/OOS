package aufgabe1;

public class PascalschesDreieck {
    private int depth;
    private int[][] triangle;

    PascalschesDreieck() {}

    PascalschesDreieck(int depth) {
        this.depth = depth;
        this.triangle = new int[depth][];
    }

    void fill() {
        for(int i = 0; i < this.depth; i++) {
            triangle[i] = new int[i+1];
            for(int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    this.triangle[i][j] = 1;
                } else {
                    this.triangle[i][j] = this.triangle[i-1][j-1] + this.triangle[i-1][j];
                }
            }
        }
    }

    void print() {
        for(int i = 0; i < this.depth; i++) {
            for(int j = 0; j < i + 1; j++) {
                System.out.print(this.triangle[i][j] + "  ");
            }
            System.out.println("");
        }
    }
}
