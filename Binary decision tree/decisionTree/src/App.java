import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String answer;

        TreeNode q1 = new TreeNode("Did the pain occur after blow?");
        TreeNode q2 = new TreeNode("Do you have fever?");
        TreeNode q3 = new TreeNode("Do you have difficulty controlling arms and legs?");
        TreeNode r1 = new TreeNode("You are ok");
        TreeNode r2 = new TreeNode("You have headache");
        TreeNode r3 = new TreeNode("You have strain");
        TreeNode r4 = new TreeNode("You have serious issue");
        BinaryTree binaryt = new BinaryTree();
        binaryt.addRoot(q1);
        binaryt.addLeft(q1, q2);
        binaryt.addRight(q1, q3);

        binaryt.addLeft(q2, r1);
        binaryt.addRight(q2, r2);

        binaryt.addLeft(q3, r3);
        binaryt.addRight(q3, r4);
        
        String rightanswer = "yes";
        String wronganswer = "no";

        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        TreeNode current = binaryt.root;
        while(flag!=1){
            if(current.left==null && current.right==null){
                flag=1;
            }
            System.out.print(current.data);
            answer = scanner.nextLine();
            if(answer.equals(rightanswer)){
                current=current.right;
            }else if(answer.equals(wronganswer)){
                current=current.left;
            }else if(flag!=1){
                System.out.println("Enter yes or no");
            }
            

        }
    }
}
