package studeny_decisiontree;

public class maintest {
    

    public static void main(String[] args) {
        double student_perc = 30;
        String result_status = "pass";
        double student_subm = 0.7;
        double student_pres = 0.3;

        String perc = "percent";
        String subm = "subm"; 
        String pres ="pres";

        TNode qa = new TNode("Is latest percentage greater than 60%", 60,perc);

        TNode qb1 = new TNode("IS submission<=0.5 percent lww", 0.5,subm);
        TNode qb2 = new TNode("IS submission<=0.5 percent grr ", 0.5,subm);

        TNode qc1 = new TNode("Is present<0.5 (perc bad sub good)",0.5,pres);
        TNode qc2 = new TNode("Is present<0.5 (perc bad sub bad)",0.5,pres);
        TNode qc3 = new TNode("Is present<0.5 (perc good sub good)",0.5,pres);
        TNode qc4 = new TNode("Is present<0.5 (perc good sub bad)",0.5,pres);

        TNode e1 = new TNode("Student is regular and has good submission with good studies");
        TNode e2 = new TNode("Student is not regular and has good submission with good studies");
        TNode e3 = new TNode("Student is regular and doesnot have good submission but with good studies");
        TNode e4 = new TNode("Student is not regular and doesnot have good submission but with good studies");
        TNode e5 = new TNode("Student is regular and has good submission with bad studies");
        TNode e6 = new TNode("Student is not regular and has good submission with bad studies");
        TNode e7 = new TNode("Student is regular and has bad submission with bad studies");
        TNode e8 = new TNode("Student is not regular and has bad submission with bad studies");

        BTree btree = new BTree();
        btree.addRoot(qa);

        btree.addRight(qa, qb1);
        btree.addLeft(qa, qb2);

        btree.addLeft(qb1, qc1);
        btree.addRight(qb1, qc2);

        btree.addLeft(qb2, qc3);
        btree.addRight(qb2, qc4);

        //LAST LEAFNODES
        btree.addLeft(qc3,e1);
        btree.addRight(qc3,e2);

        btree.addLeft(qc4,e3);
        btree.addRight(qc4,e4);

        btree.addLeft(qc1,e5);
        btree.addRight(qc1,e6);

        btree.addLeft(qc2,e7);
        btree.addRight(qc2,e8);




        TNode current = btree.root;
        int flag=0;

        while(flag!=1){
            
            if(current.codex.equals(perc)){
                if(result_status.equals("fail")){
                    current=current.right;
                    
                    
                }else if(student_perc<=60){
                    current=current.right;
                    
                    
                }else{
                    current=current.left;
                    
                    
                }
            }else if(current.codex.equals(subm)){
                if(student_subm<=current.acceptablevalue){
                    current=current.right;
                    
                    
                    
                }else{
                    current=current.left;
                    
                    
                }
            }
            else if(current.codex.equals(pres)){
                if(student_pres<=current.acceptablevalue){
                    current=current.right;
                    System.out.println(current.check);
                    flag=1;
                }else{
                    current=current.left;
                    System.out.println(current.check);
                    flag=1;
                    
                }
            }
            
            
        }
        
    }


    
}
