package pawel.szafraniec.test;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.model.TextToBrailleModel;

/**
 * Class performing tests for TextToBrailleModel class
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
@pawel.szafraniec.annotations.TestInfo(
        testPriority = pawel.szafraniec.annotations.TestInfo.TestPriority.MEDIUM,
        createdBy = "Pawel Szafraniec",
        tags = {"Unit tests", "TextToBraille"}
)
public class TextToBrailleModelTest {

    /**
     * TextToBrailleModel model field
     */
    private TextToBrailleModel textToBrailleModel;

    /**
     * Annotated method used before each test creates new TextToBrailleModel
     * class object
     *
     */
    @BeforeEach
    public void setup() {
        textToBrailleModel = new TextToBrailleModel();
    }

  /**
     * Test checking copyStringToCharList method.
     *
     */
    @Test
    public void copyStringToCharList() {

        List<Character> testList = new ArrayList<>();
        testList.add('a');
        testList.add('b');
        testList.add('c');
        try {
            assertFalse((!testList.equals(textToBrailleModel.copyStringToCharList("abc"))), "Fail");
        } catch (InvalidInputException m) {
        }

    }

    /**
     * Test checking the Exception
     *
     */
    @Test
    public void testException() {

        try {
            textToBrailleModel.copyStringToCharList(" ");
            fail("Exception for empty input string!");
        } catch (InvalidInputException m) {
        }

    }

}
