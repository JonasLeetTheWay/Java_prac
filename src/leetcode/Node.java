package leetcode;

import java.util.List;

/**
 * Definition for singly-linked list.
 * public class Node {
 * int val;
 * Node next;
 * Node(int x) {val = x;}
 * }
 */

public class Node {
    int val;
    Node next;
    List<Node> children;

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}