package DecisionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.validation.Validator;

import java.util.List;


public class mainApp {
    public static void main(String[] args) {
        Map<String,String> data1 = new HashMap<>();
        data1.put("age","old");
        data1.put("income","high");
        data1.put("credit","good");
        data1.put("approved","yes");

        Map<String,String> data2 = new HashMap<>();
        data2.put("age","young");
        data2.put("income","low");
        data2.put("credit","bad");
        data2.put("approved","no");

        Map<String,String> data3 = new HashMap<>();
        data1.put("age","young");
        data1.put("income","high");
        data1.put("credit","good");
        data1.put("approved","yes");

        Map<String,String> data4 = new HashMap<>();
        data2.put("age","old");
        data2.put("income","low");
        data2.put("credit","bad");
        data2.put("approved","no");

        Map<String,String> data5 = new HashMap<>();
        data1.put("age","old");
        data1.put("income","high");
        data1.put("credit","good");
        data1.put("approved","yes");
        

        List<Map<String,String>> dataset = Arrays.asList(data1,data2,data3,data4,data5);
        DecisionNode rootNode = buildDecisionTree(dataset);

        Map<String,String> testdata = new HashMap<>();
        data1.put("age","young");
        data1.put("income","low");
        data1.put("credit","good");

        String result = predict(testdata, rootNode);
        System.out.println("Result: "+result);

        
    }    



public static DecisionNode buildDecisionTree(List<Map<String,String>>dataset){
    DecisionNode rootNode = new DecisionNode();

    //Check if all data has same classification
    boolean sameClassification=true;
    String firstClassification = dataset.get(0).get("approved");
    for(Map<String,String>data:dataset){
        if(!data.get("approved").equals(firstClassification)){
            sameClassification=false;
            break;
        }
    }
    if(sameClassification){
        rootNode.setClassification(firstClassification);
        return rootNode;
    }

    //FIND Best Attribute to split data on
    String bestAttribute="";
    double bestGain=0;
    for(String attribute:dataset.get(0).keySet()){
        if(attribute.equals("approved")){
            continue;
        }

        double gain = calculateInformationGain(dataset, bestAttribute);
        if(gain>bestGain){
            bestGain=gain;
            bestAttribute = attribute;
        }
    }
    rootNode.setAttribute(bestAttribute);
    Map<String,List<Map<String,String>>> splitData = splitDataOnAttribute(dataset, bestAttribute);

    
    for(String value:splitData.keySet()){
        DecisionNode childNode = buildDecisionTree(splitData.get(value));
        rootNode.addChild(value, childNode);
    }
    return rootNode;

}

public static double calculateInformationGain(List<Map<String,String>> dataset,String attribute){
    Map<String,Integer> classCounts = new HashMap<>();
    //USED TO ADD COUNT OF APPROVED YES OR NO AND ADD TO CLASSCOUNTS
    for(Map<String,String>data:dataset){
        String classification=data.get("approved");
        if(classCounts.containsKey(classification)){
            classCounts.put(classification,classCounts.get(classification)+1);
        }else{
            classCounts.put(classification,1);
        }
    }
    double entropyS = calculateEnropy(classCounts);

    Map<String,List<Map<String,String>>> splitData = splitDataOnAttribute(dataset, attribute);
    double remainder=0;
    for(String value:splitData.keySet()){
        Map<String,Integer> valueCounts = new HashMap<>();
        for(Map<String,String> data:splitData.get(value)){
            String classification = data.get("approved");
            if(valueCounts.containsKey(classification)){
                valueCounts.put(classification,valueCounts.get(classification)+1);
            }else{
                valueCounts.put(classification,1);
            }
        }
        double valueEntropy = calculateEnropy(valueCounts);
        double valueWeight = (double) splitData.get(value).size()/dataset.size();
        remainder += valueWeight*valueEntropy;
    }



    return entropyS-remainder;
}

public static double calculateEnropy(Map<String,Integer> classCounts){
    double entropy=0;
    int total=0;
    for(String classification:classCounts.keySet()){
        total+=classCounts.get(classification);
    }
    for(String classification:classCounts.keySet()){
        double probability = (double) classCounts.get(classification)/total;
        entropy -= probability*Math.log(probability)/Math.log(2);
    }
    return entropy;
}

public static Map<String,List<Map<String,String>>> splitDataOnAttribute(List<Map<String,String>> dataset,String attribute){
    Map<String,List<Map<String,String>>> splitData = new HashMap<>();
    for(Map<String,String>data:dataset){
        String value = data.get(attribute);
        if(!splitData.containsKey(value)){
            splitData.put(value,new ArrayList<>());
        }
        splitData.get(value).add(data);
    }
    return splitData;

    
}

public static String predict(Map<String,String> testData,DecisionNode rootNode){
    while(!rootNode.isLeaf()){
        String attribute = rootNode.getAttribute();
        String value = testData.get(attribute);
        rootNode = rootNode.getChild(value);
    }
    return rootNode.getClassification();
}
}