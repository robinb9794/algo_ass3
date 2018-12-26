package redblack;

import workers.ByteManager;
import interfaces.Tree;

public class RedBlackTree implements Tree{
    private ByteManager byteManager;

    private RedBlackNode root;

    public RedBlackTree(){
        this.byteManager = new ByteManager();
    }
    
    @Override
	public Node getRoot() {
		return (RedBlackNode) this.root;
    }

    @Override
    public boolean search(String key){
        RedBlackNode tmp = root;
        int i = 0;
        while(tmp != null){
            if(tmp.getKey().equals(key))
                return true;
            byte[] tmpBytes = byteManager.getKeyBytes(tmp.getKey());
            int byteIndex = byteManager.getKeyIndex(tmpBytes);
            int bit = byteManager.getShiftedBit(tmpBytes, byteIndex, i);
            boolean left = bit == 0;
            tmp = left ? tmp.getLeft() : tmp.getRight();
            i++;
        }
        return false;
    }
    
    @Override
	public void insert(String key) {
        NodeHandler h = new NodeHandler(root);
        byte[] keyBytes = byteManager.getKeyBytes(key);
        RedBlackNode node = new RedBlackNode(key, byteManager.getBinaryCode(keyBytes));
        System.out.println(".............................\nInserting _" + key + "_ with binary code: " + node.getData());
        boolean left = false;
        int i = -1;
        int keyIndex = byteManager.getKeyIndex(keyBytes);
		while(!h.isNull()) {
            i++;
            System.out.println("Now we're at node _" + h.nodes[h.NODE].getKey() + "_");
			if(h.nodes[h.NODE].is4Node()) {
				h.nodes[h.NODE].convert4Node();
				h.split(i);
            }
            keyIndex = byteManager.getKeyIndex(keyBytes, i);
            int bit = byteManager.getShiftedBit(keyBytes, keyIndex, i);
            System.out.println("Checking key bit at position " + i + ": " + bit);
            left = bit == 0;
            System.out.println("Going down " + (left ? "left" : "right"));
            h.down(left);
        }
        
		h.set(node, h.NODE, left, false);
        h.split(i);
        root.setIsRed(false);
    }	

    @Override
    public boolean delete(String key){
        System.out.println(".............................\nDeleting _" + key + "_");
        NodeHandler h = new NodeHandler(root);
        byte[] keyBytes = byteManager.getKeyBytes(key);
        boolean left = false;
        int i = -1;
        int keyIndex = byteManager.getKeyIndex(keyBytes);
        System.out.println("Searching for next bigger key");
        while(!h.isNull()){
            i++;
            h.join();
            keyIndex = byteManager.getKeyIndex(keyBytes, i);
            int bit = byteManager.getShiftedBit(keyBytes, keyIndex, i);
            left = bit == 0;
            if(h.nodes[h.NODE].getKey().equals(key)){
                if(h.nodes[h.NODE].getRight() == null){
                    h.set(h.nodes[h.NODE].getLeft(), h.NODE, false, true);
                }else{
                    NodeHandler h2 = new NodeHandler(h);
                    h2.down(false);
                    h2.join();
                    while(h2.nodes[h2.NODE].getLeft() != null){
                        h2.down(true);
                        h2.join();
                    }
                    h.nodes[h.NODE].setKey(h2.nodes[h2.NODE].getKey());
                    h2.set(h2.nodes[h.NODE].getRight(), h2.NODE, true, true);
                }
                if(root != null)
                    root.setIsRed(false);
                return true;
            }
            h.down(left);
        }
        return false;
    }
    
    class NodeHandler {	
        public final int NODE = 0;
        public final int DAD = 1;
        public final int GRAND_DAD = 2;
        public final int GRAND_GRAND_DAD = 3;

        public RedBlackNode[] nodes = new RedBlackNode[4];
        
        public NodeHandler(RedBlackNode node) {
            nodes[NODE] = node;
        }

        public NodeHandler(NodeHandler h){
            nodes[NODE] = h.nodes[NODE];
            nodes[DAD] = h.nodes[DAD];
            nodes[GRAND_DAD] = h.nodes[GRAND_DAD];
            nodes[GRAND_GRAND_DAD] = h.nodes[GRAND_GRAND_DAD];
        }
        
        public void down(boolean left) {
            for(int i = nodes.length - 1; i > 0; i--)
                nodes[i] = nodes[i-1];
            nodes[NODE] = left ? nodes[DAD].getLeft() : nodes[DAD].getRight();
        }
        
        public boolean isNull() {
            return nodes[NODE] == null;
        }   

        private void join(){
            if(nodes[NODE].is2Node()){
                if(nodes[DAD] == null && nodes[NODE].getLeft() != null && nodes[NODE].getLeft().is2Node()
                    && nodes[NODE].getRight() != null && nodes[NODE].getRight().is2Node()){ // case 1
                        nodes[NODE].getLeft().setIsRed(true);
                        nodes[NODE].getRight().setIsRed(true);
                }else if(nodes[DAD] != null){
                    NodeHandler nephew = getNephew();
                    if(nephew.nodes[DAD].isRed()){ // case 6-9
                        nephew.rotate(GRAND_DAD);
                        nodes[GRAND_GRAND_DAD] = nodes[GRAND_DAD];
                        nodes[GRAND_DAD] = nephew.nodes[GRAND_DAD];
                        nephew = getNephew();
                    }
                    if(nephew.nodes[DAD].is2Node()){ // case 2
                        nodes[NODE].setIsRed(true);
                        nephew.nodes[DAD].setIsRed(true);
                        nodes[DAD].setIsRed(false);
                    }else{
                        if(!nephew.isNull() && nephew.nodes[NODE].isRed())
                            nephew.rotate(DAD); // case 4-5
                        nephew.rotate(GRAND_DAD); // case 3
                    }
                }
            }
        }

        private NodeHandler getNephew(){
            RedBlackNode node = nodes[NODE];
            RedBlackNode dad = nodes[DAD];
            RedBlackNode grandDad = nodes[GRAND_DAD];

            RedBlackNode brother = node == dad.getLeft() ? dad.getRight() : dad.getLeft();
            RedBlackNode nephew = node == dad.getLeft() ? brother.getLeft() : brother.getRight();
            NodeHandler h = new NodeHandler(nephew);

            h.nodes[DAD] = brother;
            h.nodes[GRAND_DAD] = dad;
            h.nodes[GRAND_GRAND_DAD] = grandDad;

            return h;
        }
        
        public void set(RedBlackNode node, int kind, boolean left, boolean copyColors) {
            if(nodes[kind+1] == null) {
                System.out.println("Root is now _" + node.getKey() + "_");
                root = node;
                root.setIsRed(false);
            }else if(nodes[kind] != null ? nodes[kind+1].getLeft() == nodes[kind] : left){
                if(node != null)
                    System.out.println("Setting node _" + node.getKey() + "_ left from _" + nodes[kind+1].getKey() + "_");
                nodes[kind+1].setLeft(node);
            }else{
                if(node != null)
                    System.out.println("Setting node _" + node.getKey() + "_ right from _" + nodes[kind+1].getKey() + "_");
                nodes[kind+1].setRight(node);
            }
            if(copyColors && nodes[kind] != null && node != null)
                node.setIsRed(nodes[kind].isRed());
            nodes[kind] = node;
        }
        
        public void rotate(int kind) {
            RedBlackNode dad = nodes[kind];
            System.out.print(".............................\nRotating at dad _" + dad.getKey() + "_ ");
            RedBlackNode son = nodes[kind-1];
            boolean sonIsRed = son.isRed();

            if(!sonIsRed){
                if(son.getLeft() != null)
                    son.getLeft().setIsRed(false);
                if(son.getRight() != null)
                    son.getRight().setIsRed(false);
                dad.setIsRed(false);
                dad.getLeft().setIsRed(true);
                dad.getRight().setIsRed(true);
            }else{
                son.setIsRed(dad.isRed());
                dad.setIsRed(sonIsRed);
            }
			
            if(dad.getLeft() == son) {
                System.out.println("clockwise");
                dad.setLeft(son.getRight());
                son.setRight(dad);
            }else {
                System.out.println("counter clockwise");
                dad.setRight(son.getLeft());
                son.setLeft(dad);
            }
            set(son, kind, nodes[GRAND_DAD].getLeft() == dad, false);
        }
        
        public void split(int i) {
            RedBlackNode grandDad = nodes[GRAND_DAD];
            RedBlackNode dad = nodes[DAD];
            RedBlackNode son = nodes[NODE];
            if(dad != null && dad.isRed()) {
                System.out.println(".............................\nSplitting at dad _" + dad.getKey() + "_");
                boolean dadIsLeftFromGrandDad = grandDad.getLeft() == dad;
                boolean sonIsLeftFromDad = dad.getLeft() == son;
                System.out.println("Dad _" + dad.getKey() + "_ is " + (dadIsLeftFromGrandDad ? "left" : "right") + " from GrandDad _" + grandDad.getKey() + "_");
                System.out.println("Son _" + son.getKey() + "_ is " + (sonIsLeftFromDad ? "left" : "right") + " from Dad _" + dad.getKey() + "_");
                boolean isSameOrientation = dadIsLeftFromGrandDad == sonIsLeftFromDad;
                if(!isSameOrientation)
                    rotate(DAD);
                rotate(GRAND_DAD);
            }
        }
    }
}