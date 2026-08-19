public class Ejemplo3 {
    // Búsqueda lineal
    public static int search(int[] arr, int N, int x) {
        for (int i = 0; i < N; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("--- Ejemplo 3: Búsqueda Lineal ---");
        int[] arr = {2, 3, 4, 10, 40};
        int target1 = 10;
        int target2 = 99;

        int res1 = search(arr, arr.length, target1);
        int res2 = search(arr, arr.length, target2);

        System.out.println("Buscando " + target1 + ": encontrado en índice " + res1 + " (Esperado: 3)");
        System.out.println("Buscando " + target2 + ": encontrado en índice " + res2 + " (Esperado: -1)");
    }
}
