package Trees;

import Lists.MyVector;

public class BinarySearchTree {
    private class BinaryNode<AnyType> {
        private AnyType value;
        private BinaryNode leftChild = null;
        private BinaryNode rightChild = null;

        BinaryNode(AnyType elem) {
            this(elem, null, null);
        }

        BinaryNode(AnyType elem, BinaryNode left, BinaryNode right) {
            this.value = elem;
            this.leftChild = left;
            this.rightChild = right;
        }

//        public void setLeftChild(BinaryNode child) {
//            leftChild = child;
//        }
//
//        public void setRightChild(BinaryNode child) {
//            rightChild = child;
//        }
//
//        public BinaryNode getLeftChild() {
//            return leftChild;
//        }
//
//        public BinaryNode getRightChild() {
//            return rightChild;
//        }
//
//        public AnyType getValue() {
//            return value;
//        }
//
//        public void setValue(AnyType value) {
//            this.value = value;
//        }
    }

    private BinaryNode<Comparable> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Comparable rootValue) {
        this.root = new BinaryNode(rootValue);
    }

    public BinaryNode<Comparable> getRoot() {
        return root;
    }

    public void insert(Comparable elem) {
        insert(new BinaryNode<>(elem), root);
    }

    private BinaryNode insert(BinaryNode<Comparable> childNode, BinaryNode<Comparable> parentNode) {
        // Break condition
        if(parentNode == null) {
            return childNode;
        }

        // Search logic
        if(childNode.value.compareTo(parentNode.value) < 0) {
            parentNode.leftChild = insert(childNode, parentNode.leftChild);
        }
        else if(childNode.value.compareTo(parentNode.value) > 0) {
            parentNode.rightChild = insert(childNode, parentNode.rightChild);
        }
        // else element is already present, then ignore.
        return parentNode;
    }

    public void remove(Comparable elem) {
        root = remove(elem, root);
    }

    private BinaryNode remove(Comparable elem, BinaryNode<Comparable> node) {
        // Break condition, in case no element to be removed
        if(node == null)
            return null;

        // Search node to be removed
        int compareResult = elem.compareTo(node.value);
        if(compareResult < 0) {
            node.leftChild = remove(elem, node.leftChild);
        }
        else if(compareResult > 0) {
            node.rightChild = remove(elem, node.rightChild);
        }
        else {
            //Else node is to be removed
            if(node.leftChild == null)
                node = node.rightChild;
            else if(node.rightChild == null)
                node = node.leftChild;
            else {
                // Node has 2 children
                node.value = (Comparable) getMaxNode(node.leftChild).value;         // Replace value of node to be removed
                node.leftChild = remove(node.value, node.leftChild);                // Remove now-duplicate value from left subtree
            }
        }
        return node;
    }

    private BinaryNode<Comparable> findNode(Comparable elem, BinaryNode node) {
        if(node == null)
            return null;

        if(elem.compareTo(node.value) < 0) {
            return findNode(elem, node.leftChild);
        }
        else if(elem.compareTo(node.value) > 0) {
            return findNode(elem, node.rightChild);
        }
        else {  // Node was found
            return node;
        }
    }

    private BinaryNode<Comparable> getMaxNode(BinaryNode<Comparable> node) {
        if(node == null)
            return null;

        if(node.rightChild == null)
            return node;
        else
            return getMaxNode(node.rightChild);
    }

    public void displayByLevel() throws Exception{
        MyVector<BinaryNode> nodeStack = new MyVector<>();
        nodeStack.add(root);
        System.out.println(root.value);
        int currentIndex = 0;
        while(currentIndex < nodeStack.getSize()) {
            BinaryNode currentNode = nodeStack.get(currentIndex++);
            if(currentNode.leftChild != null) {
                nodeStack.add(currentNode.leftChild);
                System.out.print("<- " + currentNode.leftChild.value + " ");
            }
            if(currentNode.rightChild != null) {
                nodeStack.add(currentNode.rightChild);
                System.out.print(" " + currentNode.rightChild.value + " ->");
            }
            System.out.println();
        }
        System.out.println();
    }
}
