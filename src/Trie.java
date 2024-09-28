public class Trie {
    public Node root;
    public Node last;

    public Trie() {
        this.root = new Node(' ', false, null);
        this.last = this.root;
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
            charIndex = getCharIndex(currentChar);
            if((charIndex < 0) || (charIndex > 25)){
                continue;
            }
            if(i == (s.length() - 1)){
                currentWord = true;
            }
            if(pNode.getNext(charIndex) == null){
                newNode = new Node(s.charAt(i), currentWord, null);
                pNode.setNext(newNode, charIndex);
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
        for(int i = 0; i < s.length(); i++){
            charIndex = getCharIndex(s.charAt(i));
            if(charIndex < 0 || charIndex > 25)
            {
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

    public int getCharIndex(char currentChar){
        int charIndex = -1;
        if(Character.isLowerCase(currentChar)){
            charIndex = currentChar - 'a';
        }
        else{
            charIndex = currentChar - 'A';
        }
        return charIndex;
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
        for(int i = 0; i < 26; i++){
            currentNode = pNode.getNext(i);
                if(currentNode != null){
                    System.out.println(currentNode.toString());
                    printMyChildren(currentNode);
                }
            }
    }
}
