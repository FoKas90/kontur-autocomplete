package ru.kontur.dictionary;

import java.util.*;

class TreeNode {

    private final char letter;
    private boolean isEnd;
    private final TreeNode[] children = new TreeNode[26];
    private List<Integer> wordIndexes = new ArrayList<>();

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

    List<Integer> getWordIndexes() {
        return wordIndexes;
    }
}
