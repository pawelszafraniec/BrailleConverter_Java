package pawel.szafraniec.view;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * View class performing I/O operations
 *
 * @author Pawel Szafraniec
 * @version 4.0
 */
public class View {

    /**
     * Class constructor
     */
    public View() {
    }

    /**
     * Method reading input text from the user
     *
     * @return input string
     */
    public String readUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text: ");
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Method displaying text converted to Braille
     *
     * @param arrayList converted array list
     * @return converted String
     */
    public String returnOutputStringBraille(List<String> arrayList) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            printEx(e.getMessage());
        }

        String str = "";
        for (String s : arrayList) {
            str = str + s + " ";
        }
        return str;
    }

    /**
     * Method displaying converted input String to Braille unicode 
     * 
     * @param str String in Braille Unicode
     */
    public void printOutputStringBraille(String str)
    {  
        System.out.println("Braille: ");
         try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            printEx(e.getMessage());
        }
        System.out.println(str);
        
    }
    
    /**
     * Method returing Braille converted to plain text as string
     *
     * @param arrayList converted array list
     * @return converted String
     */
    public String returnOutputCharacterList(List<Character> arrayList) {
        String str = "";
        for (Character s : arrayList) {
            str = str + s;
        }
        return str;
    }

    /**
     * Method displaying output String
     *
     * @param str output string to print out
     */
    public void printOutputString(String str) {
        System.out.println("Text: ");
        System.out.println(str);
    }

    /**
     * Method displaying Hash map
     *
     * @param map  hash map to display
     */
    public void printHashMap(HashMap<String, String> map) {

        TreeMap<String, String> sortedMap = new TreeMap<>(map);
        Set<Map.Entry<String, String>> mapEntry = sortedMap.entrySet();

        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (UnsupportedEncodingException e) {
            printEx(e.getMessage());
        }
        mapEntry.forEach(entry -> {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        });
    }

    /**
     * Method displaying instruction in Braille to text conversion
     *
     */
    public void printInstruction() {
        System.out.println(" Enter the string using numbers presented above. Each Braille symbol has corresponding numeric value.\n"
                + " Separate each entered number with a whitespace.\n"
                + " To start conversion push enter. \n"
                + " Exemplary input string: 10 11 12 ");
    }

    /**
     * Method printing out exception message
     *
     * @param message error message to print
     */
    public void printEx(String message) {
        System.out.println(message);
    }

    /**
     * Method displaying menu of the program and reading option chosen by the
     * user
     *
     * @return user option
     */
    public String menuOption() {

        System.out.println("Braille Converter: ");
        System.out.println("> to convert from text to Braille press 1 ");
        System.out.println("> to convert from Braille to text press 2 ");
        System.out.println("> press 3 to exit: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Option: ");
        String option = scanner.nextLine();
        return option;
    }

    /**
     * Method priniting out message for default option in main loop switch
     */
    public void printDefaultOption() {
        System.out.println("No such option.");
    }

}
