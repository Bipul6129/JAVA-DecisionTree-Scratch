package Gini_index;

import java.util.HashMap;

public class mainapp {
    


    public static void main(String[] args) {
        String moneyLeft="Rich";
        String moneyRight="Poor";
        String parentLeft="Yes";
        String parentRight="No";
        String weatherLeft="Sunny";
        String weatherRight="Rainy";
        String money="Money";
        String parents="Parents";
        String weather="Weather";

        String smallgini1left;
        String smallgini1right;
        String smallgini1label;
        String biginie1left,biginie1right,biginielabel1;
        String biginie2left,biginie2right,biginielabel2;
        String smallRightginileft;
        String smallRightginiright;
        String smallRightginilabel;
        String lastlabel,lastright,lastleft;

        String smallgini2label;

        double ginirightcal1;
        double ginirightcal2;
        double ginileftcal1;
        double ginileftcal2;

        HashMap <String,String> bigginie1 = new HashMap<>();

        System.out.println("running");
        HashMap<String,Integer> topattrib = new HashMap<>();
        topattrib.put( "Weather",0);
        topattrib.put( "Parents",1);
        topattrib.put( "Money",2);
        topattrib.put( "Decision",3);

        Hashmaps hsmap = new Hashmaps();
        hsmap.createdecision();
        hsmap.CountDecisionAndGini();
        
        // hsmap.createMoney();
        // hsmap.CountMoneyDecisionAndGini();
        double moneygini=hsmap.getginiIndex("Rich", "Poor", 2);
        double parentsgini=hsmap.getginiIndex("Yes", "No", 1);
        double weathergini=hsmap.getginiIndex("Sunny", "Rainy", 0);
        
        System.out.println(moneygini);
        System.out.println(parentsgini);
        System.out.println(weathergini);
        
        double smallgini1 = moneygini;
        smallgini1left=moneyLeft;
        smallgini1right=moneyRight;
        smallgini1label=money;
        biginie1left=parentLeft;biginie1right=parentRight;biginielabel1=parents;
        biginie2left=weatherLeft;biginie2right=weatherRight;biginielabel2=weather;

        if(smallgini1>parentsgini){
            smallgini1=parentsgini;
            smallgini1left=parentLeft;
            smallgini1right=parentRight;
            smallgini1label=parents;
            biginie1left=moneyLeft;biginie1right=moneyRight;biginielabel1=money;
            biginie2left=weatherLeft;biginie2right=weatherRight;biginielabel2=weather;
            
        }else if(smallgini1>weathergini){
            smallgini1=weathergini;
            smallgini1left=weatherLeft;
            smallgini1right=weatherRight;
            smallgini1label=weather;
            biginie1left=parentLeft;biginie1right=parentRight;biginielabel1=parents;
            biginie2left=moneyLeft;biginie2right=moneyRight;biginielabel2=money;
        }
        System.out.println(smallgini1+" of "+smallgini1label);
        DecisionTree dTree = new DecisionTree(smallgini1label,smallgini1left,smallgini1right,topattrib.get(smallgini1label));
        dTree.addroot(dTree);

        ginirightcal1=hsmap.getginiIndexSecond(biginie1left, biginie1right, topattrib.get(biginielabel1),smallgini1right, topattrib.get(smallgini1label));
        ginileftcal1=hsmap.getginiIndexSecond(biginie1left, biginie1right, topattrib.get(biginielabel1),smallgini1left, topattrib.get(smallgini1label));
        
        ginirightcal2=hsmap.getginiIndexSecond(biginie2left, biginie2right, topattrib.get(biginielabel2),smallgini1right, topattrib.get(smallgini1label));
        ginileftcal2=hsmap.getginiIndexSecond(biginie2left, biginie2right, topattrib.get(biginielabel2),smallgini1left, topattrib.get(smallgini1label));
        
        
        double smallRightgini2=ginirightcal1;
        smallRightginilabel = biginielabel1;
        smallRightginileft = biginie1left;
        smallRightginiright = biginie1right;
        lastlabel = biginielabel2;
        lastleft = biginie2left;
        lastright = biginie2right;
        
        if(ginirightcal1>ginirightcal2){
            smallRightgini2=ginirightcal2;
            smallRightginilabel = biginielabel2;
            smallRightginileft = biginie2left;
            smallRightginiright = biginie2right;
            lastlabel = biginielabel1;
            lastleft = biginie2left;
            lastleft = biginie2right;
        }
        if(smallRightgini2!=0){
            DecisionTree dTreeR2 = new DecisionTree(smallRightginilabel, smallRightginileft, smallRightginiright, topattrib.get(smallRightginilabel));
            dTree.addRight(dTreeR2);
            DecisionTree dTreeR3 = new DecisionTree(lastlabel, lastleft,lastright, topattrib.get(lastlabel));
            dTreeR2.addLeft(dTreeR3);
            dTreeR2.addRight(dTreeR3);
        }

        System.out.println(smallRightgini2);




        
        

        


        
        

    }

    
}
