package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.ValueResult;

class DictionarySize {
    final int size;

    private DictionarySize(int size) {

        this.size = size;
    }

    static ValueResult<RestrictSizeInt> parse(String sizeStr) {
        return RestrictSizeInt.parse(sizeStr, size -> !(size < 1 || size > 100_000));
    }
}
