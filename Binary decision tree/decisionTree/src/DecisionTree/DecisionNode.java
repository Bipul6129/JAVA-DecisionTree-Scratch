package DecisionTree;
import java.util.Map;
import java.util.HashMap;


public class DecisionNode {
    private String attribute;
    private String classification;
    private Map<String,DecisionNode>children;

    public DecisionNode(){
        children = new HashMap<>();
    }

    public String getAttribute(){
        return attribute;
    }

    public void setAttribute(String attribute){
        this.attribute=attribute;
    }

    public String getClassification(){
        return classification;
    }

    public void setClassification(String classification){
        this.classification=classification;
    }

    public void addChild(String value,DecisionNode child){
        children.put(value,child);
    }

    public DecisionNode getChild(String value){
        return children.get(value);
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }
}
