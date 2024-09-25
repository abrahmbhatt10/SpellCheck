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
        Map<String, Integer> misspelledWordsMap = new TreeMap<>();
        ArrayList<String> misspelledWords = new ArrayList<String>();
        /*
            Sorts the below dictionary in alphabetical order in a map,
            so when I check if its misspelled, it runs in constant time
            (rather than in linear time, as that's what it takes to
            traverse through an array). So converting the dictionary into
            a map is beneficial because it can check if the word is misspelled
            in constant time.
         */
        for(int i = 0; i < dictionary.length; i++){
            sortedDict.put(dictionary[i], i);
        }
        /*
            The below function checks if its misspelled.
            If it is, and if it isn't already in the misspelled words map,
            (another fast way to check if it is already added to the misspelledwords map
            because it checks in constant, not linear time).
            then I add it to the misspelled words arraylist.
         */
        for(int i = 0; i < text.length; i++){
            if ((!sortedDict.containsKey(text[i])) && (!misspelledWordsMap.containsKey(text[i]))) {
                misspelledWordsMap.put(text[i],i);
                misspelledWords.add(text[i]);
            }
        }
        String[] misspellArr = new String[misspelledWords.size()];
        /*
        Moves everything from the arraylist into the misSpellArr array
        and returns it.
         */
        int j = 0;
        for(String a : misspelledWords){
            misspellArr[j] = misspelledWords.get(j);
            j++;
        }
        return misspellArr;
    }
}
