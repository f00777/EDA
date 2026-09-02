// 509. Fibonacci Number
// Link: https://leetcode.com/problems/fibonacci-number/
// Complejidad Temporal: O(2^N)
// Complejidad Espacial: O(N) por la pila de recursión

class Solution {
    public int fib(int n) {
        // Caso base
        if (n <= 1) {
            return n;
        }

        // Paso recursivo
        return fib(n - 1) + fib(n - 2);
    }
}
