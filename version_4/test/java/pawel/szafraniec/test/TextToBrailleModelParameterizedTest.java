package pawel.szafraniec.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.annotations.TestInfo;
import pawel.szafraniec.annotations.TestInfo.TestPriority;
import pawel.szafraniec.model.TextToBrailleModel;
import pawel.szafraniec.view.View;

/**
 * Class performing parameterized tests for TextToBrailleModel class
 * 
 * @author Pawel Szafraniec
 * @version 4.0
 */
@TestInfo(
testPriority = TestPriority.HIGH,
createdBy = "Pawel Szafraniec",
tags = {"ParameterizedTests","TextToBraille"}
)
public class TextToBrailleModelParameterizedTest {

    /**
     * Parameterized test for convertToBraille method
     * 
     * @param input input String
     * @param expected expected output String
     */
    @ParameterizedTest
    @CsvSource({
        "abc,\u2800\u2801\u2803", 
        "ABC,\u2800\u2801\u2803", 
        "1+1=2,\u2802\u282c\u2802\u283f\u2806",
        "123,\u2802\u2806\u2812",
        "1a 2b,\u2802\u2801 \u2806\u2803"})
    public void testConvert(String input, String expected) {
            TextToBrailleModel modelTextToBraille = new TextToBrailleModel();
            View view = new View();
        try {
            assertFalse((view.returnOutputStringBraille(modelTextToBraille.convertToBraille(input)).equals(expected)),
                    "true");
        } catch (InvalidInputException m) {
        }

    }
    
}

