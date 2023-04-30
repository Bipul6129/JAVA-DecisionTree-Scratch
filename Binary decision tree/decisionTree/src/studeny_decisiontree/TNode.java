package studeny_decisiontree;

public class TNode {
    TNode right;
    TNode left;
    String check;
    double acceptablevalue;
    String codex;

    TNode(String check,double value,String codex){
        this.check = check;
        acceptablevalue = value;
        right=null;
        left=null;
        this.codex = codex;

    }

    TNode(String check){
        this.check=check;
    }
    
}
