package br.edu.insper.digitaltelegraph;

public class BinaryTree {

    private Root root;
    private BinaryTree leftTree;
    private BinaryTree rightTree;

    public void setLeftTree(BinaryTree leftTree) {
        this.leftTree = leftTree;
    }

    public void setRightTree(BinaryTree rightTree) {
        this.rightTree = rightTree;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public BinaryTree getLeftTree() {
        return this.leftTree;
    }

    public BinaryTree getRightTree() {
        return this.rightTree;
    }

    public Root getRoot() {
        return this.root;
    }

}
