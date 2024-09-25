import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [YOUR NAME HERE]
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
        Map<String, Integer> misspelledWords = new HashMap<>();
        for(int i = 0; i < dictionary.length; i++){
            sortedDict.put(dictionary[i], i);
        }
        for(int i = 0; i < text.length; i++){
            if ((!sortedDict.containsKey(text[i])) && (!misspelledWords.containsKey(text[i]))) {
                misspelledWords.put(text[i], i);
            }
        }
        String[] misspellArr = new String[misspelledWords.size()];
        // using for-each loop for
        // iteration over TreeMap.entrySet()
        /*
        I got the below code from here:
        https://www.geeksforgeeks.org/how-to-iterate-over-a-treemap-in-java/
         */
        /*
        I got the below code from:
        https://stackoverflow.com/questions/10596132/how-to-iterate-hashmap-in-reverse-order-in-java
         */
        ArrayList<String> keys = new ArrayList<String>(misspelledWords.keySet());
        int j = 0;
        for(int i=keys.size()-1; i>=0;i--){
            misspellArr[j] = keys.get(i);
            j++;
        }
        return misspellArr;
    }
}
