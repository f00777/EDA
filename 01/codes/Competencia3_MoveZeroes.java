import java.util.Arrays;

public class Competencia3_MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }

        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Competencia 3: Move Zeroes ---");
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.println("Entrada: " + Arrays.toString(nums1));
        moveZeroes(nums1);
        System.out.println("Salida: " + Arrays.toString(nums1) + " (Esperado: [1, 3, 12, 0, 0])");

        int[] nums2 = {0};
        System.out.println("Entrada: " + Arrays.toString(nums2));
        moveZeroes(nums2);
        System.out.println("Salida: " + Arrays.toString(nums2) + " (Esperado: [0])");
    }
}
