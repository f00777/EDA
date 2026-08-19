public class Competencia2_ValidPalindrome {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

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

    public static void main(String[] args) {
        System.out.println("--- Competencia 2: Valid Palindrome ---");
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = " ";

        System.out.println("Entrada: \"" + s1 + "\" -> Es palíndromo: " + isPalindrome(s1) + " (Esperado: true)");
        System.out.println("Entrada: \"" + s2 + "\" -> Es palíndromo: " + isPalindrome(s2) + " (Esperado: false)");
        System.out.println("Entrada: \"" + s3 + "\" -> Es palíndromo: " + isPalindrome(s3) + " (Esperado: true)");
    }
}
