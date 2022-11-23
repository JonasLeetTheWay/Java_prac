package leetcode;

public class lc206_ReversedLinkedList {
    public static void main(String[] args) {
        // input LinkedList
        Node head = new Node(1);
        Node nodeB = new Node(2);
        Node nodeC = new Node(3);
        Node nodeD = new Node(4);
        Node nodeE = new Node(5);
        head.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;
        nodeE.next = null;

//        reversedList(head);
        System.out.println(nodesCount(head));
    }
    public static Node reversedList(Node head){
        Node prev = null; // Node obj = create object-instance of Node

        while(head!=null){
            Node next = head.next; //Node obj, and saves original NEXT neighbour to temporary
            head.next = prev; // rewrite original NEXT neighbour to NULL
            prev = head; // switch PREVIOUS neighbour to current head
            head = next; // switch current head to NEXT,
            // assignment to head is last step, b/c head information was needed everywhere before this
        }
        return prev; // linked list is completely altered, no copies been made
    }
    public static int nodesCount(Node head){
        int i = 0;
        while (head!=null){
            i+=1;
            head = head.next;
        }
        return i;
    }
//    public static void NodeBuilder(int arrLength, int[] nodeArray){
//        for (int i=0; i<arrLength; i++) {
//            Node node
//        }
//    }
}
