public class Main {

    public static void main(String[] args) {
        int[] lista = {12, 5, -21, 10, -345, 22, 50, -125, 80, -1};
        int multiplicacao = 1;
        int negativos = 0;
        int maior = 0;
        int menor = 0;

        for (int i = 0; i < lista.length; i++) {
            // multiplicar
            if (lista[i] > 0) {
                multiplicacao *= lista[i];
            }
            if (lista[i] < 0) {
                negativos++;
            }
            if (lista [i] > maior) {
                maior = lista[i];
            }
            if (lista [i] < menor) {
                menor = lista[i];
            }
        }

        System.out.println("Multiplicacao: " + multiplicacao);
        System.out.println("Elementos negativos " + negativos);
        System.out.println("Maior numero: " + maior);
        System.out.println("Menor numero: " + menor);
    }
}
