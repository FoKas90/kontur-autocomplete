package ru.kontur.dictionary;

import ru.kontur.util.Result;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static ru.kontur.util.Result.fail;
import static ru.kontur.util.Result.ok;

class KonturRatingDictionaryImpl implements KonturRatingDictionary {

    private static final int DICTIONARY_MAX_SIZE = 100_000;
    private final TreeNode root = new TreeNode(Character.MIN_VALUE);
    private static final int MAX_BEST = 10;
    private int currentSize = 0;

    KonturRatingDictionaryImpl() {

    }

    Result addWord(KonturDictionaryWord word) {
        if(currentSize == DICTIONARY_MAX_SIZE) return fail("Dictionary is fill");
        TreeNode node = root;
        for (int i = 0; i < word.word.length(); i++) {
            char aLetter = word.word.charAt(i);
            int index = aLetter - 97;
            if (node.getChildren()[index] == null) {
                TreeNode treeNode = new TreeNode(aLetter);
                node.getChildren()[index] = treeNode;
                node = treeNode;
            } else {
                node = node.getChildren()[index];
            }
            node.addRatingWord(word.word, word.rating);
        }
        node.setEnd(true);
        currentSize++;
        return ok();
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
        List<Set<String>> words = current.getRatingWords();
        int bests = 0;
        Iterator<Set<String>> iterator = words.iterator();
        String[] result = new String[MAX_BEST];
        while (bests != MAX_BEST && iterator.hasNext()) {
            Set<String> aLetterWords = iterator.next();
            Iterator<String> aLetterWordIterator = aLetterWords.iterator();
            while (bests != MAX_BEST && aLetterWordIterator.hasNext()) {
                result[bests] = aLetterWordIterator.next();
                bests++;
            }
        }
        return result;
    }
}
