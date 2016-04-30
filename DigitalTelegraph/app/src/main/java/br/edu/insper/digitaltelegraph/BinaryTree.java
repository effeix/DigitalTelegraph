package br.edu.insper.digitaltelegraph;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    private final static int NUM_TREES = 40;
    private Node[] nodes;

    public BinaryTree() {

        this.nodes = new Node[NUM_TREES];

        String[] alphanumChars = {"","E","T","I","A","N","M","S","U","R","W","D","K","G","O","H","V","F","","L",
                "P","J","B","X","C","Y","Z","Q","","","5","4","3","2","1","6","7","8","9","0"};

        for(int i = 0; i < NUM_TREES; i++) {
            this.nodes[i] = new Node(alphanumChars[i]);
        }

        nodes[0].setLeftChild(nodes[1]);
        nodes[0].setRightChild(nodes[2]);
        nodes[1].setLeftChild(nodes[3]);
        nodes[1].setRightChild(nodes[4]);
        nodes[2].setLeftChild(nodes[5]);
        nodes[2].setRightChild(nodes[6]);
        nodes[3].setLeftChild(nodes[7]);
        nodes[3].setRightChild(nodes[8]);
        nodes[4].setLeftChild(nodes[9]);
        nodes[4].setRightChild(nodes[10]);
        nodes[5].setLeftChild(nodes[11]);
        nodes[5].setRightChild(nodes[12]);
        nodes[6].setLeftChild(nodes[13]);
        nodes[6].setRightChild(nodes[14]);
        nodes[7].setLeftChild(nodes[15]);
        nodes[7].setRightChild(nodes[16]);
        nodes[8].setLeftChild(nodes[17]);
        nodes[8].setRightChild(nodes[18]);
        nodes[9].setLeftChild(nodes[19]);
        nodes[10].setLeftChild(nodes[20]);
        nodes[10].setRightChild(nodes[21]);
        nodes[11].setLeftChild(nodes[22]);
        nodes[11].setRightChild(nodes[23]);
        nodes[12].setLeftChild(nodes[24]);
        nodes[12].setRightChild(nodes[25]);
        nodes[13].setLeftChild(nodes[26]);
        nodes[13].setRightChild(nodes[27]);
        nodes[14].setLeftChild(nodes[28]);
        nodes[14].setRightChild(nodes[29]);
        nodes[15].setLeftChild(nodes[30]);
        nodes[15].setRightChild(nodes[31]);
        nodes[16].setRightChild(nodes[32]);
        nodes[18].setRightChild(nodes[33]);
        nodes[21].setRightChild(nodes[34]);
        nodes[22].setLeftChild(nodes[35]);
        nodes[26].setLeftChild(nodes[36]);
        nodes[28].setLeftChild(nodes[37]);
        nodes[29].setLeftChild(nodes[38]);
        nodes[29].setRightChild(nodes[39]);
    }

    public Node[] getBinaryTree() {
        return this.nodes;
    }

    public List<String> BFS() {
        List<String> characters = new ArrayList<>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(nodes[0]);

        while(!queue.isEmpty()) {
            Node node = (Node) queue.remove();
            characters.add(node.getLetter());

            if(node.hasChildTree("left")) {
                queue.add(node.getLeftChild());
            }

            if(node.hasChildTree("right")) {
                queue.add(node.getRightChild());
            }
        }

        return characters;
    }
}