package ru.kontur.autocomplete;

import ru.kontur.dictionary.KonturRatingDictionary;
import ru.kontur.util.ValueResult;

import java.io.BufferedReader;

import static ru.kontur.util.ValueResult.*;

/**
 * Описывает поведение автозаполнения на основании популярности слова
 */
public interface KonturAutocomplete {

    /**
     * Создант экземпляр {@link KonturAutocomplete} из символьного потока словаря,
     * обернутого в {@link ValueResult}.
     * @param reader Символьный поток словаря
     * @return Результат выполнения операции. Успешность операции нужно проверять через {@link ValueResult#isSuccess()}
     * Если создание завершилось успешно, то результат может быть получен через {@link ValueResult#getValue()}
     */
    static ValueResult<KonturAutocomplete> from(BufferedReader reader) {
        ValueResult<KonturRatingDictionary> dictionaryResult = KonturRatingDictionary.from(reader);
        if (dictionaryResult.isNotSuccess()) {
            return fail(dictionaryResult.getErrorMessage());
        }
        return ok(new KonturAutocompleteImpl(dictionaryResult.getValue()));
    }

    /**
     * Возвращает 10 самым популярных слов, начинающихся на {@code start}
     * @param prefix Начальная часть для искомых слов
     * @return Результат ввполнения операции. Успешность операции нужно проверять через {@link ValueResult#isSuccess()}
     * Если поиск завершился успешно, то найденные слова могут быть получены через {@link ValueResult#getValue()}
     */
    ValueResult<String[]> getBests(String prefix);
}
