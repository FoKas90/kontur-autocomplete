package ru.kontur.util;

public interface Result {
    String getErrorMessage();

    boolean isSuccess();

    static Result fail(String errorMessage) {
        return new ResultImpl(errorMessage);
    }

    static Result ok() {
        return new ResultImpl(true, "");
    }

    boolean isNotSuccess();
}
