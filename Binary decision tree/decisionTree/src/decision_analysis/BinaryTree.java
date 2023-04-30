package decision_analysis;

public class BinaryTree {
    TreeNode root;

    public void addRoot(TreeNode root){
        this.root = root;
    }

    public void addLeft(TreeNode current,TreeNode added){
        current.left = added;
    }

    public void addRight(TreeNode current,TreeNode added){
        current.right = added;
    }
}
