package ru.kontur.util;

import java.util.function.Predicate;

import static java.lang.Integer.parseInt;
import static ru.kontur.util.ValueResult.fail;
import static ru.kontur.util.ValueResult.ok;

public class RestrictSizeInt {

    public final int size;

    private RestrictSizeInt(int size) {

        this.size = size;
    }

    public static ValueResult<RestrictSizeInt> parse(String sizeStr, Predicate<Integer> restrictionCheck) {
        try {
            int size = parseInt(sizeStr);
            return from(size, restrictionCheck);
        } catch (NumberFormatException e) {
            return fail(e.getMessage());
        }
    }

    public static ValueResult<RestrictSizeInt> from(int candidate, Predicate<Integer> restrictionCheck) {
        if (restrictionCheck.test(candidate)) {
            return ok(new RestrictSizeInt(candidate));
        } else {
            return fail(String.format("Initial size is large or small (%d)", candidate));
        }
    }
}
