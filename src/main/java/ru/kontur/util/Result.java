package ru.kontur.util;

public class Result<T> {
    private final boolean isSuccess;
    private final String errorMessage;
    private final T value;

    private Result(String errorMessage) {
        this(null, false, errorMessage);
    }

    private Result(T value) {
        this(value, true, "");
    }

    private Result(T value, boolean success, String errorMessage) {
        this.value = value;
        this.isSuccess = success;
        this.errorMessage = errorMessage;
    }

    public boolean isNotSuccess() {
        return !isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public T getValue() {
        return value;
    }

    public static <T> Result<T> fail(String errorMessage) {
        return new Result<>(errorMessage);
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(value);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
