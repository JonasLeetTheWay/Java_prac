package longer_questions_prob;

import java.util.LinkedList;
import java.util.Stack;

/*
    There are 8 nodes in a graph, namely, a, b, c, d, e, f, g, and h, respectively. An ant wants to
    travel from node a, through every other node once, and then come back to node a. The original
    route is as shown below
    a→b→c→d→e→f→g→h→a
    This route can be represented by a sequence of characters abcdefgha.
    Suppose now the ant decides to reverse its local route between two intermediate nodes, say b
    and e, then the new route will be
    a→e→d→c→b→f→g→h→a
    Or, it can be represented by a sequence of characters aedcbfgha.
    The above process is called a 2-opt swap.

Write a program to

    Input, in sequence,
    • The number of 2-opt swaps the ant likes to perform before its trip. Note that this
    number is a positive integer not greater than 4.
    • Pairs of characters, and each pair represents the two distinct intermediate nodes to
    perform a 2-opt swap.

    Output the sequence of nodes after performing the last 2-opt swap.
 */

public class q5 {

    static class NodeManager {
        LinkedList<Character> ll = new LinkedList<>();

        NodeManager(String str) {
            for (char c : str.toCharArray()) {
                ll.addLast(c);
            }
        }

        void twoOpt(char str, char end) {
            int[] str_end = new int[]{-1, -1};

            System.out.printf("checking... %c & %c \n", str, end);
            for (int i = 0; i < ll.size(); i++) {
                char check = ll.get(i);
                System.out.print(check + ", ");
                if (check == str) {
                    if (str_end[0] == -1) {
                        str_end[0] = i;
                    } else {
                        str_end[1] = i;
                    }
                }
                if (check == end) {
                    if (str_end[0] == -1) {
                        str_end[0] = i;
                    } else {
                        str_end[1] = i;
                    }
                }
            }

            if (str_end[0] == -1) {
                return;
            }

            if (str_end[0] > str_end[1]) {
                int temp = str_end[0];
                str_end[0] = str_end[1];
                str_end[1] = temp;
            }

            System.out.println();

            int rev_str = str_end[0];
            int rev_end = str_end[1] + 1;
            Stack<Character> s = new Stack<>();

            System.out.println("position to switch..." + rev_str + "," + rev_end);

            for (int i = rev_str; i < rev_end; i++) {
                s.add(ll.get(i));
                System.out.println("stack: " + s);

            }

            System.out.println("building new linked-list...");
            LinkedList<Character> lln = new LinkedList<>();
            for (int i = 0; i < rev_str; i++) {
                lln.add(ll.get(i));
                System.out.println(lln);
            }

            int p1 = rev_str;
            while (p1 < rev_end) {
                lln.add(s.pop());
                p1++;
                System.out.println(lln);
            }

            for (int i = rev_end; i < ll.size(); i++) {
                lln.add(ll.get(i));
                System.out.println(lln);
            }

            ll = lln;
            System.out.println("swapped");
            System.out.println(ll);

        }

        public String toString() {
            StringBuilder str = new StringBuilder();
            LinkedList<Character> llt = (LinkedList<Character>) ll.clone();

            while (llt.size() != 1) {
                str.append(llt.pollFirst());
                str.append("->");
            }
            str.append(llt.pollFirst());

            return str.toString();
        }
    }

    static void func(String... twoOptLoc) {

        NodeManager nodes = new NodeManager("abcdefgha");
        System.out.println(nodes);

        for (String s : twoOptLoc) {
            if (s.toCharArray().length == 1) {
                System.out.println("error: the current design is not reversible by one node");
                break;
            }
            else if (s.toCharArray().length != 2) {
                System.out.println("error: the current design is not reversible by more than two nodes");
                break;
            }
            char str = s.toCharArray()[0];
            char end = s.toCharArray()[1];
            nodes.twoOpt(str, end);
        }
        System.out.println("\nDONE\n");
    }

    static void run(){
        func("be","gd");
        func("cg","bf","ch");
        func("dh");
        func("eh","bd","ce","hg");
        func("bf","gc");

    }

    public static void main(String[] args) {
        run();
    }

}
