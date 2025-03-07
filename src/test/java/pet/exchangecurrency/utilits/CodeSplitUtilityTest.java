package pet.exchangecurrency.utilits;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeSplitUtilityTest {

    @Test
    void splitCode_validCode_returnsCorrectSplit() {
        String code = "ABCDEF";
        List<String> expected = List.of("ABC", "DEF");
        List<String> actual = CodeSplitUtility.splitCode(code);

        assertEquals(expected, actual);
    }

    @Test
    void splitCode_codeShorterThanSixChars_throwsIllegalArgumentException() {
        String code = "ABCDE";
        assertThrows(IllegalArgumentException.class, () -> CodeSplitUtility.splitCode(code));
    }

    @Test
    void splitCode_codeLongerThanSixChars_throwsIllegalArgumentException() {
        String code = "ABCDEFG";
        assertThrows(IllegalArgumentException.class, () -> CodeSplitUtility.splitCode(code));
    }

    @Test
    void splitCode_emptyCode_throwsIllegalArgumentException() {
        String code = "";
        assertThrows(IllegalArgumentException.class, () -> CodeSplitUtility.splitCode(code));
    }

    @Test
    void splitCode_nullCode_throwsIllegalArgumentException() {
        String code = null;
        assertThrows(IllegalArgumentException.class, () -> CodeSplitUtility.splitCode(code));
    }
}

