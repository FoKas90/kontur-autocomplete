package ru.kontur.autocomplete;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;

public class RequestCountSize {
    public static Result<RestrictSizeInt> parse(String sizeStr) {
        return RestrictSizeInt.parse(sizeStr, size -> !(size < 1 || size > 15_000));
    }
}
