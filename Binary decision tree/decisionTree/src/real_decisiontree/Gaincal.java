package real_decisiontree;

public class Gaincal {
    double infogain;


    Gaincal(double mainEntrop,double entropy1,double entropy2,double entropy3,double divsv1,double divsv2,double divsv3){
        this.infogain = mainEntrop-divsv1*entropy1-divsv2*entropy2-divsv3*entropy3;

    }

    Gaincal(double mainEntrop,double entropy1,double entropy2,double divsv1,double divsv2){
        this.infogain = mainEntrop-divsv1*entropy1-divsv2*entropy2;

    }
}
