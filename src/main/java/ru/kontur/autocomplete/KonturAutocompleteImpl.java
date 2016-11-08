package ru.kontur.autocomplete;

import ru.kontur.dictionary.KonturRatingDictionary;
import ru.kontur.util.Result;

import static ru.kontur.util.Result.fail;

class KonturAutocompleteImpl implements KonturAutocomplete {
    private KonturRatingDictionary dictionary;

    KonturAutocompleteImpl(KonturRatingDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public Result<String[]> getBests(String start) {
        String[] strings;
        try {
            strings = dictionary.search(start);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return Result.ok(strings);
    }
}
