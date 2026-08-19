public class Ejemplo4 {
    // Determinar si existe un par que sume x
    public static boolean chkPair(int[] A, int x) {
        int size = A.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (A[i] + A[j] == x) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("--- Ejemplo 4: Par con suma X ---");
        int[] A = {0, -1, 2, -3, 1};
        int targetSum1 = -2;
        int targetSum2 = 10;

        System.out.println("Existe par con suma " + targetSum1 + ": " + chkPair(A, targetSum1) + " (Esperado: true)");
        System.out.println("Existe par con suma " + targetSum2 + ": " + chkPair(A, targetSum2) + " (Esperado: false)");
    }
}
