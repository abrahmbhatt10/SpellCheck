public class SlimNode {
    //Instance Variables
    private boolean isWord;
    private int nodeChar;
    private SlimNode[] next;

    // Constructor
    public SlimNode(int nodeChar, boolean isWord) {
        this.isWord = isWord;
        this.next = new SlimNode[3];
        for(int i = 0; i < next.length; i++){
            next[i] = null;
        }
        this.nodeChar = nodeChar;
    }

    // Default constructor
    public SlimNode() {
        this.isWord = false;
        this.next = new SlimNode[3];
        for(int i = 0; i < next.length; i++){
            next[i] = null;
        }
        this.nodeChar = (int)' ';
    }

    // Getters and setters
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

    public SlimNode getNext(int index) {
        if(index >= next.length){
            return null;
        }
        return next[index];
    }

    public void setNext(int index, SlimNode newNode) {
        if((index < 0) || (index > (next.length - 1))){
            next[1] = newNode;
        }
        else{
            next[index] = newNode;
        }
    }

    /*
        This functions gets the char index
        or the position of the char in the array
     */
    public int getCharIndex(int currentChar, boolean insertFlag, boolean isWord){
        int charIndex = -1;
        if((next[0] == null) && (next[1] == null) && (next[2] == null)){
            return 1;
        }
        if((next[1] == null)){
            if(insertFlag){
                next[1] = new SlimNode(currentChar, isWord);
            }
            return 1;
        }
        if(next[1].getNodeChar() == currentChar){
            return 1;
        }
        if(next[1].getNodeChar() > currentChar){
            if((next[0] == null) && (insertFlag)){
                next[0] = new SlimNode(currentChar, isWord);
            }
            return 0;
        }
        if((next[2] == null) && (insertFlag)){
            next[2] = new SlimNode(currentChar, isWord);
        }
        return 2;
    }

    public SlimNode getCharNextNode(int currentChar, boolean insertFlag, boolean isWord, SlimNode lastNode){
        SlimNode pNode1 = null;
        SlimNode pNode = lastNode;
        if(pNode == null){
            if(!insertFlag){
                return null;
            }
            pNode = new SlimNode(currentChar, isWord);
            return pNode;
        }
        while(pNode != null){
            pNode1 = pNode.getNext(1);
            if(pNode1 != null) {
                if(pNode1.getNodeChar() == currentChar){
                    return pNode1;
                }
                else if (pNode1.getNodeChar() < currentChar) {
                    pNode1 = pNode.getNext(2);
                } else {
                    pNode1 = pNode.getNext(0);
                }
            }
            pNode = pNode1;
            pNode1 = null;
        }
        if((pNode == null) && (insertFlag)){
            pNode = new SlimNode(currentChar, isWord);
        }
        return pNode;
    }

    // ToString for debugging.
    @Override
    public String toString() {
        return "Node{" +
                "isWord=" + isWord +
                ", nodeChar=" + nodeChar +
                '}';
    }
}
