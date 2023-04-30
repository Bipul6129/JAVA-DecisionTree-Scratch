package Gini_index;

public class DecisionTree {
    public String data;
    DecisionTree left;
    DecisionTree right;
    DecisionTree root;
    String leftmethod;
    String rightmethod;
    int methodindex;

    DecisionTree(String data,String leftmethod,String rightmethod,Integer index){
        this.data=data;
        this.left=null;
        this.right=null;
        this.leftmethod=leftmethod;
        this.rightmethod=rightmethod;
        this.methodindex = index;
    }
    public void addroot(DecisionTree added){
        if(root==null){
            this.root=added;
        }
    }

    public void addLeft(DecisionTree left){
        this.left = left;
    }

    public void addRight(DecisionTree right){
        this.right=right;
    }

}
