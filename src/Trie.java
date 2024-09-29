public class Trie {
    public Node root;
    public Node last;
    private char[] punctuation;

    public Trie() {
        this.root = new Node(' ', false);
        this.last = this.root;
        this.punctuation = new char[]{'\'', ',', '.', '!', '?', ';'};
    }

    public void insert(String s){
        if((s == null) || (s.length() <= 0)){
            return;
        }
        Node pNode = root;
        Node newNode = null;
        boolean currentWord = false;
        int charIndex = 0;
        Character currentChar;
        for(int i = 0; i < s.length(); i++){
            currentChar = s.charAt(i);
            charIndex = pNode.getCharIndex(currentChar);
            if(i == (s.length() - 1)){
                currentWord = true;
            }
            if(pNode.getNext(charIndex) == null){
                newNode = new Node(s.charAt(i), currentWord);
                pNode.setNext(charIndex, newNode);
            }
            else {
                if(i== (s.length()-1) && (!pNode.getNext(charIndex).isWord())){
                    pNode.getNext(charIndex).setWord(true);
                }
            }
            pNode = pNode.getNext(charIndex);
        }
    }
    public boolean lookup(String s){
        Node pNode = root;
        Node currentNode = null;
        int charIndex;
        for(int i = 0; (i < s.length()) && (pNode != null); i++){
            charIndex = pNode.getCharIndex(s.charAt(i));
            currentNode = pNode.getNext(charIndex);
            if(currentNode == null){
                if(!pNode.isWord()){
                    return false;
                }
                else{
                    return true;
                }
            }
            if((i == s.length() - 1) && (!currentNode.isWord())){
                return false;
            }
            pNode = currentNode;
            if((pNode == null) && (i != s.length() - 1)){
                return false;
            }
        }
        return true;
    }

    public void printTrie(){
        Node pNode = root;
        Node currentNode = null;
        while(pNode != null){
            System.out.println(pNode.toString());
            printMyChildren(pNode);
        }
    }

    public void printMyChildren(Node pNode){
        Node currentNode = null;
        if(pNode == null){
            return;
        }
        for(int i = 0; i < 26 + punctuation.length; i++){
            currentNode = pNode.getNext(i);
                if(currentNode != null){
                    System.out.println(currentNode.toString());
                    printMyChildren(currentNode);
                }
            }
    }
}
