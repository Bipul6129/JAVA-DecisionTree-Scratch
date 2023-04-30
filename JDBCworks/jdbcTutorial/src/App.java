import java.sql.*;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try{
            String url="jdbc:mysql://localhost:3306/mydb";
            String user="root";
            String password="root";
            Connection con = DriverManager.getConnection(url, user, password);

            if(con.isClosed()){
                System.out.println("Con closed");
            }else{
                System.out.println("1:Insert into table");
                System.out.println("2:Select all data");
                System.out.println("YOur choice:");
                Integer choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter name");
                        String name = scanner.nextLine();
                        String query = "insert into trial values(?)";
                        PreparedStatement pstm = con.prepareStatement(query);
                        pstm.setString(1, name);
                        pstm.executeUpdate();
                        break;
                    case 2:
                        String query2 = "select *from trial";
                        Statement stm = con.createStatement();
                        ResultSet results = stm.executeQuery(query2);
                        
                        if(results.next()){
                            System.out.println(results.getString(1));
                        }else{
                            System.out.println(results.getString(1));
                        }

                    default:
                        break;
                }

                
            }

            

        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }

}
