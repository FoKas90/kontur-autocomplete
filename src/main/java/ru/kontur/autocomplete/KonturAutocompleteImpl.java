package ru.kontur.autocomplete;

import ru.kontur.dictionary.KonturRatingDictionary;
import ru.kontur.util.ValueResult;

import static ru.kontur.util.ValueResult.fail;
import static ru.kontur.util.ValueResult.ok;

class KonturAutocompleteImpl implements KonturAutocomplete {
    private KonturRatingDictionary dictionary;

    KonturAutocompleteImpl(KonturRatingDictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public ValueResult<String[]> getBests(String start) {
        String[] strings;
        try {
            strings = dictionary.search(start);
        } catch (Exception e) {
            return fail(e.getMessage());
        }
        return ok(strings);
    }
}
