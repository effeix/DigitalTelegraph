package br.edu.insper.digitaltelegraph;

public class Root {
    private String letter;

    public Root(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return this.letter;
    }

    public boolean hasLetter() {
        if(this.getLetter() != null) {
            return true;
        }
        return false;
    }

}
