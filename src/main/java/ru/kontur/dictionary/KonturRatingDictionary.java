package ru.kontur.dictionary;

import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;
import ru.kontur.util.ValueResult;

import java.io.BufferedReader;
import java.io.IOException;

import static ru.kontur.util.ValueResult.fail;
import static ru.kontur.util.ValueResult.ok;

/**
 * Интерфейс словаря
 */
public interface KonturRatingDictionary {

    /**
     * Созддает словарь {@link KonturRatingDictionary}, обернутый в {@link ValueResult}
     * @param reader Символьный поток словаря
     * @return Результат ввполнения операции. Успешность операции нужно проверять через {@link ValueResult#isSuccess()}
     * Если словарь создан успешно, то его можно получить через {@link ValueResult#getValue()}
     */
    static ValueResult<KonturRatingDictionary> from(BufferedReader reader) {
        try {
            String line = reader.readLine();
            ValueResult<RestrictSizeInt> sizeResult = DictionarySize.parse(line);
            if (sizeResult.isSuccess()) {
                KonturRatingDictionaryImpl dictionary = new KonturRatingDictionaryImpl();
                int size = sizeResult.getValue().size;
                for (int i = 0; i < size; i++) {
                    line = reader.readLine();
                    ValueResult<KonturDictionaryWord> wordResult = KonturDictionaryWord.parse(line);
                    if (wordResult.isSuccess()) {
                        Result addWordResult = dictionary.addWord(wordResult.getValue());
                        if (addWordResult.isNotSuccess()) {
                            return fail(addWordResult.getErrorMessage());
                        }
                    } else {
                        return fail(wordResult.getErrorMessage());
                    }
                }
                return ok(dictionary);
            } else {
                return fail(sizeResult.getErrorMessage());
            }
        } catch (IOException e) {
            return fail(e.getMessage());
        }
    }

    /**
     * Ищет 10 популярных слов, начинающихся с {@code prefix}
     * @param prefix Начальная часть для искомых слов
     * @return 10 популярных слов. ПУстой массив возвращается, если {@code prefix} равна null и пуста,
     * в словаре ничего не найдено, словарь пуст
     */
    String[] search(String prefix);
}
