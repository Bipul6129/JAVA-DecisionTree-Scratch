import java.util.Map;


public class PrintTree {
    public static int flag=0;
    public void print(DecisionNode rootnode,Map<String,String[]> attribute){
        if(rootnode.isclassification()){
            System.out.println("=="+rootnode.getClassification());
            System.out.println("---------------------------");
        }else{
            System.out.println("0 "+rootnode.getAttribute());
            
        }
        String currentattribute = rootnode.getAttribute();
        
        if(!rootnode.isLeaf()){
            for(int i=0;i<=2;i++){
                System.out.println(attribute.get(currentattribute)[i]+"//");
                DecisionNode othernode = rootnode.getChild(attribute.get(currentattribute)[i]);  
                  
                print(othernode, attribute); 
                    
            }
            

        }
        
    }
}
