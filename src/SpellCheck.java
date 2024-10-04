import java.util.*;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Agastya Brahmbhatt
 * */

public class SpellCheck {


    /**
     * checkWords finds all words in text that are not present in dictionary
     *
     * @param text       The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {
        /*
        Pseudocode taken from Mr. Blick's slides:

        Create a Trie for the dictionary
For each word in the dictionary,
	insert it into the Trie

Create a Trie for the misspelled words

for each word in text:
	if not in dictionary Trie and
	   not in misspelled Trie
add to misspelled Trie

         */

        /*
        Trie code below:

        Trie dictWords = new Trie();
        Trie misspelledWords = new Trie();
        ArrayList<String> misspelledList = new ArrayList<String>();
        if ((dictionary == null) || dictionary.length <= 0) {
            return text;
        }
        if ((text == null) || text.length <= 0) {
            return null;
        }
        for (int i = 0; i < dictionary.length; i++) {
            dictWords.insert(dictionary[i]);
        }
        for (int i = 0; i < text.length; i++) {
            if (!dictWords.lookup(text[i]) && !(misspelledWords.lookup(text[i]))) {
                misspelledWords.insert(text[i]);
                misspelledList.add(text[i]);
            }
        }
        return misspelledList.toArray(new String[misspelledList.size()]);
    }
         */

        /*
        The below code is a replica of the code above but adjusted so that it can
        test out the SlimTrie (TST) class instead of the trie.
         */
        SlimTrie dictWords = new SlimTrie();
        SlimTrie misspelledWords = new SlimTrie();
        ArrayList<String> misspelledList = new ArrayList<String>();
        if ((dictionary == null) || dictionary.length <= 0) {
            return text;
        }
        if ((text == null) || text.length <= 0) {
            return null;
        }
        for (int i = 0; i < dictionary.length; i++) {
            dictWords.insert(dictionary[i]);
        }
        for (int i = 0; i < text.length; i++) {
            if (!dictWords.lookup(text[i]) && !(misspelledWords.lookup(text[i]))) {
                misspelledWords.insert(text[i]);
                misspelledList.add(text[i]);
            }
        }
        //System.out.println(misspelledList);
        return misspelledList.toArray(new String[misspelledList.size()]);
    }
}
