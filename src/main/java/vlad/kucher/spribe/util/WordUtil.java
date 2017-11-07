package vlad.kucher.spribe.util;

import java.util.Objects;

/**
 * Created by Vlad on 07.11.2017.
 */
public class WordUtil {

    private static final String WORD_REGEX = "[a-zA-Z]+";

    private WordUtil(){
    }

    public static void checkWord(String word) {
        Objects.requireNonNull(word, "Word must not be null");
        if (!word.matches(WordUtil.WORD_REGEX)) {
            throw new IllegalArgumentException("Invalid word");
        }
    }
}
