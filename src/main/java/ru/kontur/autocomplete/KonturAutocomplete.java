package ru.kontur.autocomplete;

import ru.kontur.dictionary.KonturRatingDictionary;
import ru.kontur.util.ValueResult;

import java.io.BufferedReader;

import static ru.kontur.util.ValueResult.*;

public interface KonturAutocomplete {

    static ValueResult<KonturAutocomplete> from(BufferedReader reader) {
        ValueResult<KonturRatingDictionary> dictionaryResult = KonturRatingDictionary.from(reader);
        if (dictionaryResult.isNotSuccess()) {
            return fail(dictionaryResult.getErrorMessage());
        }
        return ok(new KonturAutocompleteImpl(dictionaryResult.getValue()));
    }

    ValueResult<String[]> getBests(String start);
}
