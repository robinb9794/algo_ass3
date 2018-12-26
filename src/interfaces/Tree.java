package interfaces;

import redblack.Node;

public interface Tree{
    Node getRoot();
    boolean search(String key);
    void insert(String key);
    boolean delete(String key);
}