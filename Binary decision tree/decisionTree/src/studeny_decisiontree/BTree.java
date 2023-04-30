package studeny_decisiontree;

public class BTree {
    TNode root;

    public void addRoot(TNode root){
        this.root = root;
    }

    public void addRight(TNode current,TNode added){
        current.right = added;
    }

    public void addLeft(TNode current,TNode added){
        current.left = added;
    }
}
