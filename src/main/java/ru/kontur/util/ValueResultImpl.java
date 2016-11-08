package ru.kontur.util;

class ValueResultImpl<T> implements ValueResult<T> {
    private Result result;
    private T value;

    public ValueResultImpl(Result result, T value) {
        this.result = result;
        this.value = value;
    }

    @Override
    public String getErrorMessage() {
        return result.getErrorMessage();
    }

    @Override
    public boolean isSuccess() {
        return result.isSuccess();
    }

    @Override
    public boolean isNotSuccess() {
        return result.isNotSuccess();
    }

    @Override
    public T getValue() {
        return value;
    }
}
