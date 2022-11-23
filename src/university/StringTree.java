package university;

public class StringTree {

    private StringTree leftChild;
    private StringTree rightChild;
    private String savedValue;

    public StringTree(String str) {
        this.savedValue = str;
    }

    public void insert(String str) {

        if (savedValue.compareTo(str) > 0) {

            if (leftChild == null) {
                leftChild = new StringTree(str);
            } else {
                leftChild.insert(str);
            }

        } else {
            if (rightChild == null) {
                rightChild = new StringTree(str);
            } else {
                rightChild.insert(str);
            }
        }
    }
    public String toStringPreOrder() {
        String s = "";
        s += savedValue;
        if (leftChild != null) {
            s += leftChild.toString();
        }
        if (rightChild != null) {
            s += rightChild.toString();
        }
        return s;

    }

    public String toStringPostOrder() {
        String s = "";
        if (leftChild != null) {
            s += leftChild.toString();
        }
        if (rightChild != null) {
            s += rightChild.toString();
        }
        s += savedValue;
        return s;

    }
    public String toString() {
        String s = "";
        if (leftChild != null) {
            s += leftChild.toString();
        }
        s += savedValue;
        if (rightChild != null) {
            s += rightChild.toString();
        }
        return s;
    }
}