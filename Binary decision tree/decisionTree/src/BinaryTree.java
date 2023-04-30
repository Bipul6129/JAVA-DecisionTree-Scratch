public class BinaryTree {
    TreeNode root;

    public void addRoot(TreeNode data){
        if(root==null){
            root = data;
        }
    }

    public void addLeft(TreeNode current,TreeNode added){
        current.left = added;
    }

    public void addRight(TreeNode current, TreeNode added){
        current.right = added;
    }
}
