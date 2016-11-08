package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;

import java.util.function.Predicate;

class RatingSize {

    private static Predicate<Integer> restrictionCheck = size -> !(size < 1 || size > 1_000_000);

    public static Result<RestrictSizeInt> parse(String sizeInt) {
        return RestrictSizeInt.parse(sizeInt, restrictionCheck);
    }

    static Result<RestrictSizeInt> from(int ratingCandidate) {
        return RestrictSizeInt.from(ratingCandidate, restrictionCheck);
    }
}
