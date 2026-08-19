public class Ejercicio3 {
    // Análisis de complejidad O(1)
    public static void ejecutar() {
        int iteraciones = 0;
        for (int i = 0; i < 1000; i += 10) {
            // Operaciones O(1)
            iteraciones++;
        }
        System.out.println("Iteraciones fijas ejecutadas: " + iteraciones + " (Complejidad O(1))");
    }

    public static void main(String[] args) {
        System.out.println("--- Ejercicio 3: Complejidad O(1) ---");
        ejecutar();
    }
}
