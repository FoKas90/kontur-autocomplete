package ru.kontur.util;

public interface ValueResult<T> extends Result {

    T getValue();

    static <T> ValueResult<T> ok(T value) {
        return new ValueResultImpl<>( Result.ok(),value);
    }

    static <T> ValueResult<T> fail(String errorMessage) {
        return new ValueResultImpl<>(Result.fail(errorMessage), null);
    }
}
