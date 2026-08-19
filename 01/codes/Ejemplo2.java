public class Ejemplo2 {
    // Búsqueda binaria recursiva
    public static int binarySearch(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] > x) return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("--- Ejemplo 2: Búsqueda Binaria Recursiva ---");
        int[] arr = {2, 3, 4, 10, 40};
        int target1 = 10;
        int target2 = 5;

        int res1 = binarySearch(arr, 0, arr.length - 1, target1);
        int res2 = binarySearch(arr, 0, arr.length - 1, target2);

        System.out.println("Buscando " + target1 + ": encontrado en índice " + res1 + " (Esperado: 3)");
        System.out.println("Buscando " + target2 + ": encontrado en índice " + res2 + " (Esperado: -1)");
    }
}
