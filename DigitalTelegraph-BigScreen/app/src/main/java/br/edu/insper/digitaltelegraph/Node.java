package br.edu.insper.digitaltelegraph;

public class Node {

    private String letter;
    private Node leftTree;
    private Node rightTree;

    public Node(String letter) {
        this.letter = letter;
    }

    public void setLeftChild(Node leftTree) {
        this.leftTree = leftTree;
    }

    public void setRightChild(Node rightTree) {
        this.rightTree = rightTree;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Node getLeftChild() {
        return this.leftTree;
    }

    public Node getRightChild() {
        return this.rightTree;
    }

    public String getLetter() {
        return this.letter;
    }

    public boolean hasChildTree(String tree) {
        switch(tree) {
            case "left":
                if(this.getLeftChild() != null) {
                    return true;
                }
                return false;
            case "right":
                if(this.getRightChild() != null) {
                    return true;
                }
                return false;
        }

        return false;
    }

}
