public class Ejemplo1 {
    // Función para verificar si un número es par o impar
    public static void checkEvenOdd(int N) {
        int r = N % 2;
        if (r == 0) {
            System.out.println(N + " es Par");
        } else {
            System.out.println(N + " es Impar");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Ejemplo 1: Par o Impar ---");
        int test1 = 101;
        int test2 = 42;
        checkEvenOdd(test1);
        checkEvenOdd(test2);
    }
}
