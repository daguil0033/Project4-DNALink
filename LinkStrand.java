// Daniel Aguilar
// Jack Navin-Weinstein
public class LinkStrand implements IDnaStrand {
    private class Node{
        String info;
        Node next;

        public Node(String s, Node n) {
            info = s;
            next = n;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;

    /**
     * default constructor for LinkStrand
     */
    public LinkStrand() {
        this("");
    }

    /**
     * this is a constructor that takes the parameter s and
     * calls initialize that establishes the class invariant with a single node representing
     * the entire strand of DNA (s)
     */
    public LinkStrand(String s) { initialize(s);
    }

    @Override
    /**
     * this method updates values of the private variables
     * depending on their values within the source parameter
     */
    public void initialize(String source) {
        myFirst = new Node(source,null);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myIndex = 0;
        myLocalIndex = 0;
        myCurrent = myFirst;

    }

    @Override
    /**
     * this method returns mySize private variable whenever size is called
     */
    public long size() {
        return mySize;
    }

    @Override
    /**
     * this method takes the source and returns a new LinkStrand
     * object that represents the instance IDnaStrand
     */
    public IDnaStrand getInstance(String source) { return new LinkStrand(source); }

    @Override
    /**
     * this method does exactly as it is called, appends to
     * the LinkStrand IDnaStrand
     */
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna,null);
        myLast = myLast.next;
        mySize = mySize + dna.length();
        myAppends += 1;
        return this;
    }
    @Override
    /**
     * this method creates a StringBuilder object
     * that turns the a LinkedList into a string
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        Node nod = myFirst;
        while(nod != null){
            out.append(nod.info);
            nod = nod.next;
        }
        return out.toString();
    }
    /**
     * this method is a helper method for reverse method
     * it allows us to append a node to the front of
     * a given LinkStrand
     */
    private void appendToFront(String dna){
        Node n = new Node(dna, myCurrent);
        this.mySize += n.info.length();
        n.next = myFirst;
        myFirst = n;
    }

    @Override
    /**
     * reverses the order of every node in a LinkStrand and the actual strings
     * that are in each node
     */
    public IDnaStrand reverse() {

        LinkStrand rev = new LinkStrand();
        Node nod = myFirst;

        while (nod != null){
            StringBuilder temp = new StringBuilder(nod.info);
            String str = temp.reverse().toString();
            rev.appendToFront(str);
            nod = nod.next;
        }
        return rev;
    }

    @Override
    /**
     * returns the number of appends done to IDnaStrand
     */
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    /**
     * returns char that is found at the given index
     */
    public char charAt(int index) {
        if(index <0 || index >= mySize) throw new IndexOutOfBoundsException();

        if(index < myIndex){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        if(index == myIndex)return myCurrent.info.charAt(myLocalIndex);

        if(index > myIndex){
            while(myIndex != index){
                myIndex++;
                myLocalIndex++;
                if(myLocalIndex >= myCurrent.info.length()){
                    myLocalIndex = 0;
                    myCurrent = myCurrent.next;
                }
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }

}
