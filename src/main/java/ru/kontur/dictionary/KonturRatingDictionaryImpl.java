package ru.kontur.dictionary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

class KonturRatingDictionaryImpl implements KonturRatingDictionary {

    private static final int DICTIONARY_MAX_SIZE = 100_000;
    private final TreeNode root = new TreeNode(Character.MIN_VALUE);
    private final List<KonturDictionaryWord> allWords = new ArrayList<>(DICTIONARY_MAX_SIZE + (int) (DICTIONARY_MAX_SIZE * 0.16));
    private final int maxBest = 10;
    private int currentIndex = 0;
    private Comparator<KonturDictionaryWord> ratingComparator = (w1, w2) ->
            Integer.compare(w1.rating, w2.rating);
    private Comparator<KonturDictionaryWord> alphaComparator = (w1, w2) ->
            w1.word.compareTo(w2.word);
    private Comparator<KonturDictionaryWord> konturComparator = ratingComparator.reversed().thenComparing(alphaComparator);

    KonturRatingDictionaryImpl() {

    }

    @Override
    public void addWord(KonturDictionaryWord word) {
        allWords.add(currentIndex, word);
        TreeNode node = root;
        for (int i = 0; i < word.word.length(); i++) {
            char aLetter = word.word.charAt(i);
            int index = aLetter - 97;
            if (node.getChildren()[index] == null) {
                TreeNode treeNode = new TreeNode(aLetter);
                node.getChildren()[index] = treeNode;
                treeNode.getWordIndexes().add(currentIndex);
                node = treeNode;
            } else {
                node = node.getChildren()[index];
                List<KonturDictionaryWord> currentPopular = node.getWordIndexes().stream().map(allWords::get).collect(toList());
                currentPopular.add(word);
                List<Integer> newIndexes = currentPopular.stream().sorted(konturComparator).
                        limit(maxBest).
                        map(allWords::indexOf).
                        collect(toList());
                node.getWordIndexes().clear();
                node.getWordIndexes().addAll(newIndexes);
            }
        }
        currentIndex++;
        node.setEnd(true);
    }

    @Override
    public String[] search(String what) {
        if (what == null || what.isEmpty()) return new String[0];
        char[] chars = what.toCharArray();
        TreeNode current = root;
        for (char letter : chars) {
            Optional<TreeNode> letterNode = current.getNode(letter);
            if (!letterNode.isPresent()) return new String[0];
            current = letterNode.get();
        }
        if (current == root) return new String[0];
        return current.getWordIndexes().stream().map(in -> allWords.get(in).word).toArray(String[]::new);
    }
}
