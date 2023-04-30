package decision_analysis;


public class mainrun {
    public static void main(String[] args) {
    
        
        String candyshop = "Candyshop";
        String lemonshop = "Lemonshop";
        TreeNode o1 = new TreeNode("Choose candy shop or lemon shop");
        TreeNode cs = new TreeNode(candyshop);
        TreeNode ls = new TreeNode(lemonshop);
        TreeNode cs_success = new TreeNode(candyshop, 100, 50, "success");
        TreeNode cs_fail = new TreeNode(candyshop, -30, 50, "fail");
        TreeNode ls_success = new TreeNode(lemonshop, 90, 50, "success");
        TreeNode ls_fail = new TreeNode(lemonshop, -10, 50, "fail");

        BinaryTree bintree = new BinaryTree();
        bintree.addRoot(o1);
        bintree.addLeft(o1,cs);
        bintree.addRight(o1,ls);

        bintree.addLeft(cs,cs_success);
        bintree.addRight(cs,cs_fail);

        bintree.addLeft(ls,ls_success);
        bintree.addRight(ls,ls_fail);
        
        TreeNode current = bintree.root;
        System.out.println("For candyshop");

        current = current.left;
        System.out.println(current.shop);
        TreeNode success = current.left;
        TreeNode fail = current.right;
        double expectedvalue = ((double)success.percent/100)*(double)success.profit+((double)fail.percent/100)*(double)fail.profit;
        System.out.println("Expected value of candyshop is $"+expectedvalue);

        current = bintree.root;
        System.out.println("For lemonshop");

        current = current.right;
        System.out.println(current.shop);
        success = current.left;
        fail = current.right;
        expectedvalue = ((double)success.percent/100)*(double)success.profit+((double)fail.percent/100)*(double)fail.profit;
        System.out.println("Expected value of lemonshop is $"+expectedvalue);

    }
    
}
