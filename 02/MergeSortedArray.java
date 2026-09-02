// 88. Merge Sorted Array
// Link: https://leetcode.com/problems/merge-sorted-array/
// Complejidad Temporal: O(M + N)
// Complejidad Espacial: O(1) in-place

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Punteros para los últimos elementos válidos
        int p1 = m - 1;
        int p2 = n - 1;
        
        // Puntero para la última posición del arreglo contenedor
        int p = m + n - 1;
        
        // Combinamos desde atrás hacia adelante para no sobreescribir los elementos útiles de nums1
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        
        // Si quedaron elementos en nums2, los copiamos
        // No necesitamos hacer lo mismo para nums1 porque ya están en su lugar correcto
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }
}
