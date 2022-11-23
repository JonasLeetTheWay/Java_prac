package leetcode;

public class lc234_PalindromeLinkedList {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node node2 = new Node(2);
        head.next = node2;

        Node head2 = new Node(1);
        Node mode2 = new Node(2);
        Node mode3 = new Node(2);
        Node mode4 = new Node(1);
        head2.next = mode2;
        mode2.next = mode3;
        mode3.next = mode4;

        if (isPalindrome(head)){System.out.println("Palindrome1!");}
        if (isPalindrome(head2)){System.out.println("Palindrome2!");}
    }

    public static boolean isPalindrome(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        slow = reversed(slow);
        fast = head; // set back to the beginning

        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static Node reversed(Node head) {
        Node prev = null;
        while (head != null) {
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = prev;
        }
        return prev;
    }
}
