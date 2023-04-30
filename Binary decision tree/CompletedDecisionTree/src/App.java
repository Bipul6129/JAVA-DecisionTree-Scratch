import java.util.HashMap;


public class App {
    public static void main(String[] args) throws Exception {
        String[][] dataset={
            {"Y","Y","Pass"},
            {"N","Y","Pass"},
            {"Y","N","Pass"},
            {"Y","Y","Fail"},
            {"N","Y","Fail"},
            {"N","N","Fail"},
            {"Y","Y","Pass"},

        };
        System.out.println();
        DecisionNode rootnode=buildDecisionTree(dataset);
        String[][] testdata = {{"N","Y"}};
        System.out.println(predict(testdata, rootnode));
        printTree(rootnode);
        
    }

    public static DecisionNode buildDecisionTree(String[][] dataset){
        if(dataset.length<4){
            System.out.println();
            System.out.println("NotEnoughData");
            return new DecisionNode();
        }else{
        DecisionNode rootNode = new DecisionNode();
        String[] attributes = {"Present>60","Submison>60"};
        String bestattrib = "",worstattrib="";
        int bestindex=100,badindex=100;
        double smallestgini=1000,biggini=1000;
        for(int i=0;i<attributes.length;i++){
            double gini = doubleConditionGini(dataset, "Y", "N", i);
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
        String[][]bestSplitY=SplitDataOnAttrib(dataset, bestindex, "Y");
        String[][]bestSplitN=SplitDataOnAttrib(dataset, bestindex, "N");

       ///LEFTTT NODEEE//////////
        DecisionNode leftNode = new DecisionNode();
        leftNode.setAttribute(worstattrib);
        leftNode.addIndex(badindex);
        String[][]leftNodeSplitY=SplitDataOnAttrib(bestSplitY, badindex, "Y");
        String[][]leftNodeSplitN=SplitDataOnAttrib(bestSplitY, badindex, "N");

        DecisionNode leftNodeleft = new DecisionNode();
        leftNodeleft.setClassification(getcommonResult(leftNodeSplitY));
        leftNode.addChild("Y",leftNodeleft);

        DecisionNode leftNodeRight = new DecisionNode();
        leftNodeRight.setClassification(getcommonResult(leftNodeSplitN));
        leftNode.addChild("N", leftNodeRight);

        rootNode.addChild("Y", leftNode);

        ///////FOR RIGHT NODEEEE////////////////
        DecisionNode rightNode = new DecisionNode();
        rightNode.setAttribute(worstattrib);
        rightNode.addIndex(badindex);
        String[][]rightNodeSplitY=SplitDataOnAttrib(bestSplitN, badindex, "Y");
        String[][]rightNodeSplitN=SplitDataOnAttrib(bestSplitN, badindex, "N");

        DecisionNode rightNodeleft = new DecisionNode();
        rightNodeleft.setClassification(getcommonResult(rightNodeSplitY));
        rightNode.addChild("Y",rightNodeleft);

        DecisionNode rightNodeRight = new DecisionNode();
        rightNodeRight.setClassification(getcommonResult(rightNodeSplitN));
        rightNode.addChild("N", rightNodeRight);

        rootNode.addChild("N", rightNode);

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
    public static double doubleConditionGini(String[][]dataset,String val1,String val2,Integer index){
        HashMap<String,Integer> counts = new HashMap<>();
        counts = doubleConditionLoop(dataset,val1,index);
        double gini1 = calculateGini(counts.get("Pass"), counts.get("Fail"), counts.get("Pass")+counts.get("Fail"));


        HashMap<String,Integer> counts2 = new HashMap<>();
        counts2 = doubleConditionLoop(dataset,val2,index);
        double gini2 = calculateGini(counts2.get("Pass"), counts2.get("Fail"), counts2.get("Pass")+counts2.get("Fail"));

        return calculateWeighted(gini1, gini2, counts, counts2);

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

    public static double calculateWeighted(double gini1,double gini2,HashMap<String,Integer> counts,HashMap<String,Integer> counts2){
        double weighted;
        double totalval1 = counts.get("Pass")+counts.get("Fail");
        double totalval2 = counts2.get("Pass")+counts.get("Fail");
        double overalllen = totalval1+totalval2;
        weighted = gini1*(totalval1/overalllen)+gini2*(totalval2/overalllen);
        return weighted;
    }


    public static double calculateGini(double val1,double val2,double totallen){
        double gini;
        gini = 1-(Math.pow((val1/totallen),2)+Math.pow((val2/totallen),2));
        return gini;

    }

    public static String predict(String[][] test,DecisionNode rootNode){
    
        while(!rootNode.isLeaf()){
            rootNode= rootNode.getChild(test[0][rootNode.getIndex()]);
        }
        return rootNode.getClassification();
    }

    public static void printTree(DecisionNode rootNode){
        int count=0;
        String firstattrib = rootNode.getAttribute();
        DecisionNode secondNodeleft = rootNode.getChild("Y");
        DecisionNode secondNoderight = rootNode.getChild("N");
        String secondattrib = secondNodeleft.getAttribute();

        DecisionNode leftleft = secondNodeleft.getChild("Y");
        DecisionNode leftright = secondNodeleft.getChild("N");

        DecisionNode rightleft = secondNoderight.getChild("Y");
        DecisionNode rightright = secondNoderight.getChild("N");

        for(int i=0;i<6;i++){

            if(i==0){
                System.out.println("        "+firstattrib);
            }
            if(i==1){
                System.out.println("");
                System.out.println("    "+secondattrib+"    "+secondattrib);
            }
            if(i==3){
                System.out.println("");
                System.out.println("  "+leftleft.getClassification()+"  "+leftright.getClassification()+"          "+rightleft.getClassification()+"  "+rightright.getClassification());
            }
            if(i>=4){
                System.out.println();
            }


        }
    }
}
