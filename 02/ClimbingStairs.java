// 70. Climbing Stairs
// Link: https://leetcode.com/problems/climbing-stairs/
// Complejidad Temporal: O(N)
// Complejidad Espacial: O(N)

class Solution {
    public int climbStairs(int n) {
        // Casos base
        if (n <= 2) {
            return n;
        }
        
        // Arreglo para memoización
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        
        // Calculamos de abajo hacia arriba (Bottom-Up) utilizando los subproblemas resueltos
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        
        return memo[n];
    }
}
