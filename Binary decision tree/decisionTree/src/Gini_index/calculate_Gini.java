package Gini_index;

public class calculate_Gini {
    
    public double calculategini(double val1,double val2,double val3,double val4,double totallen){
        double gini;
        gini = 1-(Math.pow((val1/totallen), 2)+Math.pow((val2/totallen), 2)+Math.pow((val3/totallen), 2)+Math.pow((val4/totallen), 2));
        return gini;

    }

    public double weighted2Avg(double gini1,double frac1,double gini2,double frac2){
        double weighted;
        weighted = gini1*frac1+gini2*frac2;
        return weighted;
    }

    public double calculatesmallgini(double gini1,double gini2,double gini3){
        double smallgini=gini1;
        if(smallgini>gini2){
            smallgini=gini2;
        }else if(smallgini>gini3){
            smallgini=gini3;
        }
        return smallgini;
    }
    
}
