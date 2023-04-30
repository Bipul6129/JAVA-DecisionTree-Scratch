package Gini_index;
import java.util.HashMap;

public class Hashmaps extends calculate_Gini{
   double OutputGini;
   double MoneyGini;
   double ParentsGini;
    String[][] dataset = {
        {"Sunny","Yes","Rich","Cinema"},
        {"Sunny","No","Rich","Tennis"},
        {"Sunny","Yes","Rich","Cinema"},
        {"Rainy","Yes","Poor","Cinema"},
        {"Rainy","No","Rich","Stayin"},
        {"Rainy","Yes","Poor","Cinema"},
        {"Sunny","No","Poor","Cinema"},
        {"Rainy","No","Rich","Shopping"},
        {"Sunny","Yes","Rich","Cinema"},
        {"Sunny","No","Rich","Tennis"},
    
    
    };



    public HashMap<String,Integer> countofdecision = new HashMap<>();
    public void createdecision(){
        createhashpairs(countofdecision, "Cinema", "Tennis", "Stayin","Shopping");
    }

    public void CountDecisionAndGini(){
        for(int i=0;i<dataset.length;i++){           
                for(String key:countofdecision.keySet()){
                    if(dataset[i][3]==key){
                        int value = countofdecision.get(key);
                        countofdecision.put(key,value+1);
                    }
                }
        }
        OutputGini=calculategini(countofdecision.get("Cinema"), countofdecision.get("Tennis"), countofdecision.get("Stayin"), countofdecision.get("Shopping"), dataset.length);
        System.out.println("Decision Gini "+OutputGini);
    }

    
    

    public double getginiIndex(String val1,String val2,Integer index){
    final HashMap<String,Integer> countofMoney = new HashMap<>();
    final HashMap<String,Integer> countofRichMoneyDecision = new HashMap<>();
    final HashMap<String,Integer> countofPoorMoneyDecision = new HashMap<>();
    
        createhashpairMoney(countofMoney, val1, val2);
        createhashpairs(countofRichMoneyDecision, "Cinema", "Tennis", "Stayin","Shopping");
        createhashpairs(countofPoorMoneyDecision, "Cinema", "Tennis", "Stayin","Shopping");
    
    
    
        for(int i=0;i<dataset.length;i++){
            if(dataset[i][index].equals(val1)){
                for(String key:countofRichMoneyDecision.keySet()){
                    if(dataset[i][3]==key){
                        int value = countofRichMoneyDecision.get(key);
                        countofRichMoneyDecision.put(key,value+1);
                    }
                }
            }else if(dataset[i][index].equals(val2)){
                for(String key:countofPoorMoneyDecision.keySet()){
                    if(dataset[i][3]==key){
                        int value = countofPoorMoneyDecision.get(key);
                        countofPoorMoneyDecision.put(key,value+1);
                    }
                }
            }
        }
        double totalPoorDecision = countofPoorMoneyDecision.get("Cinema")+countofPoorMoneyDecision.get("Tennis")+countofPoorMoneyDecision.get("Stayin")+countofPoorMoneyDecision.get("Shopping");
        double poorGini = calculategini(countofPoorMoneyDecision.get("Cinema"), countofPoorMoneyDecision.get("Tennis"), countofPoorMoneyDecision.get("Stayin"), countofPoorMoneyDecision.get("Shopping"),totalPoorDecision);
        

        double totalRichDecision = countofRichMoneyDecision.get("Cinema")+countofRichMoneyDecision.get("Tennis")+countofRichMoneyDecision.get("Stayin")+countofRichMoneyDecision.get("Shopping");
        double richGini = calculategini(countofRichMoneyDecision.get("Cinema"), countofRichMoneyDecision.get("Tennis"), countofRichMoneyDecision.get("Stayin"), countofRichMoneyDecision.get("Shopping"),totalRichDecision);
        
        double getgini;
        getgini=weighted2Avg(poorGini,totalPoorDecision/(totalPoorDecision+totalRichDecision), richGini, totalRichDecision/(totalPoorDecision+totalRichDecision));
        return getgini;
    
    }


    public double getginiIndexSecond(String val1,String val2,Integer index,String gini1right,Integer gini1index){
        final HashMap<String,Integer> countofMoney = new HashMap<>();
        final HashMap<String,Integer> countofRichMoneyDecision = new HashMap<>();
        final HashMap<String,Integer> countofPoorMoneyDecision = new HashMap<>();
        
            createhashpairMoney(countofMoney, val1, val2);
            createhashpairs(countofRichMoneyDecision, "Cinema", "Tennis", "Stayin","Shopping");
            createhashpairs(countofPoorMoneyDecision, "Cinema", "Tennis", "Stayin","Shopping");
        
        
        
            for(int i=0;i<dataset.length;i++){
                if(dataset[i][index].equals(val1)&&dataset[i][gini1index].equals(gini1right)){
                    for(String key:countofRichMoneyDecision.keySet()){
                        if(dataset[i][3]==key){
                            int value = countofRichMoneyDecision.get(key);
                            countofRichMoneyDecision.put(key,value+1);
                        }
                    }
                }else if(dataset[i][index].equals(val2)&&dataset[i][gini1index].equals(gini1right)){
                    for(String key:countofPoorMoneyDecision.keySet()){
                        if(dataset[i][3]==key){
                            int value = countofPoorMoneyDecision.get(key);
                            countofPoorMoneyDecision.put(key,value+1);
                        }
                    }
                }
            }
            double totalPoorDecision = countofPoorMoneyDecision.get("Cinema")+countofPoorMoneyDecision.get("Tennis")+countofPoorMoneyDecision.get("Stayin")+countofPoorMoneyDecision.get("Shopping");
            double poorGini = calculategini(countofPoorMoneyDecision.get("Cinema"), countofPoorMoneyDecision.get("Tennis"), countofPoorMoneyDecision.get("Stayin"), countofPoorMoneyDecision.get("Shopping"),totalPoorDecision);
            
    
            double totalRichDecision = countofRichMoneyDecision.get("Cinema")+countofRichMoneyDecision.get("Tennis")+countofRichMoneyDecision.get("Stayin")+countofRichMoneyDecision.get("Shopping");
            double richGini = calculategini(countofRichMoneyDecision.get("Cinema"), countofRichMoneyDecision.get("Tennis"), countofRichMoneyDecision.get("Stayin"), countofRichMoneyDecision.get("Shopping"),totalRichDecision);
            
            double getgini;
            getgini=weighted2Avg(poorGini,totalPoorDecision/(totalPoorDecision+totalRichDecision), richGini, totalRichDecision/(totalPoorDecision+totalRichDecision));
            return getgini;
        
        }






    private void createhashpairs(HashMap<String,Integer> hashpair,String val1,String val2,String val3,String val4){
        hashpair.put(val1, 0);
        hashpair.put(val2, 0);
        hashpair.put(val3, 0);
        hashpair.put(val4, 0);
    }

    private void createhashpairMoney(HashMap<String,Integer> hashpair,String val1,String val2){
        hashpair.put(val1, 0);
        hashpair.put(val2, 0);
    
    }

    
    
    
    
}
