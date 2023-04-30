package real_decisiontree;

import java.sql.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class entropycal {
    public double entireEntropy;

    public double Outlookinfo;
    public double Tempinfo;
    public double Huminfo;
    public double Windinfo;

    public double greatFirstInfo;
    public int greatFirstAtrib;


    public String[][] dataset = {{"Outlook","Temp","Hum","Wind","PlayTenis"},
        {"Sunny","Hot","High","Weak","No"},
        {"Sunny","Hot","High","Strong","No"},
        {"Overcast","Hot","High","Weak","Yes"},
        {"Rain","Mild","High","Weak","Yes"},
        {"Rain","Cool","Normal","Weak","Yes"},
        {"Rain","Cool","Normal","Strong","No"},
        {"Overcast","Cool","Normal","Strong","Yes"},
        {"Sunny","Mild","High","Weak","No"},
        {"Sunny","Cool","Normal","Weak","Yes"},
        {"Rain","Mild","Normal","Weak","Yes"},
        {"Sunny","Mild","Normal","Strong","Yes"},
        {"Overcast","Mild","High","Strong","Yes"},
        {"Overcast","Hot","Normal","Weak","Yes"},
        {"Rain","Mild","High","Strong","No"},
    };


    public static double log2(double N){
        double result = (double)(Math.log(N)/Math.log(2));
        return result;
    }

    public void entireentropy(){
        double positive=0;
        double negative=0;
        double totallength = dataset.length-1;
        
        for(int i=1;i<dataset.length;i++){
            for(int j=0;j<dataset[i].length;j++){
                if(dataset[i][j].equals("Yes")){
                    positive = positive+1;
                }else if(dataset[i][j].equals("No")){
                    negative=negative+1;
                }
            }
        }
        System.out.println("Total positive "+positive+" neg "+negative);
        entireEntropy = (-positive/totallength)*log2(positive/totallength)-(negative/totallength)*log2(negative/totallength);
        System.out.println(entireEntropy);
    }

    public void attributeEntropy(){
        calAttribEntropy(0);
        calAttribEntropy(1);
        calAttribEntropy(2);
        calAttribEntropy(3);
        System.out.println(Outlookinfo);
        System.out.println(Tempinfo);
        System.out.println(Huminfo);
        System.out.println(Windinfo);
        System.out.println();
        checkgreater();
        System.out.println(greatFirstInfo);
        System.out.println(greatFirstAtrib);
        

    }

    public void calAttribEntropy(int attribnum){
        double positive1=0;
        double negative1=0;
        double positive2=0;
        double negative2=0;
        double positive3=0;
        double negative3=0;

        double totallength1 = 0;
        double totallength2 = 0;
        double totallength3= 0;


        double attribEntro1 =0;
        double attribEntro2 = 0;
        double attribEntro3 = 0;
        int count=0;
        Set<String> uniquevalue = new HashSet<>();
        for(int i=1;i<dataset.length;i++){
            for(int j=0;j<dataset[i].length;j++){
                if(!uniquevalue.contains(dataset[i][attribnum])){
                    uniquevalue.add(dataset[i][attribnum]);
                }
            }
        }
        for(String values:uniquevalue){
            // System.out.println(values);
            
            for(int i=1;i<dataset.length;i++){
                    if(dataset[i][attribnum].equals(values)&&dataset[i][4]=="Yes"){
                        if(count==0){
                            positive1=positive1+1;
                            
                        }
                        if(count==1){
                            positive2=positive2+1;
                        }
                        if(count==2){
                            positive3=positive3+1;
                        }
                        
                    }else if(dataset[i][attribnum].equals(values)&&dataset[i][4]=="No"){
                        if(count==0){
                            negative1=negative1+1;
                        }
                        if(count==1){
                            negative2=negative2+1;
                        }
                        if(count==2){
                            negative3=negative3+1;
                        }
                    }   
            }
            if(count==0){
                totallength1=positive1+negative1;
                attribEntro1 =(-positive1/totallength1)*log2(positive1/totallength1)-(negative1/totallength1)*log2(negative1/totallength1);
                // System.out.println("Entropy "+attribEntro1);
                
            }else if(count==1){
                totallength2=positive2+negative2;
                attribEntro2 =(-positive2/totallength2)*log2(positive2/totallength2)-(negative2/totallength2)*log2(negative2/totallength2);
                // System.out.println("Entropy "+attribEntro2);
            }else if(count==2){
                totallength3=positive3+negative3;
                attribEntro3 =(-positive3/totallength3)*log2(positive3/totallength3)-(negative3/totallength3)*log2(negative3/totallength3);
                // System.out.println("Entropy "+attribEntro3);
            }

            count++;
        }
        if(Double.isNaN(attribEntro1)){
            attribEntro1=0;
        }
        if(Double.isNaN(attribEntro2)){
            attribEntro2=0;
        }
        if(Double.isNaN(attribEntro3)){
            attribEntro3=0;
        }
        // System.out.println("pos:"+positive1+" neg: "+negative1);
        // System.out.println("pos:"+positive2+" neg: "+negative2);
        // System.out.println("pos:"+positive3+" neg: "+negative3);

        if(count==2){
            double datasetlength = dataset.length-1;
            Gaincal gaincal = new Gaincal(entireEntropy,attribEntro1, attribEntro2,(positive1+negative1)/(datasetlength),(positive2+negative2)/(datasetlength));
            // System.out.println("The infogain is "+gaincal.infogain);
            assigninfo(attribnum,gaincal.infogain);
            
        }

        if(count==3){
            double datasetlength = dataset.length-1;
            Gaincal gaincal = new Gaincal(entireEntropy,attribEntro1, attribEntro2, attribEntro3,(positive1+negative1)/(datasetlength),(positive2+negative2)/(datasetlength),(positive3+negative3)/(datasetlength));
            // System.out.println("The infogain is "+gaincal.infogain);
            assigninfo(attribnum,gaincal.infogain);
            
        }

        
    }

    public void assigninfo(int attribnum,double gaincal){
        if(attribnum==0){
            Outlookinfo=gaincal;
        }else if(attribnum==1){
            Tempinfo=gaincal;
        }else if(attribnum==2){
            Huminfo=gaincal;
        }else if(attribnum==3){
            Windinfo=gaincal;
        }

    }

    public void checkgreater(){
        greatFirstInfo = Outlookinfo;
        greatFirstAtrib = 0;
        if(Tempinfo>greatFirstInfo){
            greatFirstInfo=Tempinfo;
            greatFirstAtrib=1;
        }
        if(Huminfo>greatFirstInfo){
            greatFirstInfo=Huminfo;
            greatFirstAtrib=2;
        }
        if(Windinfo>greatFirstInfo){
            greatFirstInfo=Windinfo;
            greatFirstAtrib=3;
        }

    }

    


}
