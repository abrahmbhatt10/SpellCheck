import java.text.Normalizer;

public class Trie {
    public Node root;
    public Node last;

    // Default Constructor
    public Trie() {
        this.root = new Node(' ', false);
        this.last = this.root;
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
        Node pNode = root;
        Node newNode = null;
        Node pNode1;
        boolean currentWord = false;
        int charIndex = 0;
        int currentChar;
        for(int i = 0; (i < s.length()) && (pNode != null); i++){
            currentChar = s.codePointAt(i);
            if(i == s.length() - 1){
                currentWord = true;
            }
            else{
                currentWord = false;
            }
            charIndex = pNode.getCharIndex(currentChar, true, currentWord);
            if(pNode.getNext(charIndex) == null){
                newNode = new Node(s.codePointAt(i), currentWord);
                pNode1 = newNode;
                if(charIndex < 26){
                    pNode.setNext(charIndex, newNode);
                }
                else{
                    pNode.setNext(-1, newNode);
                }
            }
            else {
                if(i== (s.length()-1) && (!pNode.getNext(charIndex).isWord())){
                    pNode.getNext(charIndex).setWord(true);
                }
                pNode1 = pNode.getNext(charIndex);
            }
            pNode = pNode1;
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
        Node pNode = root;
        Node currentNode = null;
        int charIndex;
        boolean currentWord;
        for(int i = 0; (i < s.length()) && (pNode != null); i++){
            if(i == s.length() - 1){
                currentWord = true;
            }
            else{
                currentWord = false;
            }
            charIndex = pNode.getCharIndex(s.codePointAt(i), false, currentWord);
            if(charIndex < 0){
                return false;
            }
            currentNode = pNode.getNext(charIndex);
            if(currentNode == null){
                return false;
            }
            if((i == s.length() - 1) && (!currentNode.isWord())){
                return false;
            }
            pNode = currentNode;
        }
        return true;
    }

    // Helps for debugging by printing out
    // Each Node data
    public void printTrie(){
        Node pNode = root;
        Node currentNode = null;
        while(pNode != null){
            System.out.println(pNode.toString());
            printMyChildren(pNode);
        }
    }

    // Prints the nodes branches
    public void printMyChildren(Node pNode){
        Node currentNode = null;
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
