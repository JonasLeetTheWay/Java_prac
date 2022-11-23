package general_prob.double_linked_list;

public class DoublyLinkedList {
    private DLLElement head, tail, curr;
    private int idx=0; // idx for curr double_linked_list.DLLElement
    private int length=0;

    DoublyLinkedList(){
        head = new DLLElement();
        tail = new DLLElement();

        head.setNext(tail);
        tail.setPrevious(head);
    }

    DoublyLinkedList(Object... objs){
        this();
        for (Object obj : objs){
            this.insertLast(obj);
        }
    }

    int findElementIdx(Object obj){
        DLLElement traversal = head; // reset traversal to head every run
        idx = 0; // reset idx, it's separate route from setCurrElementIdx

        while (traversal.getNext() != null) {
            if (traversal.getObject() == obj) {
                break;
            }
            traversal = traversal.getNext();
            idx++;
        }
        // if element not found
        if (idx == 0) return -1;
        // reset curr
        curr = head;
        for (int i=0; i<idx; i++){
            curr = curr.getNext();
        }
        // since indexing include traversing head & current location, so both their indexing must be neglected
        return idx-2;
    }

    void setCurrElementIdx(int idx){
        this.idx = idx;
        if (head.getNext() != null){
            curr = head.getNext();
        } else {
            curr = head;
        }
        for (int i=0; i<idx; i++){
            curr = curr.getNext();
        }
    }

    int getCurrElementIdx(){
        return idx;
    }
    Object getCurrElement(){
        if (idx == 0) this.setCurrElementIdx(0);
        return curr.getObject();
    }
    Object getPrevElement(){
        // if idx = 0, initializes itself with default currElementIdx = 0
        if (idx == 0) this.setCurrElementIdx(0);
        if (idx > 1) {
            idx--;
            curr = curr.getPrevious();
            return curr.getObject();
        }
        return "* out of bound *";
    }
    Object getNextElement(){
        // if idx = 0, initializes itself with default currElementIdx = 0
        if (idx == 0) this.setCurrElementIdx(0);
        if (idx < length-1) {
            idx++;
            curr = curr.getNext();
            return curr.getObject();
        }
        return "* out of bound *";
    }

    void insertFirst(Object obj){
        DLLElement temp = new DLLElement();
        // insert temp in between
        temp.setPrevious(head);
        temp.setNext(head.getNext());
        temp.setObject(obj);
        // case 1: empty list
        if (head.getNext() == null) { tail.setPrevious(temp); }
        // case 2: there's existing item inside
        else { head.getNext().setPrevious(temp); }

        head.setNext(temp);

        length++;
    }

    void insertLast(Object obj){
        DLLElement temp = new DLLElement();

        // insert temp in between
        temp.setNext(tail);
        temp.setPrevious(tail.getPrevious());
        temp.setObject(obj);
        // case 1: empty list
        if (tail.getPrevious() == null) { head.setNext(temp); }
        // case 2: there's existing item inside
        else { tail.getPrevious().setNext(temp); }

        tail.setPrevious(temp);

        length++;
    }

    void delete(Object... objs){
        DLLElement traversal = head;
        for (Object obj : objs) {
            traversal = head; // reset traversal to head every run
            while (traversal.getNext() != null) {
                if (traversal.getNext().getObject() == obj) {
                    traversal.setNext(traversal.getNext().getNext());
                    break;
                }
                traversal = traversal.getNext();
            }
        }
    }

    void traverseAndPrint(int step, int strIdx){
        if (strIdx != 0){
            this.setCurrElementIdx(strIdx); // 0 is default val, unless strIdx != 0
            System.out.print( this.getNextElement() + "\t, idx: \t" + this.getCurrElementIdx()+'\n' );
        }

        int s = step;
        while(s > 0){
            System.out.print( this.getCurrElement() + "\t, idx: \t" + this.getCurrElementIdx()+'\n' );
            this.getNextElement();
            s--;
        }
        while(s < 0){
            System.out.print( this.getCurrElement() + "\t, idx: \t" + this.getCurrElementIdx()+'\n' );
            this.getPrevElement();
            s++;
        }
    }

    void traverseAndPrint(int step){
        traverseAndPrint(step,0);
    }

    @Override
    public String toString(){
        // print each one by traversal
        StringBuilder str = new StringBuilder();
        str.append("[");
        DLLElement head_temp = head;
        // insert temp in between
        while(head_temp.getNext() != null){
            head_temp = head_temp.getNext();
            if (head_temp.getObject() != null) { str.append(head_temp.getObject() + ", ");}
        }
        // print last one
        str.delete(str.length()-1-1,str.length());
        str.append("]");
        return str.toString();
    }

    public static void main(String[] args) {

        // insert by helper method
        DoublyLinkedList d = new DoublyLinkedList();
        d.insertFirst('i');
        d.insertFirst('h');
        d.insertLast("there");
        System.out.println(d);

        // insert directly inside constructor
        DoublyLinkedList d2 = new DoublyLinkedList("Y",'e','S',"!!!",0,12,3.0, (int) 4.999f  );
        System.out.println(d2);

        System.out.println("\n\n");
        // showcase linkedlist traversal
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(1);

        System.out.println("\n\n");
        // reset linkedlist head
        d2.setCurrElementIdx(0);
        // showcase linkedlist traversal
        d2.traverseAndPrint(3);
        d2.traverseAndPrint(2);
        d2.traverseAndPrint(1);
        d2.traverseAndPrint(0);
        d2.traverseAndPrint(2);
    }
}
