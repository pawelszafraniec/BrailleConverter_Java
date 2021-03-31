package pawel.szafraniec.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;
import pawel.szafraniec.model.InvalidInputException;

/**
 * BrailleToTextModel class realizing operations of input string representing
 * Braille code to convert it to plain text
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
public class BrailleToTextModel {

    /**
     * Class constructor
     */
    public BrailleToTextModel() {
    }

    /**
     * Hash map representing numbers and corresponding Braille unicode values
     * Used in Braille to text conversion
     *
     */
    private final HashMap<String, String> brailleToTextMap = new HashMap<String, String>() {
        {
            put("10", "\u2800"); //white space
            put("11", "\u2801"); //A
            put("12", "\u2803"); //B
            put("13", "\u2809"); //C
            put("14", "\u2819"); //D
            put("15", "\u2811"); //E 
            put("16", "\u280B"); //F
            put("17", "\u281B"); //G
            put("18", "\u2813"); //H
            put("19", "\u280a"); //I
            put("20", "\u281a"); //J
            put("21", "\u2805"); //K
            put("22", "\u2807"); //L
            put("23", "\u280d"); //M
            put("24", "\u281d"); //N
            put("25", "\u2815"); //O
            put("26", "\u280f"); //P
            put("27", "\u281f"); //Q
            put("28", "\u2817"); //R
            put("29", "\u280e"); //S
            put("30", "\u281e"); //T
            put("31", "\u2825"); //U
            put("32", "\u2827"); //V
            put("33", "\u283a"); //W
            put("34", "\u282d"); //X
            put("35", "\u283d"); //Y
            put("36", "\u2835"); //Z      
            put("37", "\u2834"); //0
            put("38", "\u2802"); //1
            put("39", "\u2806"); //2
            put("40", "\u2812"); //3
            put("41", "\u2832"); //4
            put("42", "\u2822"); //5
            put("43", "\u2816"); //6
            put("44", "\u2836"); //7
            put("45", "\u2826"); //8
            put("46", "\u2814"); //9
        }
    };

    /**
     * Accessor for the hashMap
     *
     * @return HashMap
     */
    public HashMap<String, String> getMap() {
        return brailleToTextMap;
    }

    /**
     * Method splitting input String and placing it into List of Strings
     *
     * @param inputString user input
     * @return list of Strings containing input numbers
     * @throws InvalidInputException exception for empty input String
     */
    public List<String> getStringToList(String inputString) throws InvalidInputException {
        if (inputString.isBlank()) {
            throw new InvalidInputException("Input text is empty!");
        } else {

            Stream<String> stream = Arrays.stream(inputString.split(" "));
            List<String> list = new ArrayList<>();
            stream.forEach(s -> {
                boolean isKeyPresent = brailleToTextMap.containsKey(s);
                if (isKeyPresent) {
                    list.add((String) brailleToTextMap.get(s));
                }
            });
            return list;
        }
    }

    /**
     * Main operational method converting Braille Code to plain text
     *
     * @param userInput user input
     * @param map map used for decoding the text
     * @return converted to plain text map
     * @throws InvalidInputException exception for empty input String
     */
    public List<Character> convertToText(String userInput, HashMap<Character, String> map) throws InvalidInputException {
        if (userInput.isBlank()) {
            throw new InvalidInputException("Input text is empty!");
        } else {
            List<Character> outputList = new ArrayList<>();
            List<String> inputList = getStringToList(userInput);
            Stream<String> inputListStream = inputList.stream();
            inputListStream.forEach(s
                    -> {
                for (Entry<Character, String> entry : map.entrySet()) {
                    if (entry.getValue().equals(s)) {
                        outputList.add(entry.getKey());
                        break;
                    }

                }
            });            
            return outputList;
        }
    }
}
