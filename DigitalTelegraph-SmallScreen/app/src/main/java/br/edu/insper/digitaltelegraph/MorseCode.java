package br.edu.insper.digitaltelegraph;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {

    private Map<String, String> alphanumToMorse = new HashMap<>();

    public MorseCode() {

        alphanumToMorse.put("E",".");
        alphanumToMorse.put("T","-");
        alphanumToMorse.put("I","..");
        alphanumToMorse.put("A",".-");
        alphanumToMorse.put("N","-.");
        alphanumToMorse.put("M","--");
        alphanumToMorse.put("S","...");
        alphanumToMorse.put("U","..-");
        alphanumToMorse.put("R",".-.");
        alphanumToMorse.put("W",".--");
        alphanumToMorse.put("D","-..");
        alphanumToMorse.put("K","-.-");
        alphanumToMorse.put("G","--.");
        alphanumToMorse.put("O","---");
        alphanumToMorse.put("H","....");
        alphanumToMorse.put("V","...-");
        alphanumToMorse.put("F","..-.");
        alphanumToMorse.put("L",".-..");
        alphanumToMorse.put("P",".--.");
        alphanumToMorse.put("J",".---");
        alphanumToMorse.put("B","-...");
        alphanumToMorse.put("X","-..-");
        alphanumToMorse.put("C","-.-.");
        alphanumToMorse.put("Y","-.--");
        alphanumToMorse.put("Z","--..");
        alphanumToMorse.put("Q","--.-");
        alphanumToMorse.put("5",".....");
        alphanumToMorse.put("4","....-");
        alphanumToMorse.put("3","...--");
        alphanumToMorse.put("2","..---");
        alphanumToMorse.put("1",".----");
        alphanumToMorse.put("6","-....");
        alphanumToMorse.put("7","--...");
        alphanumToMorse.put("8","---..");
        alphanumToMorse.put("9","----.");
        alphanumToMorse.put("0","-----");
    }

    public String getMorseCodeFromLetter(String letter) {
        return alphanumToMorse.get(letter);
    }
}
