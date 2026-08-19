public class Ejercicio2 {
    // Análisis de complejidad O(log n)
    public static void ejecutar(int n) {
        int original = n;
        int pasos = 0;
        while (n > 0) {
            n = n / 2;
            pasos++;
        }
        System.out.println("Para n = " + original + ", se ejecutaron " + pasos + " iteraciones (Complejidad O(log n))");
    }

    public static void main(String[] args) {
        System.out.println("--- Ejercicio 2: Complejidad O(log n) ---");
        ejecutar(16);
        ejecutar(1024);
    }
}
