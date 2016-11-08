package ru.kontur;

import ru.kontur.autocomplete.KonturAutocomplete;
import ru.kontur.autocomplete.RequestCountSize;
import ru.kontur.util.RestrictSizeInt;
import ru.kontur.util.Result;

import java.io.*;

public class AutocompleteApp {

    public static void main(String[] args) throws IOException {
        System.setIn(new BufferedInputStream(new FileInputStream("D:\\Кам\\Тестовое задание Java для опытных разработчиков\\test.in")));
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("D:\\Кам\\Тестовое задание Java для опытных разработчиков\\out.txt"))));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String read;
        Result<KonturAutocomplete> autocompleteResult = KonturAutocomplete.from(in);
        if (autocompleteResult.isNotSuccess()) {
            exitWithError(autocompleteResult.getErrorMessage(), out);
        }
        KonturAutocomplete autocomplete = autocompleteResult.getValue();
        Result<RestrictSizeInt> requestCountSizeResult = null;
        try {
            requestCountSizeResult = RequestCountSize.parse(in.readLine());
        } catch (IOException e) {
            exitWithError(e.getMessage(), out);
        }
        if (requestCountSizeResult.isNotSuccess()) {
            exitWithError("Requests count size has incorrect value", out);
        }
        int size = requestCountSizeResult.getValue().size;
        for (int i = 0; i < size; i++) {
            read = in.readLine();
            Result<String[]> bestResult = autocomplete.getBests(read);
            if (bestResult.isSuccess()) {
                String[] found = bestResult.getValue();
                print(found, out);
            } else {
                exitWithError(bestResult.getErrorMessage(), out);
            }
        }
        out.flush();
    }

    private static void print(String[] lines, BufferedWriter writer) {
        try {
            for (String s : lines) {
                writer.write(s);
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            exitWithError(e.getMessage(), writer);
        }
    }

    private static void exitWithError(String errorMessage, BufferedWriter writer) {
        try {
            writer.write(errorMessage);
            writer.write("Нажмите любую клавишу для выхода...");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
