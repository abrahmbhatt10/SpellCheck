import java.util.ArrayList;
import java.util.Arrays;
import java.text.Normalizer;

public class Node {
    //Instance Variables
    private boolean isWord;
    private int nodeChar;
    private ArrayList<Node> next;

    public Node(int nodeChar, boolean isWord) {
        this.isWord = isWord;
        this.next = new ArrayList<Node>();
        this.nodeChar = nodeChar;
    }

    public Node() {
        this.isWord = false;
        this.next = new ArrayList<Node>();
        this.nodeChar = (int)' ';
    }

    public int getNodeChar() {
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
        if(index >= next.size()){
            return null;
        }
        return next.get(index);
    }

    public void setNext(int index, Node newNode) {
        if(index < 0){
            next.add(newNode);
        }
        else{
            next.set(index, newNode);
        }
    }

    public int getCharIndex(int currentChar, boolean insertFlag, boolean isWord){
        int charIndex = -1;
        if(next.size() < 26){
            for(int j = 0; j < 26; j++){
                next.add(new Node((char)('a' + j), false));
            }
        }
        if((currentChar >= 97) && (currentChar <= 122)){
            charIndex = currentChar - 'a';
            return charIndex;
        }
        if((currentChar >= 65) && (currentChar <= 90)){
            charIndex = currentChar - 'A';
            return charIndex;
        }
        // This is for special characters
        Node pNode;
        int i;
        for(i = 26; i < next.size(); i++){
            pNode = next.get(i);
            if(pNode.getNodeChar() == currentChar){
                return i;
            }
        }
        if(insertFlag){
            next.add(i, new Node(currentChar, isWord));
            return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "isWord=" + isWord +
                ", nodeChar=" + nodeChar +
                '}';
    }
}
