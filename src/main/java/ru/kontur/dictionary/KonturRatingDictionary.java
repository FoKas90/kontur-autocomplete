package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;
import ru.kontur.util.ValueResult;

import java.io.BufferedReader;
import java.io.IOException;

import static ru.kontur.util.ValueResult.fail;
import static ru.kontur.util.ValueResult.ok;

public interface KonturRatingDictionary {
    static ValueResult<KonturRatingDictionary> from(BufferedReader reader) {
        try {
            String line = reader.readLine();
            ValueResult<RestrictSizeInt> sizeResult = DictionarySize.parse(line);
            if (sizeResult.isSuccess()) {
                KonturRatingDictionaryImpl dictionary = new KonturRatingDictionaryImpl();
                int size = sizeResult.getValue().size;
                for (int i = 0; i < size; i++) {
                    line = reader.readLine();
                    ValueResult<KonturDictionaryWord> wordResult = KonturDictionaryWord.parse(line);
                    if (wordResult.isSuccess()) {
                        Result addWordResult = dictionary.addWord(wordResult.getValue());
                        if (addWordResult.isNotSuccess()) {
                            return fail(addWordResult.getErrorMessage());
                        }
                    } else {
                        return fail(wordResult.getErrorMessage());
                    }
                }
                return ok(dictionary);
            } else {
                return fail(sizeResult.getErrorMessage());
            }
        } catch (IOException e) {
            return fail(e.getMessage());
        }
    }
    String[] search(String what);
}
