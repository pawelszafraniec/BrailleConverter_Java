package pawel.szafraniec.test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pawel.szafraniec.annotations.TestInfo;
import pawel.szafraniec.model.BrailleToTextModel;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.model.TextToBrailleModel;
import pawel.szafraniec.view.View;

/**
 * Class performing tests for BrailleToTextModel class
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
@TestInfo(
        testPriority = TestInfo.TestPriority.MEDIUM,
        createdBy = "Pawel Szafraniec",
        tags = {"Unit Tests", "TextToBraille"}
)
public class BrailleToTextModelTest {

    /**
     * BrailleToTextModel model field
     */
    private BrailleToTextModel brailleToTextModel;

    /**
     * Annotated method used before each test creates new BrailleToTextModel
     * class object
     */
    @BeforeEach
    public void setup() {
        brailleToTextModel = new BrailleToTextModel();
    }

      /**
     * Test checking getStringToList method.
     *
     */
    @Test
    public void testGetStringToList() {
        List<String> testList = new ArrayList<>();
        testList.add("\u2801");
        testList.add("\u2801");
        testList.add("\u2803");
        testList.add("\u2800");
        testList.add("\u2809");
        testList.add("\u283a");
        try {
            assertFalse(!(brailleToTextModel.getStringToList("11 11 12 10 13 33").equals(testList)), "Fail");
        } catch (InvalidInputException m) {
        }

    }
    
    /**
     * Test checking the exception
     *
     */
    @Test
    public void testException() {
        try {
            brailleToTextModel.getStringToList(" ");
            fail("Exception for empty input string!");
        } catch (InvalidInputException m) {
        }
    }

}
