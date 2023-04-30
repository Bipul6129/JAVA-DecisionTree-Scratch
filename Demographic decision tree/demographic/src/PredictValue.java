import java.util.Map;

public class PredictValue {

    public void checkclass(DecisionNode root,Map<String,String> checkvalue){
        String attribute;
        String checkattribute;
        DecisionNode nextnode;
        if(!root.isclassification()){
            attribute = root.getAttribute();
            checkattribute=checkvalue.get(attribute);
            nextnode = root.getChild(checkattribute);
            checkclass(nextnode, checkvalue);
        }
        if(root.isclassification())
        {
            System.out.println("The given student will likely perform "+root.getClassification());
        }
        
    }
}
