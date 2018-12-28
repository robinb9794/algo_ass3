package redblack;

import interfaces.Tree;
import workers.ByteManager;

public class PatriciaTree implements Tree{
    private PatriciaNode root;
    private ByteManager byteManager;
    
    public PatriciaTree(){
        this.byteManager = new ByteManager();
    }

    @Override
    public Node getRoot(){
        return this.root;
    }
    
    @Override
    public boolean search(String key){
        NodeHandler h = new NodeHandler(root);
        h.search(key);
        return !h.isNull() && h.nodes[h.NODE].getKey() == key;
    }

    @Override
    public void insert(String key){
        NodeHandler h = new NodeHandler(root);
        h.search(key);
        int index = 0;
        if(h.isNull()){
            if(h.nodes[h.DAD] != null){
                while(left(key, index) == left(h.nodes[h.DAD].getKey(), index) && index < h.nodes[h.DAD].getBitPosition())
                    ++index;
                if(index == h.nodes[h.DAD].getBitPosition())
                    ++index;
            }
        }else if(!h.nodes[h.NODE].getKey().equals(key)){
            while(left(key, index) == left(h.nodes[h.NODE].getKey(), index)) // case 3
                ++index;
        }else
            return; // case 1
        h = new NodeHandler(root);
        h.search(key, index);
        h.set(new PatriciaNode(key, index, h.nodes[h.NODE]), h.NODE);
    }

    @Override
    public boolean delete(String key){
        NodeHandler h = new NodeHandler(root);
        h.search(key);
        if(h.isNull() || !h.nodes[h.NODE].getKey().equals(key))
            return false;
        else{
            NodeHandler h2 = new NodeHandler(h.nodes[h.DAD]);
            h2.search(h.nodes[h.DAD].getKey());
            h.nodes[h.NODE].setKey(h.nodes[h.DAD].getKey());
            h2.set(h.nodes[h.NODE], h2.NODE);
            h.set(h.brother(h.NODE), h.DAD);
        }
        return true;
    }

    public static boolean left(String key, int bitPosition){
        byte[] keyBytes = key.getBytes();
        int index = (bitPosition >= 8 && keyBytes.length > 1) ? (keyBytes.length-1-(bitPosition%8)) : keyBytes.length-1;
        return ((keyBytes[index] >> bitPosition) & 1) == 0;
    }

    private class NodeHandler{
        public final int NODE = 0;
        public final int DAD = 1;

        private PatriciaNode[] nodes = new PatriciaNode[3];

        NodeHandler(PatriciaNode node){
            nodes[NODE] = node;
        }

        void down(boolean left){
            for(int i = nodes.length - 1; i>0; --i)
                nodes[i] = nodes[i-1];
            nodes[NODE] = left ? nodes[DAD].getLeft() : nodes[DAD].getRight();
        }

        boolean isNull(){
            return nodes[NODE] == null;
        }

        void set(PatriciaNode node, int kind){
            if(nodes[kind+1] == null)
                root = node;
            else if(nodes[kind] != null ? nodes[kind+1].getLeft() == nodes[kind] : left(node.getKey(), nodes[kind+1].getBitPosition()))
                nodes[kind+1].setLeft(node);
            else
                nodes[kind+1].setRight(node);
            nodes[kind] = node;
        }

        void search(String key, int maxPosition){
            int lastBitPosition = -1;
            while(!isNull() && lastBitPosition < nodes[NODE].getBitPosition() && maxPosition > nodes[NODE].getBitPosition()){
                lastBitPosition = nodes[NODE].getBitPosition();
                down(left(key, lastBitPosition));
            }
        }

        void search(String key){
            search(key, Integer.MAX_VALUE);
        }

        PatriciaNode brother(int kind){
            PatriciaNode dad = nodes[kind+1];
            PatriciaNode node = nodes[kind];
            return dad.getLeft() == node ? dad.getRight() : dad.getLeft();
        }
    }
}