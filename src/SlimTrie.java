import java.text.Normalizer;

public class SlimTrie {
    public SlimNode root;

    // Default Constructor
    public SlimTrie() {
        this.root = new SlimNode(' ', false);
    }

    /*
        This function inserts words from the dictionary
        to the dictionary trie and the misspelled words to the misspelled trie.
        This function is based on the pseudocode from the class.
     */
    public void insert(String s){
        if((s == null) || (s.length() <= 0)){
            return;
        }
        /*
        I got the below code from:
        https://stackoverflow.com/questions/69633201/unicode-normalization-forms-explanation-java
         */
        String s2 = Normalizer.normalize(s, Normalizer.Form.NFC);
        s = s2;
        SlimNode pNode = root;
        SlimNode newNode = null;
        SlimNode pNode1 = null;
        boolean currentWord = false;
        int charIndex = 0;
        int currentChar;
        for(int i = 0; (i < s.length()) && (pNode != null); i++){
            if(i == s.length() - 1){
                currentWord = true;
            }
            else{
                currentWord = false;
            }
            if(pNode.getNodeChar() == ' '){
                // This is the root node
                pNode.setNodeChar(s.charAt(i));
                pNode.setWord(currentWord);
                continue;
            }
            while((pNode1 == null) && (pNode != null)){
                if(pNode.getNodeChar() == s.charAt(i)){
                    pNode1 = pNode.getNext(1);
                }
                else if(pNode.getNodeChar() < s.charAt(i)){
                    pNode1 = pNode.getNext(2);
                }
                else{
                    pNode1 = pNode.getNext(0);
                }
                if(pNode1 == null){
                    pNode1 = new SlimNode(s.charAt(i), currentWord);
                }
                else{
                    pNode = pNode1;
                    pNode1 = null;
                }
            }
            if((pNode == null) && (pNode1 == null)){
                pNode = new SlimNode(s.charAt(i), currentWord);
                System.out.println(currentWord);
            }
        }
    }

    /*
        This function looks up words from the trie
        and returns true if the word exists and false if not.
     */
    public boolean lookup(String s){
        /*
        I got the below code from:
        https://stackoverflow.com/questions/69633201/unicode-normalization-forms-explanation-java
         */
        String s2 = Normalizer.normalize(s, Normalizer.Form.NFC);
        s = s2;
        SlimNode pNode = root;
        SlimNode pNode1 = null;
        SlimNode currentNode = null;
        int charIndex;
        boolean currentWord;
        for(int i = 0; (i < s.length()) && (pNode != null); i++){
            if(i == s.length() - 1){
                currentWord = true;
            }
            else{
                currentWord = false;
            }
            while(pNode1 == null){
                if(pNode.getNodeChar() == s.charAt(i)){
                    pNode1 = pNode.getNext(1);
                }
                else if(pNode.getNodeChar() < s.charAt(i)){
                    pNode1 = pNode.getNext(2);
                }
                else{
                    pNode1 = pNode.getNext(0);
                }
                if(pNode1 != null){
                    pNode = pNode1;
                    pNode1 = null;
                }
            }
            if((pNode == null) && (pNode1 == null)){
                pNode = new SlimNode(s.charAt(i), currentWord);
            }
        }
        return true;
    }

    // Helps for debugging by printing out
    // Each Node data
    public void printTrie(){
        SlimNode pNode = root;
        SlimNode currentNode = null;
        while(pNode != null){
            System.out.println(pNode.toString());
            printMyChildren(pNode);
        }
    }

    // Prints the nodes branches
    public void printMyChildren(SlimNode pNode){
        SlimNode currentNode = null;
        if(pNode == null){
            return;
        }
        for(int i = 0; pNode != null; i++){
            currentNode = pNode.getNext(i);
            if(currentNode != null){
                System.out.println(currentNode.toString());
                printMyChildren(currentNode);
            }
        }
    }
}
