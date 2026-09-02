// 206. Reverse Linked List
// Link: https://leetcode.com/problems/reverse-linked-list/
// Complejidad Temporal: O(N)
// Complejidad Espacial: O(1)

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // Guardamos temporalmente el siguiente nodo
            ListNode nextTemp = curr.next;

            // Invertimos el puntero del nodo actual hacia el previo
            curr.next = prev;

            // Avanzamos ambos punteros (previo y actual) un paso adelante
            prev = curr;
            curr = nextTemp;
        }

        // Al terminar, 'curr' será nulo y 'prev' apuntará a la nueva cabeza de la lista
        // invertida
        return prev;
    }
}
