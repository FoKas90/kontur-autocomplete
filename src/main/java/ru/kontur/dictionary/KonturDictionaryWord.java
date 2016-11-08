package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;

import static ru.kontur.util.Result.fail;
import static ru.kontur.util.Result.ok;

class KonturDictionaryWord {

    final String word;

    final int rating;

    private KonturDictionaryWord(String word, int rating) {
        this.word = word;
        this.rating = rating;
    }

    static Result<KonturDictionaryWord> create(String word, int ratingInt) {
        if (word.isEmpty() || word.length() > 15)
            return fail(String.format("Word is empty or large (%s)", word));
        Result<RestrictSizeInt> sizeIntResult = RatingSize.from(ratingInt);
        if (sizeIntResult.isSuccess()) {
            return ok(new KonturDictionaryWord(word, ratingInt));
        } else {
            return fail(String.format("Word rating is large or small (%d)", ratingInt));
        }
    }

    static Result<KonturDictionaryWord> parse(String s) {
        String[] wordUnits = s.split(" ");
        if (wordUnits.length != 2)
            return fail("Wrong dictionary word representation");
        try {
            return create(wordUnits[0], Integer.parseInt(wordUnits[1]));
        } catch (NumberFormatException e) {
            return fail(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KonturDictionaryWord that = (KonturDictionaryWord) o;

        if (rating != that.rating) return false;
        return word.equals(that.word);

    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + rating;
        return result;
    }
}
