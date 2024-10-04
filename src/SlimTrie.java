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
        if(pNode.getNodeChar() == ' '){
            // This is the root node
            pNode.setNodeChar(s.charAt(0));
        }
        for(int i = 1; (i < s.length()) && (pNode != null); i++){
            if(i == s.length() - 1){
                currentWord = true;
            }
            else{
                currentWord = false;
            }
            pNode1 = pNode.getCharNextNode(s.charAt(i), true, currentWord, pNode);
            pNode = pNode1;
        }
        if(pNode != null){
            pNode.setWord(true);
        }
    }
    /*
        node = node.next[word.charAt(i)];

        insert((node)root, (word)"cab", (index)0);
        (if statement) check for null nodes then create new nodes there
        create node before recursing
        Second insert function
        If it goes left or right on the tree, don't increase index
        However, if it goes down the tree, then increase it by 1.
     */
    public void insert(SlimNode node, String currentWord, int index){

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
            pNode1 = pNode.getCharNextNode(s.charAt(i), false, currentWord, pNode);
            if((pNode1 == null) && (currentWord == false)){
                return false;
            }
            if((currentWord) && (pNode1 != null) && (!pNode1.isWord())){
                return false;
            }
            pNode = pNode1;
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
