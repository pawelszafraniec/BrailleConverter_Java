package pawel.szafraniec.test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pawel.szafraniec.annotations.TestInfo;
import pawel.szafraniec.model.BrailleToTextModel;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.model.TextToBrailleModel;
import pawel.szafraniec.view.View;

/**
 * Class performing parameterized tests for BrailleToTextModel class
 *
 * @author Pawel Szafraniec
 * @version 3.0
 */
@TestInfo(
        testPriority = TestInfo.TestPriority.HIGH,
        createdBy = "Pawel Szafraniec",
        tags = {"ParameterizedTests", "BrailleToText"}
)
public class BrailleToTextModelParameterizedTest {

    /**
     * Parameterized test checking convertToText method
     *
     * @param input user input String
     * @param expected expected output String
     */
    @ParameterizedTest
    @CsvSource({
        "11 11 12, AAB",
        "12 13 14,BCD",
        "38 39 40,123",
        "11 10 10 10 11 ,A   A",
        "11 38 10 12 39,A1 B2"})
    public void testConvert(String input, String expected) {

        BrailleToTextModel modelBrailleToText = new BrailleToTextModel();
        View view = new View();
        TextToBrailleModel modelTextToBraille = new TextToBrailleModel();
        try {
            assertFalse((!view.returnOutputCharacterList(modelBrailleToText.convertToText(input, modelTextToBraille.getMap()))
                    .equals(expected)), "true");
        } catch (InvalidInputException m) {
        }
    }
}
