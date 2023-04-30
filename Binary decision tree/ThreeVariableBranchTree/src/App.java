import java.util.HashMap;


public class App {
    public static String dec1 = "is<40";
    public static String dec2=">40<70";
    public static String dec3="is>70";
    public static String attribute1 = "PresentRecord";
    public static String attribute2 = "SubmissionRecord";
    public static void main(String[] args) throws Exception {
        String[][] dataset={
            {"is<40","is>70","Fail"},
            {"is<40","is>70","Pass"},
            {"is<40","is>70","Fail"},
            {"is<40",">40<70","Pass"},
            {"is<40","is<40","Fail"},
            {"is>70","is<40","Pass"},
            {"is>70","is>70","Pass"},
            {">40<70","is>70","Fail"},
            
            

        };
        System.out.println();
        DecisionNode rootnode=buildDecisionTree(dataset);
        String[][] testdata = {{"is>70","is>70"}};
        System.out.println("The student will be "+predict(testdata, rootnode));
        PrintTree tree1 = new PrintTree();
        tree1.printTree(rootnode);
        
    }

    public static DecisionNode buildDecisionTree(String[][] dataset){
        if(dataset.length<4){
            System.out.println();
            System.out.println("NotEnoughData");
            return new DecisionNode();
        }else{
        DecisionNode rootNode = new DecisionNode();
        String[] attributes = {attribute1,attribute2};
        String bestattrib = "",worstattrib="";
        int bestindex=100,badindex=100;
        double smallestgini=1000,biggini=1000;
        for(int i=0;i<attributes.length;i++){
            double gini = doubleConditionGini(dataset, dec1, dec2,dec3, i);
            if(gini<smallestgini){
                biggini=smallestgini;
                badindex=bestindex;
                worstattrib=bestattrib;
                smallestgini=gini;
                bestattrib=attributes[i];
                bestindex=i;
            }else{
                worstattrib=attributes[i];
                badindex=i;
                biggini=gini;
            }
        }
        rootNode.setAttribute(bestattrib);
        rootNode.addIndex(bestindex);
        System.out.println("Best attrib to SPLIT:"+ bestattrib);
        System.out.println("Small gini:"+smallestgini+" Bigginie:"+biggini);
        String[][]bestSplitY=SplitDataOnAttrib(dataset, bestindex, dec1);
        String[][]bestSplitN=SplitDataOnAttrib(dataset, bestindex, dec2);
        String[][]bestSplitM=SplitDataOnAttrib(dataset, bestindex, dec3);

        ///MIDDLE NODEEE////////
        createNode(badindex, worstattrib, bestSplitM, rootNode, dec3);

       ///LEFTTT NODEEE//////////
        createNode(badindex, worstattrib, bestSplitY, rootNode, dec1);

        ///////FOR RIGHT NODEEEE////////////////
        createNode(badindex, worstattrib, bestSplitN, rootNode, dec2);

        return rootNode;
    }
    }

    //GET MOST COMMON RESULT
    public static String getcommonResult(String[][] dataset){
        final HashMap<String,Integer> countofdecision = new HashMap<>();
        countofdecision.put("Pass",0);
        countofdecision.put("Fail",0);
        for(int i=0;i<dataset.length;i++){
            for(String key:countofdecision.keySet()){
                if(dataset[i][2]==key){
                    int value=countofdecision.get(key);
                    countofdecision.put(key,value+1);
                }
            }
        }
        String maxKey = "";
        int maxValue = Integer.MIN_VALUE;

        // Find key with greatest value
        for (String key : countofdecision.keySet()) {
            int value = countofdecision.get(key);
            if (value > maxValue) {
                maxValue = value;
                maxKey = key;
            }
        }
        // System.out.println(maxKey);
        return maxKey;

    }

    ///GET GINI OF WHOLE  PASS AND FAIL/////
    public static double getWholeGini(String[][]dataset){
        final HashMap<String,Integer> countofdecision = new HashMap<>();
        countofdecision.put("Pass",0);
        countofdecision.put("Fail",0);

        for(int i=0;i<dataset.length;i++){
            for(String key:countofdecision.keySet()){
                if(dataset[i][2]==key){
                    int value=countofdecision.get(key);
                    countofdecision.put(key,value+1);
                }
            }
        }
        double gini = calculateGini(countofdecision.get("Pass"), countofdecision.get("Fail"), countofdecision.get("Pass")+countofdecision.get("Fail"));
        return gini;
    }

    public static String[][] SplitDataOnAttrib(String[][]dataset,Integer bestindex,String value){
        int count=0;
        int countspace = 0;
        
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][bestindex].equals(value)){
                countspace++;
            }
        }
        String[][] newdataset= new String[countspace][dataset[0].length];
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][bestindex].equals(value)){
                for(int j=0;j<dataset[i].length;j++){
                    newdataset[count][j]=dataset[i][j];
                }
                count++;
            }
        }
        
        
        
        return newdataset;
    }

    //USED FOR DOUBLE CONDITION GINI I.E WEIGHTED AVERAGE////
    public static double doubleConditionGini(String[][]dataset,String val1,String val2,String val3,Integer index){
        HashMap<String,Integer> counts = new HashMap<>();
        counts = doubleConditionLoop(dataset,val1,index);
        double gini1 = calculateGini(counts.get("Pass"), counts.get("Fail"), counts.get("Pass")+counts.get("Fail"));


        HashMap<String,Integer> counts2 = new HashMap<>();
        counts2 = doubleConditionLoop(dataset,val2,index);
        double gini2 = calculateGini(counts2.get("Pass"), counts2.get("Fail"), counts2.get("Pass")+counts2.get("Fail"));

        HashMap<String,Integer> counts3 = new HashMap<>();
        counts3 = doubleConditionLoop(dataset,val3,index);
        double gini3 = calculateGini(counts3.get("Pass"), counts3.get("Fail"), counts3.get("Pass")+counts3.get("Fail"));

        return calculateWeighted(gini1, gini2,gini3, counts, counts2,counts3);

    }


    public static HashMap<String,Integer> doubleConditionLoop(String[][]dataset,String val1,Integer index){
        HashMap<String,Integer> counts = new HashMap<>();
        counts.put("Pass",0);
        counts.put("Fail",0);
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][index].equals(val1)){
                for(String key:counts.keySet()){
                    if(dataset[i][2]==key){
                        int value=counts.get(key);
                        counts.put(key,value+1);
                    }
                }
            }
        }
        return counts;

    }

    public static double calculateWeighted(double gini1,double gini2,double gini3,HashMap<String,Integer> counts,HashMap<String,Integer> counts2,HashMap<String,Integer> counts3){
        double weighted;
        double totalval1 = counts.get("Pass")+counts.get("Fail");
        double totalval2 = counts2.get("Pass")+counts.get("Fail");
        double totalval3 = counts3.get("Pass")+counts.get("Fail");
        double overalllen = totalval1+totalval2+totalval3;
        weighted = gini1*(totalval1/overalllen)+gini2*(totalval2/overalllen)+gini3*(totalval3/overalllen);
        return weighted;
    }


    public static double calculateGini(double val1,double val2,double totallen){
        double gini;
        gini = 1-(Math.pow((val1/totallen),2)+Math.pow((val2/totallen),2));
        return gini;

    }

    public static void createNode(Integer index,String attribute,String[][] splitdata,DecisionNode rootNode,String dec){
        DecisionNode node = new DecisionNode();
        node.setAttribute(attribute);
        node.addIndex(index);
        String[][]nodesplitY = SplitDataOnAttrib(splitdata,index,dec1);
        String[][]nodesplitN = SplitDataOnAttrib(splitdata,index,dec2);
        String[][]nodesplitM = SplitDataOnAttrib(splitdata,index,dec3);
        addchild(node, nodesplitY, dec1);
        addchild(node, nodesplitN, dec2);
        addchild(node, nodesplitM, dec3);
        rootNode.addChild(dec,node);

    }

    public static void addchild(DecisionNode parentNode,String[][] oversplit,String dec){
        DecisionNode childnode = new DecisionNode();
        childnode.setClassification(getcommonResult(oversplit));
        parentNode.addChild(dec, childnode);
        
    }

    public static String predict(String[][] test,DecisionNode rootNode){
    
        while(!rootNode.isLeaf()){
            rootNode= rootNode.getChild(test[0][rootNode.getIndex()]);
        }
        return rootNode.getClassification();
    }

}
