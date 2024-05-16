 

public class ArithmeticTreeDriver{
/**
 * Write a description of class PartA_Driver here.
 *
 * Emhenya Supreme
 * 
 */
    public static void main(String[] args) {
        LinkedBinaryTree<String> arithmeticTree = createTree();
        System.out.println("\nArithmetic Tree\n---------------\n");
        System.out.println("Inorder:" + inorder(arithmeticTree));
        System.out.println("\n");
        System.out.println("Postorder:" + postorder(arithmeticTree));
        System.out.println("\n");
        int result = evaluateTree(arithmeticTree);
        System.out.println("Tree value: " + result);

    }

    /**
     * Creates and returns an arithmetic expression tree.
     *
     * @return An arithmetic expression tree.
     */
    private static LinkedBinaryTree<String> createTree() {
        LinkedBinaryTree<String> Tree = new LinkedBinaryTree<>();
        Position<String> root = Tree.addRoot("x");

        Tree.addRight(root, "10");
        
        Position<String> plus1 = Tree.addLeft(root, "+");
        Position<String> plus5 = Tree.addLeft(plus1, "+");
        Position<String> minus = Tree.addLeft(plus5, "-");
        Tree.addLeft(minus, "5");
        Tree.addRight(minus, "2");
        Position<String> times1 = Tree.addRight(plus5, "x");
        Position<String> minus2 = Tree.addRight(times1, "-");
        Tree.addLeft(times1, "4");
        Tree.addLeft(minus2, "8");
        Position<String> plus4 = Tree.addRight(minus2, "+");
        Tree.addLeft(plus4, "3");
        Tree.addRight(plus4, "1");
        Position<String> times2 = Tree.addRight(plus1, "x");
        Position<String> minus3 = Tree.addRight(times2, "-");
        Position<String> times3 = Tree.addLeft(minus3, "x");
        Position<String> plus6 = Tree.addRight(minus3, "+");
        Tree.addLeft(times2, "9");
        Tree.addLeft(times3, "3");
        Tree.addRight(times3, "6");
        Tree.addLeft(plus6, "7");
        Tree.addRight(plus6, "2");
        
        return Tree;
    }
    
    
/**
     * Returns the inorder traversal of a tree as a string.
     *
     * @param tree The tree to traverse.
     * @return The inorder traversal of the tree as a string.
     */
    private static String inorder(LinkedBinaryTree<String> tree) {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (Position<String> position : tree.inorder()) {
            result.append(position.getElement()).append(", ");
        }
        result.append("]");
        return result.toString().trim();
    }

     /**
     * Returns the postorder traversal of a tree as a string.
     *
     * @param tree The tree to traverse.
     * @return The postorder traversal of the tree as a string.
     */
    private static String postorder(LinkedBinaryTree<String> tree) {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (Position<String> position : tree.postorder()) {
            result.append(position.getElement()).append(", ");
        }
        result.append("]");
        return result.toString().trim();
    }
/**
     * Evaluates an arithmetic expression represented by a tree and returns the result.
     *
     * @param tree The arithmetic expression tree to evaluate.
     * @return The result of the arithmetic expression evaluation.
     */
    private static int evaluateTree(LinkedBinaryTree<String> tree) {
 
        LinkedStack<Integer> stack = new LinkedStack<>();
        StringBuilder builder = new StringBuilder();

        for (Position<String> position: tree.postorder()) {
            String element = position.getElement();
            char c = element.charAt(0); 
            if (Character.isDigit(c) || c == '+' || c == '-' || c == 'x') {
                builder.append(c);
            }
        }

        String order =  builder.toString();

        for (char c : order.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(Character.getNumericValue(c));
            } 
            else {
                int oper2 = stack.pop();
                int oper1 = stack.pop();
                int result = 0;
                switch (c) {
                    case '+':
                        result = oper1 + oper2;
                        break;
                    case '-':
                        result = oper1 - oper2;
                        break;
                    case 'x':
                        result = oper1 * oper2;
                        break;
                }
                stack.push(result);
            }
        }
        return stack.pop() * 10;
    }
}
