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
        Create a Trie for the dictionary
For each word in the dictionary,
	insert it into the Trie

Create a Trie for the misspelled words

for each word in text:
	if not in dictionary Trie and
	   not in misspelled Trie
add to misspelled Trie

         */
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
        for(int i = 0; i < misspelledList.size(); i++){
            System.out.println(misspelledList.get(i));
        }
        return misspelledList.toArray(new String[misspelledList.size()]);
    }
}
