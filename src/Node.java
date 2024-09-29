import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    //Instance Variables
    private boolean isWord;
    private char nodeChar;
    private ArrayList<Node> next;

    public Node(char nodeChar, boolean isWord) {
        this.isWord = isWord;
        this.next = new ArrayList<Node>();
        for(int i = 0; i < 26; i++){
            next.add(new Node());
        }
        this.nodeChar = nodeChar;
    }

    public Node() {
        this.isWord = false;
        this.next = new ArrayList<Node>();
        for(int i = 0; i < 26; i++){
            next.add(new Node());
        }
        this.nodeChar = ' ';
    }

    public char getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public boolean isWord(){
        return isWord;
    }
    public void setWord(boolean endWord){
        isWord = endWord;
    }

    public Node getNext(int index) {
        return next.get(index);
    }

    public void setNext(int index, Node newNode) {
        next.set(index, newNode);
    }

    public int getCharIndex(char currentChar){
        int charIndex = -1;
        if(Character.isLowerCase(currentChar)){
            charIndex = currentChar - 'a';
            return charIndex;
        }
        if(Character.isUpperCase(currentChar)){
            charIndex = currentChar - 'A';
            return charIndex;
        }
        // This is for special characters
        Node pNode;
        for(int i = 26; i < next.size(); i++){
            pNode = next.get(i);
            if(pNode == null){
                next.set(i, new Node(currentChar,false));
                return i;
            }
            if(pNode.getNodeChar() == currentChar){
                return i;
            }
        }
        return charIndex;
    }

    @Override
    public String toString() {
        return "Node{" +
                "isWord=" + isWord +
                ", nodeChar=" + nodeChar +
                '}';
    }
}
