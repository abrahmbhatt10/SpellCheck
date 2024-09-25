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
     * @param text The list of all words in the text.
     * @param dictionary The list of all accepted words.
     * @return String[] of all mispelled words in the order they appear in text. No duplicates.
     */
    public String[] checkWords(String[] text, String[] dictionary) {
        if(text == null){
            return null;
        }
        if(dictionary == null){
            return text;
        }
        /*
        For the treemap and hashmap implementation, I looked
        at this code:
        https://stackoverflow.com/questions/2000237/in-java-which-is-the-most-recommended-class-for-a-dictionary-data-structure
         */
        Map<String, Integer> sortedDict = new TreeMap<>();
        ArrayList<String> misspelledWords = new ArrayList<String>();
        for(int i = 0; i < dictionary.length; i++){
            sortedDict.put(dictionary[i], i);
        }
        for(int i = 0; i < text.length; i++){
            if ((!sortedDict.containsKey(text[i])) && (!misspelledWords.contains(text[i]))) {
                misspelledWords.add(text[i]);
            }
        }
        String[] misspellArr = new String[misspelledWords.size()];
        /*
        I got the below code from:
        https://www.baeldung.com/java-iterate-set
         */
        int j = 0;
        for(String a : misspelledWords){
            misspellArr[j] = misspelledWords.get(j);
            j++;
        }
        return misspellArr;
    }
}
