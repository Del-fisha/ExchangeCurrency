package pet.exchangecurrency.utilits;

import java.util.ArrayList;
import java.util.List;

public class CodeSplitUtility {
    public static List<String> splitCode(String code) {

        if (code == null || code.length() != 6) {
            throw new IllegalArgumentException("Code must be exactly 6 characters long.");
        }

        List<String> split = new ArrayList<>(2);

        split.add(code.substring(0, 3));
        split.add(code.substring(3, 6));

        return split;
    }
}
