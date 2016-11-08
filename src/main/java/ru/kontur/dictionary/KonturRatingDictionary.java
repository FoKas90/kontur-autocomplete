package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;

import java.io.BufferedReader;
import java.io.IOException;

import static ru.kontur.util.Result.fail;
import static ru.kontur.util.Result.ok;

public interface KonturRatingDictionary {
    static Result<KonturRatingDictionary> from(BufferedReader reader) {
        try {
            String line = reader.readLine();
            Result<RestrictSizeInt> sizeResult = DictionarySize.parse(line);
            if (sizeResult.isSuccess()) {
                KonturRatingDictionaryImpl dictionary = new KonturRatingDictionaryImpl();
                int size = sizeResult.getValue().size;
                for (int i = 0; i < size; i++) {
                    line = reader.readLine();
                    Result<KonturDictionaryWord> wordResult = KonturDictionaryWord.parse(line);
                    if (wordResult.isSuccess()) {
                        dictionary.addWord(wordResult.getValue());
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

    void addWord(KonturDictionaryWord word);

    String[] search(String what);
}
