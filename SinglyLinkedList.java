//Emhenya Supreme 

public class SinglyLinkedList<E>{
    
    // private nested node class
    private static class Node<E>{
        // fields
        private E element;
        private Node<E> nextNode;

        public Node(E e, Node<E> node){
            this.element = e;
            this.nextNode = node;
        }

        public E getElement(){
            return this.element;
        }

        public Node<E> getNextNode(){
            return this.nextNode;
        }

        public void setElement(E e){
            this.element = e;
        }

        public void setNextNode(Node<E> node){
            this.nextNode = node;
        }
    }

    // parent class fields
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList(){
        this.head = new Node<E>(null, null);
        this.tail = new Node<E>(null, null);
        this.size = 0;
    }

    public void addFirst(E element){
        Node<E> newNode;
        if(isEmpty()){
            newNode = new Node(element, this.head);
            this.head = newNode;
            this.tail = this.head;
        }else{
            newNode = new Node(element, this.head);

            // set the new head to be the new node
            this.head = newNode;
        }
        // increment the size of the list
        this.size++;
    }
    
    public void addLast(E element){
        Node<E> newNode = new Node<E>(element, null);
        if(isEmpty()){
            this.head = newNode;
        }
        newNode = new Node(element, null);
        // set the new head to be the new node
        this.tail = newNode;
        // increment the size of the list
        this.size++;
    }

    public E removeFirst(){
        if(isEmpty()) return null;

        E element_to_be_removed = this.head.getElement();
        // set the new head to be the node that comes after the current head;
        this.head = this.head.getNextNode();
        // decrement the size of the list
        this.size--;
        return element_to_be_removed;
    }

    // other methods
    public E first(){
        return this.head.getElement();
    }

    public E last(){
        return this.tail.getElement();
    }

    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        StringBuilder values = new StringBuilder();
        Node<E> nextNode = this.head;
        E element;
        values.append("[").append("\n\n");

        do{
            element = nextNode.getElement();
            nextNode = nextNode.getNextNode();
            values.append(element).append("\n\n");
        }while(nextNode != null);
        values.append("]");
        return values.toString();
    }
}