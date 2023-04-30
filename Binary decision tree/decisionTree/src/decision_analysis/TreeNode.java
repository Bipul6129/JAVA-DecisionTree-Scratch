package decision_analysis;

public class TreeNode {
    TreeNode left;
    TreeNode right;
    int profit;
    int percent;
    String result;
    String shop;

    TreeNode(String shop){
        this.shop = shop;
        profit = 0;
        percent = 0;
        result = "none";
    }

    TreeNode(String shop,int profit,int percent,String result){
        this.shop = shop;
        this.profit = profit;
        this.percent = percent;
        this.result = result;
    }


}
