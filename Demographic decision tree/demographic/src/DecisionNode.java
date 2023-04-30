import java.util.HashMap;
import java.util.Map;

public class DecisionNode {
    private String attribute;
    private String classification;
    private Map<String,DecisionNode> children;
    
    public DecisionNode(){
        children = new HashMap<>();
    }

    public String getAttribute(){
        return attribute;
    }

    public void setAttribute(String attribute){
        this.attribute = attribute;
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
    public boolean isclassification(){
        if(this.classification==null){
            return false;
        }else{
            return true;
        }
    }


    public boolean isLeaf(){
        return children.isEmpty();
    }
}
