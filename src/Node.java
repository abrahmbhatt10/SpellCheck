import java.util.Arrays;

public class Node {
    //Instance Variables
    private boolean isWord;
    private Node[] next;
    private char nodeChar;

    public Node(char nodeChar, boolean isWord, Node[] next) {
        this.isWord = isWord;
        this.next = next;
        this.next = new Node[26];
        this.nodeChar = nodeChar;
    }

    public Node() {
        this.isWord = false;
        this.next = null;
        this.next = new Node[26];
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

    public void setNext(Node next, int index) {
        this.next[index] = next;
    }

    public Node getNext(int index){
        return next[index];
    }

    @Override
    public String toString() {
        return "Node{" +
                "isWord=" + isWord +
                ", nodeChar=" + nodeChar +
                '}';
    }
}
