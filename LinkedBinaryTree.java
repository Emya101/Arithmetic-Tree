//Emhenya Supreme 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    protected static class Node<E> implements Position<E>{
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E e,Node<E> above,Node<E> leftChild,Node<E> rightChild){
            this.element=e;
            this.parent=above;
            this.left=leftChild;
            this.right=rightChild;
        }

        public E getElement(){return element;}
        public Node<E> getParent(){return parent;}
        public Node<E> getLeft(){return left;}
        public Node<E> getRight(){return right;}

        public void setElement(E e){element=e;}
        public void setParent(Node<E> parentNode){parent=parentNode;}
        public void setLeft(Node<E> leftChild){left=leftChild;}
        public void setRight(Node<E> rightChild){right=rightChild;}
    }
    protected Node<E> createNode(E e,Node<E> parent,Node<E> left,Node<E>right){
        return new Node<E>(e, parent, left, right);
    }
    protected Node<E> root=null;
    private int size=0;

    public LinkedBinaryTree(){}

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E>))
            throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p; 
        if (node.getParent() == node) 
            throw new IllegalArgumentException("Position no longer in the tree");
        return node;
    }

    public int size(){
        return size;
    }

    public Position<E> root(){
        return root;
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node=validate(p);
        return node.getParent();
    }
    public Position<E> left(Position<E> p) throws IllegalArgumentException{
        Node<E> node=validate(p);
        return node.getLeft();
    }
    public Position<E> right(Position<E> p) throws IllegalArgumentException{
        Node<E> node=validate(p);
        return node.getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("Position already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("Position already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("Position has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent()); 
        if (node == root)
            root = child; 
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null); 
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node);
        return temp;
    }

   public Iterator<E> iterator() {
        return new ElementIterator(); // Now this will be accessible to subclasses
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    protected class ElementIterator implements Iterator<E> {
        private Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement(); // Access the element of the position
        }

        public void remove() {
            posIterator.remove();
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Iterator<E> elementIterator = new ElementIterator(); // Use ElementIterator here

        public boolean hasNext() {
            return elementIterator.hasNext();
        }

        public Position<E> next() {
            // Since PositionIterator should return positions, wrap the elements into positions
            return new Position<E>() {
                public E getElement() {
                    return elementIterator.next(); // Return the next element from ElementIterator
                }
            };
        }

        public void remove() {
            elementIterator.remove();
        }
    }

    public Iterable<Position<E>> preorder() {
        List<Position<E>> traversal = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), traversal);
        }
        return traversal;
    }

    private void preorderSubtree(Position<E> p, List<Position<E>> traversal) {
        traversal.add(p);
        if (left(p) != null) {
            preorderSubtree(left(p), traversal);
        }
        if (right(p) != null) {
            preorderSubtree(right(p), traversal);
        }
    }

    
    public Iterable<Position<E>> postorder() {
        List<Position<E>> positions = new ArrayList<>();
        postorderSubtree(root(), positions);
        return positions;
    }

    private void postorderSubtree(Position<E> position, List<Position<E>> positions) {
        if (position != null) {
            postorderSubtree(left(position), positions);
            postorderSubtree(right(position), positions);
            positions.add(position);
        }
    }



    public Iterable<Position<E>> breadthfirst() {
        List<Position<E>> traversal = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> queue = new LinkedList<>();
            queue.add(root());
            while (!queue.isEmpty()) {
                Position<E> current = queue.remove();
                traversal.add(current);
                if (left(current) != null) {
                    queue.add(left(current));
                }
                if (right(current) != null) {
                    queue.add(right(current));
                }
            }
        }
        return traversal;
    }

    public Iterable<Position<E>> inorder() {
        List<Position<E>> traversal = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), traversal);
        }
        return traversal;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> traversal) {
        if (left(p) != null) {
            inorderSubtree(left(p), traversal);
        }
        traversal.add(p);
        if (right(p) != null) {
            inorderSubtree(right(p), traversal);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!isEmpty()) {
            toStringHelper(root(), 0, sb);
        }
        return sb.toString();
    }

    private void toStringHelper(Position<E> p, int depth, StringBuilder sb) {
        for (int i = 0; i < depth; i++) {
            sb.append("  "); // Add indentation based on depth
        }
        sb.append(p.getElement()).append("\n"); // Append element and new line
        for (Position<E> child : children(p)) {
            toStringHelper(child, depth + 1, sb); // Recursively traverse children
        }
    }
}

