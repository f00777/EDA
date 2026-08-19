Introducción a la complejidad temporal.
https://www.geeksforgeeks.org/dsa/complete-guide-on-complexity-analysis/

O(g(n)) = { f(n): there exist positive constants c and n0 such that 0 ≤ f(n) ≤ cg(n) for all n ≥ n0 }


The time complexity of an algorithm is defined as the amount of time taken by an algorithm to run as a function of the length of the input. Note that the time to run is a function of the length of the input and not the actual execution time of the machine on which the algorithm is running on


f we have statements with basic operations like comparisons, return statements, assignments, and reading a variable. We can assume they take constant time each O(1).

Statement 1: int a=5;            // reading a variable
statement 2; if( a==5) return true;  // return statement
statement 3; int x= 4>5 ? 1:0;     // comparison
statement 4; bool flag=true;      // Assignment


total time = time(statement1) + time(statement2) + ... time (statementN)




Example 1
// Java program for the above approach

public class GFG {
    // Function to check if a number is even or odd
    public static void checkEvenOdd(int N)
    {
        // Find remainder
        int r = N % 2;

        // Condition for even
        if (r == 0) {
            System.out.println("Even");
        }
        // Otherwise
        else {
            System.out.println("Odd");
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        // Given number N
        int N = 101;

        // Function call
        checkEvenOdd(N);
    }
}



Example 2
// Java program to implement recursive Binary Search

class BinarySearch {
    // A recursive binary search function. It returns
    // the index of x in the given array arr[l..r] if present,
    // otherwise returns -1
    static int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in the left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);

            // Else the element can only be present
            // in the right subarray
            return binarySearch(arr, mid + 1, r, x);
        }

        // We reach here when the element is not present in the array
        return -1;
    }

    public static void main(String args[]) {
        int arr[] = {2, 3, 4, 10, 40};
        int x = 10;
        int n = arr.length;
        int result = binarySearch(arr, 0, n - 1, x);
        if (result == -1)
            System.out.println("Element is not present in the array");
        else
            System.out.println("Element is present at index " + result);
    }
}

// This code is contributed by Utkarsh Kumar


Example 3
// Java code to linearly search x in arr[]. If x
// is present then return its location, otherwise
// return -1
class GFG {
    static int search(int[] arr, int N, int x)
    {
        for (int i = 0; i < N; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    // Driver's code
    public static void main(String[] args)
    {
        int[] arr = { 2, 3, 4, 10, 40 };
        int x = 10;
        int N = arr.length;

        // Function call
        int result = search(arr, N, x);
        if (result == -1) {
            System.out.println(
                "Element is not present in array");
        }
        else {
            System.out.println(
                "Element is present at index " + result);
        }
    }
}
// This code is contributed by prasad264




Example 4
// Java program for the above approach
import java.util.*;

public class GFG {
    // Function to find and print pair
    static boolean chkPair(int[] A, int x)
    {
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

    // Driver code
    public static void main(String[] args)
    {
        int[] A = { 0, -1, 2, -3, 1 };
        int x = -2;

        if (chkPair(A, x)) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No " + x);
        }
    }
}

// This code is contributed by Susobhan Akhuli



Exercise 1
Analice el siguiente snippet y determine la complejidad del tiempo de ejecuci´on con la notaci´on Big O.
1 for ( int i = 0; i < n ; i ++) {
2 // Operaciones O (1)
3 // ...
4 }
5
6 for ( int j = 0; j < m ; j ++) {
7 // Operaciones O (1)
8 // ...
9 }




Exercise 2
Analice el siguiente snippet y determine la complejidad del tiempo de ejecuci´on con la notaci´on Big O.
1 while ( n > 0) {
2 n = n / 2;
3 // Operaciones de tiempo constante O (1)
4 // ...
5 }



Exercise 3
nalice el siguiente snippet y determine la complejidad del tiempo de ejecuci´on con la notaci´on Big O.
1 for ( int i = 0; i < 1000; i += 10) {
2 // Operaciones de tiempo constante O (1)
3 // ...
4 }



Competition

Exercise 1
LeetCode 1281: Subtract the Product and Sum of Digits of an Integer
https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/description/

Given an integer number n, return the difference between the product of its digits and the sum of its digits. 

Example 1:

Input: n = 234
Output: 15 
Explanation: 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15

Example 2:

Input: n = 4421
Output: 21
Explanation: 
Product of digits = 4 * 4 * 2 * 1 = 32 
Sum of digits = 4 + 4 + 2 + 1 = 11 
Result = 32 - 11 = 21



Sol. Ex 1:
class Solution {
    public int subtractProductAndSum(int n) {
        int productVal = 1;
        int sumVal = 0;
        
        while (n > 0) {
            int digit = n % 10;        // Extrae el último dígito
            productVal *= digit;
            sumVal += digit;
            n /= 10;                   // Elimina el último dígito (división entera)
        }
        
        return productVal - sumVal;
    }
}
Complejidad Temporal: $O(\log_{10} n)$Complejidad Espacial: $O(1)$





Exercise 2
125. Valid Palindrome
https://leetcode.com/problems/valid-palindrome/description/

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:

Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

SOL 2
125. Valid Palindrome
https://leetcode.com/problems/valid-palindrome/description/

class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Avanzar el puntero izquierdo si no es alfanumérico
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }:
            // Retroceder el puntero derecho si no es alfanumérico
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Comparar ignorando mayúsculas/minúsculas
            char leftChar = Character.toLowerCase(s.charAt(left));
            char rightChar = Character.toLowerCase(s.charAt(right));
            
            if (leftChar != rightChar) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}

Complejidad Temporal: $O(n)$Complejidad Espacial: $O(1)$




Excersice 3
https://leetcode.com/problems/move-zeroes/description/
283. Move Zeroes
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array.

 

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:

Input: nums = [0]
Output: [0]


Solution 3

class Solution {
    public void moveZeroes(int[] nums) {
        int insertPos = 0; // Puntero de escritura para elementos no-cero
        
        // Mover todos los elementos distintos de cero al frente
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        
        // Rellenar las posiciones restantes con ceros
        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
}

Complejidad Temporal: $O(n)$Complejidad Espacial: $O(1)$




