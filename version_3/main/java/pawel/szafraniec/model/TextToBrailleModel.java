package pawel.szafraniec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TextToBrailleModel class realizing operations of input string to convert it
 * to Braille
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
public class TextToBrailleModel {

    /**
     * Class constructor
     */
    public TextToBrailleModel() {
    }

    /**
     * Hash map representing English alphabet, signs and corresponding unicode
     * values
     */
    private final HashMap<Character, String> textToBrailleMap = new HashMap<Character, String>() {
        {
            put(' ', "\u2800");
            put('A', "\u2801");
            put('B', "\u2803");
            put('C', "\u2809");
            put('D', "\u2819");
            put('E', "\u2811");
            put('F', "\u280B");
            put('G', "\u281B");
            put('H', "\u2813");
            put('I', "\u280a");
            put('J', "\u281a");
            put('K', "\u2805");
            put('L', "\u2807");
            put('M', "\u280d");
            put('N', "\u281d");
            put('O', "\u2815");
            put('P', "\u280f");
            put('Q', "\u281f");
            put('R', "\u2817");
            put('S', "\u280e");
            put('T', "\u281e");
            put('U', "\u2825");
            put('V', "\u2827");
            put('W', "\u283a");
            put('X', "\u282d");
            put('Y', "\u283d");
            put('Z', "\u2835");
            put('a', "\u2801");
            put('b', "\u2803");
            put('c', "\u2809");
            put('d', "\u2819");
            put('e', "\u2811");
            put('f', "\u280B");
            put('g', "\u281B");
            put('h', "\u2813");
            put('i', "\u280a");
            put('j', "\u281a");
            put('k', "\u2805");
            put('l', "\u2807");
            put('m', "\u280d");
            put('n', "\u281d");
            put('o', "\u2815");
            put('p', "\u280f");
            put('q', "\u281f");
            put('r', "\u2817");
            put('s', "\u280e");
            put('t', "\u281e");
            put('u', "\u2825");
            put('v', "\u2827");
            put('w', "\u283a");
            put('x', "\u282d");
            put('y', "\u283d");
            put('z', "\u2835");
            put('0', "\u2834");
            put('1', "\u2802");
            put('2', "\u2806");
            put('3', "\u2812");
            put('4', "\u2832");
            put('5', "\u2822");
            put('6', "\u2816");
            put('7', "\u2836");
            put('8', "\u2826");
            put('9', "\u2814");
            put('@', "\u2808");
            put('/', "\u280c");
            put('"', "\u2810");
            put('^', "\u2818");
            put('>', "\u281c");
            put('<', "\u2823");
            put(',', "\u2820");
            put('*', "\u2821");
            put('.', "\u2828");
            put('%', "\u2829");
            put('[', "\u282a");
            put('$', "\u282b");
            put('+', "\u282c");
            put('!', "\u282e");
            put('&', "\u282f");
            put(';', "\u2830");
            put(':', "\u2831");
            put('(', "\u2837");
            put('_', "\u2838");
            put('?', "\u2839");
            put(']', "\u283b");
            put('#', "\u283c");
            put(')', "\u283e");
            put('=', "\u283f");
        }
    };

    /**
     * Accessor for the hashMap
     *
     * @return HashMap
     */
    public HashMap<Character, String> getMap() {
        return textToBrailleMap;
    }

    /**
     * Method copying digits of input string into list of Characters
     *
     * @param inputString representing user input string
     * @return copied list of characters
     * @throws InvalidInputException exception for empty input String
     */
    public List<Character> copyStringToCharList(String inputString) throws InvalidInputException {
        if (inputString.isBlank()) {
            throw new InvalidInputException("Input text is empty!");
        } else {
            List<Character> inputTextList = inputString
                    .chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toList());
            return inputTextList;
        }
    }

    /**
     * Main operational method converting plain text to Braille code
     *
     * @param inputString user input
     * @return List containing elements converted to Braille alphabet
     * @throws InvalidInputException exception for empty input String
     */
    public List<String> convertToBraille(String inputString) throws InvalidInputException {
        if (inputString.isBlank()) {
            throw new InvalidInputException("Input text is empty!");
        } else {

            List<String> outputTextList = new ArrayList<>();
            Stream<Character> stream = copyStringToCharList(inputString).stream();
            //assign unicode values 
            stream.forEach(s -> {
                boolean isKeyPresent = textToBrailleMap.containsKey(s);
                //if given key exists in the map
                if (isKeyPresent) {
                    outputTextList.add((String) textToBrailleMap.get(s));
                }

            });

            return outputTextList;

        }
    }
    
}
