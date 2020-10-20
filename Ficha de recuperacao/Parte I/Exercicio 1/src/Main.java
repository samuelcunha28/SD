public class Main {

    public static void main(String[] args) {
	    int[][] matriz = new int[][]{{11, 7, 333}, {-20, -23, 63}, {-22, 501, 10000}};
	    int soma = 0;
	    int pos = 0;

	    for (int i = 0; i < 3; i++) {
            System.out.println(" ");
            for (int j = 0; j < 3; j++) {
                System.out.println(matriz[i][j] + "\t");
                pos++;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                soma += matriz[i][j];
            }
        }
        System.out.println("");
        System.out.println("Soma: " + soma);
        System.out.println("Media: " + soma / pos);
    }
}
