package ru.kontur.dictionary;

import java.util.*;
import java.util.stream.Collectors;

class TreeNode {

    private final char letter;
    private boolean isEnd;
    private final TreeNode[] children = new TreeNode[26];
    private Map<Integer, Set<String>> nodeWords = new HashMap<>();

    TreeNode(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isEnd() {
        return isEnd;
    }

    void setEnd(boolean end) {
        isEnd = end;
    }

    TreeNode[] getChildren() {
        return children;
    }

    Optional<TreeNode> getNode(char letter) {
        return Optional.ofNullable(children[(int) letter - 97]);
    }

    public void addRatingWord(String word, int rating) {
        if (nodeWords.containsKey(rating)) {
            nodeWords.get(rating).add(word);
        } else {
            Set<String> words = new TreeSet<>();
            words.add(word);
            nodeWords.put(rating, words);
        }
    }

    public List<Set<String>> getRatingWords() {
        List<Integer> collect = nodeWords.keySet().stream().collect(Collectors.toList());
        return collect.stream().sorted(Comparator.reverseOrder()).parallel().
                map(nodeWords::get).collect(Collectors.toList());
    }
}
