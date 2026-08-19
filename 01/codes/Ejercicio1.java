public class Ejercicio1 {
    // Análisis de complejidad O(n + m)
    public static void ejecutar(int n, int m) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            // Operaciones O(1)
            count++;
        }
        for (int j = 0; j < m; j++) {
            // Operaciones O(1)
            count++;
        }
        System.out.println("Total de operaciones realizadas para n=" + n + ", m=" + m + ": " + count + " (Complejidad O(n + m))");
    }

    public static void main(String[] args) {
        System.out.println("--- Ejercicio 1: Complejidad O(n + m) ---");
        ejecutar(5, 10);
        ejecutar(100, 200);
    }
}
