package general_prob.double_linked_list;

public class DLLElement {

    private Object object; // initialized as null
    private DLLElement next, previous; // initialized as null

    DLLElement getPrevious(){
        return previous;
    }
    DLLElement getNext(){
        return next;
    }
    Object getObject(){
        return object;
    }

    void setPrevious(DLLElement prev){
        this.previous = prev;
    }
    void setNext(DLLElement next){
        this.next = next;
    }
    void setObject(Object object){
        this.object = object;
    }
}
