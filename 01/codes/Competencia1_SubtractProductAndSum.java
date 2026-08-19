public class Competencia1_SubtractProductAndSum {
    public static int subtractProductAndSum(int n) {
        int productVal = 1;
        int sumVal = 0;

        while (n > 0) {
            int digit = n % 10;
            productVal *= digit;
            sumVal += digit;
            n /= 10;
        }

        return productVal - sumVal;
    }

    public static void main(String[] args) {
        System.out.println("--- Competencia 1: Subtract the Product and Sum of Digits of an Integer ---");
        int test1 = 234;
        int test2 = 4421;

        System.out.println("Entrada: n = " + test1 + " -> Salida: " + subtractProductAndSum(test1) + " (Esperado: 15)");
        System.out.println("Entrada: n = " + test2 + " -> Salida: " + subtractProductAndSum(test2) + " (Esperado: 21)");
    }
}
