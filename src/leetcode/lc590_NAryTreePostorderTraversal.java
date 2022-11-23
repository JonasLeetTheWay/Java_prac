package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class lc590_NAryTreePostorderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1, new ArrayList<>());
        Node val_3 = new Node(3, new ArrayList<>());
        root.children.add(val_3);
        root.children.add(new Node(2));
        root.children.add(new Node(4));
        val_3.children.add(new Node(5));
        val_3.children.add(new Node(6));

        postorder(root);
    }

    public static List<Integer> postorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output_arr = new LinkedList<>();

        if(root == null){
            return output_arr;
        }

        stack.add(root);
        while(!stack.isEmpty()){
            Node node = stack.pollLast(); // get last
            output_arr.addFirst(node.val); // insert last element to the beginning of array --> reverse order

            for (Node child : node.children){
                System.out.println(child.val+", ");
                stack.add(child);
            }
        }
        return output_arr;
    }
}

//        LinkedList<Integer> stack = new LinkedList<>();
//        stack.addLast(1);
//        stack.addLast(2);
//        stack.addLast(3);
//        stack.addLast(4);
//
//        stack.addFirst(0);
//        stack.addLast(5);
//
//final int len = stack.size();
//
//        while (stack.size()!=0){
//        int j = stack.getFirst();
//        stack.poll();

//    System.out.println(node.val+", "+
//            "\n root children are:\n"+
//            node.children.get(0).val+", "+
//            node.children.get(1).val+", "+
//            node.children.get(2).val+", "+
//            "\n val_3 children are:\n"+
//            node.children.get(0).children.get(0).val+", "+
//            node.children.get(0).children.get(1).val);