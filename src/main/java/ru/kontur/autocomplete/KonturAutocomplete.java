package ru.kontur.autocomplete;

import ru.kontur.dictionary.KonturRatingDictionary;
import ru.kontur.util.Result;

import java.io.BufferedReader;

import static ru.kontur.util.Result.fail;
import static ru.kontur.util.Result.ok;

public interface KonturAutocomplete {

    static Result<KonturAutocomplete> from(BufferedReader reader) {
        Result<KonturRatingDictionary> dictionaryResult = KonturRatingDictionary.from(reader);
        if (dictionaryResult.isNotSuccess()) {
            return fail(dictionaryResult.getErrorMessage());
        }
        return ok(new KonturAutocompleteImpl(dictionaryResult.getValue()));
    }

    Result<String[]> getBests(String start);
}
