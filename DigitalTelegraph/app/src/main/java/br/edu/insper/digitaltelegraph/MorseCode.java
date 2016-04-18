package br.edu.insper.digitaltelegraph;

import java.util.Map;

public class MorseCode {

    private Map<String, String> alphanumToMorse;
    private Map<String, String> morseToAlphanum;

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

        morseToAlphanum.put(".","E");
        morseToAlphanum.put("-","T");
        morseToAlphanum.put("..","I");
        morseToAlphanum.put(".-","A");
        morseToAlphanum.put("-.","N");
        morseToAlphanum.put("--","M");
        morseToAlphanum.put("...","S");
        morseToAlphanum.put("..-","U");
        morseToAlphanum.put(".-.","U");
        morseToAlphanum.put(".--","W");
        morseToAlphanum.put("-..","D");
        morseToAlphanum.put("-.-","K");
        morseToAlphanum.put("--.","G");
        morseToAlphanum.put("---","O");
        morseToAlphanum.put("....","H");
        morseToAlphanum.put("...-","V");
        morseToAlphanum.put("..-.","F");
        morseToAlphanum.put(".-..","L");
        morseToAlphanum.put(".--.","P");
        morseToAlphanum.put(".---","J");
        morseToAlphanum.put("-...","B");
        morseToAlphanum.put("-..-","X");
        morseToAlphanum.put("-.-.","C");
        morseToAlphanum.put("-.--","Y");
        morseToAlphanum.put("--..","Z");
        morseToAlphanum.put("--.-","Q");
        morseToAlphanum.put(".....","5");
        morseToAlphanum.put("....-","4");
        morseToAlphanum.put("...--","3");
        morseToAlphanum.put("..---","2");
        morseToAlphanum.put(".----","1");
        morseToAlphanum.put("-....","6");
        morseToAlphanum.put("--...","7");
        morseToAlphanum.put("---..","8");
        morseToAlphanum.put("----.","9");
        morseToAlphanum.put("-----","0");
    }

    public String getMorseCodeFromLetter(String letter) {
        return alphanumToMorse.get(letter);
    }

    public String getLetterFromMorseCode(String code) {
        return morseToAlphanum.get(code);
    }
}
