public class PrintTree {
    public static String dec1 = "is<40";
    public static String dec3=">40<70";
    public static String dec2="is>70";
    public void printTree(DecisionNode rootNode){
        String firstattrib = rootNode.getAttribute();
        DecisionNode secondNodeleft = rootNode.getChild(dec1);
        DecisionNode secondNoderight = rootNode.getChild(dec2);
        DecisionNode secondNodemiddle = rootNode.getChild(dec3);
        String secondattrib = secondNodeleft.getAttribute();

        DecisionNode leftleft = secondNodeleft.getChild(dec1);
        DecisionNode leftright = secondNodeleft.getChild(dec2);
        DecisionNode leftmiddle = secondNodeleft.getChild(dec3);

        DecisionNode middleleft = secondNodemiddle.getChild(dec1);
        DecisionNode middleright = secondNodemiddle.getChild(dec2);
        DecisionNode middlemiddle = secondNodemiddle.getChild(dec3);


        DecisionNode rightleft = secondNoderight.getChild(dec1);
        DecisionNode rightright = secondNoderight.getChild(dec2);
        DecisionNode rightmiddle = secondNoderight.getChild(dec3);
        System.out.println();
        for(int i=0;i<6;i++){
            if(i==1){
                System.out.println("                         "+firstattrib);
                System.out.println();
            }
            if(i==2){
                System.out.println("       "+secondattrib+"       "+secondattrib+"       "+secondattrib);
                System.out.println();
            }
            if(i==3){
                System.out.print("   "+leftleft.getClassification()+" "+leftmiddle.getClassification()+" "+leftright.getClassification());
                System.out.print("      "+middleleft.getClassification()+" "+middlemiddle.getClassification()+" "+middleright.getClassification());
                System.out.println("          "+rightleft.getClassification()+" "+rightmiddle.getClassification()+"  "+rightright.getClassification());
                System.out.println();
            }
        }
    }
    
}
