// 704. Binary Search
// Link: https://leetcode.com/problems/binary-search/
// Complejidad Temporal: O(log N)
// Complejidad Espacial: O(1)

class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            // Evitamos overflow que ocurriría con (left + right) / 2
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // Elemento encontrado
            } else if (nums[mid] < target) {
                // Descartamos la mitad izquierda
                left = mid + 1;
            } else {
                // Descartamos la mitad derecha
                right = mid - 1;
            }
        }
        
        return -1; // Elemento no encontrado
    }
}
