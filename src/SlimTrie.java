import java.text.Normalizer;

public class SlimTrie {
    public SlimNode root;

    // Default Constructor
    public SlimTrie() {
        this.root = null;
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
        boolean lastLetter = false;
        SlimNode pNode = root;
        SlimNode parentNode = root;
        int currentIndex = 1;
        boolean matchExists = false;

        for(int i=0; i< s.length(); i++)
        {
            matchExists = false;
            if( i == (s.length()-1)){
                lastLetter= true;
            } else {
                lastLetter = false;
            }
            if(root == null)
            {
                root = new SlimNode(s.charAt(i),lastLetter, parentNode,1);
                parentNode = root;
                pNode = root.getNext(1);
                if(i+1 < s.length()) {
                    insertRemaining(s, i+1, pNode, parentNode);
                }
                return;
            }
            //find the location to insert
            while(pNode != null)
            {
                parentNode = pNode;
                if(pNode.getNodeChar() == s.charAt(i))
                {
                        currentIndex = 1;
                        matchExists = true;
                        break;
                }
                if(pNode.getNodeChar() < s.charAt(i))
                {
                    pNode = pNode.getNext(2);
                    currentIndex = 2;
                }
                else if(pNode.getNodeChar() > s.charAt(i))
                {
                    pNode = pNode.getNext(0);
                    currentIndex = 0;
                }
            }
            if(matchExists) {
                //for a substring that already exists, mark end of the word
                if(i == (s.length()-1) && (!pNode.isLastLetter())){
                    pNode.setLastLetter(lastLetter);
                }
                pNode = pNode.getNext(1);
                continue;
            }
            //This is the null location to insert
            pNode = new SlimNode(s.charAt(i), lastLetter, parentNode,currentIndex);
            if(i+1 < s.length()) {
                insertRemaining(s, i + 1, pNode.getNext(1), pNode);
            }
            return;
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
        SlimNode previousNode = root;
        int currentIndex = 1;
        for(int i=0; i< s.length(); i++)
        {
            pNode = getCurrentNode(s.charAt(i), previousNode);
            if(pNode == null)
            {
                return false;
            }
            //pNode already matched with this character, go to the next
            if((i== (s.length()-1) && (!pNode.isLastLetter())))
            {
                return false;
            }
            previousNode = pNode.getNext(1);
        }
        return true;
     }

     public void insertRemaining(String s, int i, SlimNode pNode, SlimNode parentNode)
     {
         boolean lastLetter = false;
         for( ; i < s.length(); i++) {
             if(i == (s.length()-1))
             {
                 lastLetter = true;
             } else {
                 lastLetter = false;
             }
             pNode = new SlimNode(s.charAt(i),lastLetter, parentNode, 1);
             parentNode = pNode;
             pNode = pNode.getNext(1);
         }
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

    public SlimNode getCurrentNode(int currentChar, SlimNode rootNode)
    {
        SlimNode pNode = rootNode;
        while(pNode != null)
        {
            if(pNode.getNodeChar() == currentChar)
            {
                return pNode;
            }
            if(pNode.getNodeChar() < currentChar)
            {
                pNode = pNode.getNext(2);
            }
            else if(pNode.getNodeChar() > currentChar)
            {
                pNode = pNode.getNext(0);
            }
        }
        return pNode;
    }
}
