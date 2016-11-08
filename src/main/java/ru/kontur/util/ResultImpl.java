package ru.kontur.util;

class ResultImpl implements Result {

    private final boolean isSuccess;

    private final String errorMessage;

    ResultImpl(String errorMessage) {
        this(false, errorMessage);
    }

    ResultImpl(boolean success, String errorMessage) {
        this.isSuccess = success;
        this.errorMessage = errorMessage;
    }

    public boolean isNotSuccess() {
        return !isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
