// 108. Convert Sorted Array to Binary Search Tree
// Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// Complejidad Temporal: O(N)
// Complejidad Espacial: O(log N) para la pila de recursión (árbol balanceado)

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBST(nums, 0, nums.length - 1);
    }
    
    // Función auxiliar que aplica Dividir y Conquistar
    private TreeNode constructBST(int[] nums, int left, int right) {
        // Caso base
        if (left > right) {
            return null;
        }
        
        // Encontramos el elemento central para asegurar que el árbol quede balanceado
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        
        // Dividimos y conquistamos la mitad izquierda y derecha recursivamente
        node.left = constructBST(nums, left, mid - 1);
        node.right = constructBST(nums, mid + 1, right);
        
        // Retornamos el subárbol combinado
        return node;
    }
}
