package br.edu.insper.digitaltelegraph;

public class BFS {

    private final static int NUM_TREES = 40;

    public static void main(String[] args) {

        BinaryTree[] trees = new BinaryTree[NUM_TREES];
        String[] alphanumChars = {"E","T","I","A","N","M","S","U","R","W","D","K","G","O","H","V","F","L",
                "P","J","B","X","C","Y","Z","Q","5","4","3","2","1","6","7","8","9","0"};

        for(int i = 0; i < NUM_TREES; i++) {
            trees[i] = new BinaryTree();
        }

        int i = 0;
        for(String letter: alphanumChars) {
            if(i < alphanumChars.length) {
                trees[i].setRoot(new Root(letter));
            }
            i++;
        }

        trees[0].setLeftTree(trees[1]);
        trees[0].setRightTree(trees[2]);
        trees[1].setLeftTree(trees[3]);
        trees[1].setRightTree(trees[4]);
        trees[2].setLeftTree(trees[5]);
        trees[2].setRightTree(trees[6]);
        trees[3].setLeftTree(trees[7]);
        trees[3].setRightTree(trees[8]);
        trees[4].setLeftTree(trees[9]);
        trees[4].setRightTree(trees[10]);
        trees[5].setLeftTree(trees[11]);
        trees[5].setRightTree(trees[12]);
        trees[6].setLeftTree(trees[13]);
        trees[6].setRightTree(trees[14]);
        trees[7].setLeftTree(trees[15]);
        trees[7].setRightTree(trees[16]);
        trees[8].setLeftTree(trees[17]);
        trees[8].setRightTree(trees[18]);
        trees[9].setLeftTree(trees[19]);
        trees[10].setLeftTree(trees[20]);
        trees[10].setRightTree(trees[21]);
        trees[11].setLeftTree(trees[22]);
        trees[11].setRightTree(trees[23]);
        trees[12].setLeftTree(trees[24]);
        trees[12].setRightTree(trees[25]);
        trees[13].setLeftTree(trees[26]);
        trees[13].setRightTree(trees[27]);
        trees[14].setLeftTree(trees[28]);
        trees[14].setRightTree(trees[29]);
        trees[15].setLeftTree(trees[30]);
        trees[15].setRightTree(trees[31]);
        trees[16].setRightTree(trees[32]);
        trees[18].setRightTree(trees[33]);
        trees[21].setRightTree(trees[34]);
        trees[22].setLeftTree(trees[35]);
        trees[26].setLeftTree(trees[36]);
        trees[28].setLeftTree(trees[37]);
        trees[29].setLeftTree(trees[38]);
        trees[29].setRightTree(trees[39]);
    }
}