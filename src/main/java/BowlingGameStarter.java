import java.util.Scanner;

public class BowlingGameStarter {
        public static int MAX_NO_OF_THROWS=20;
        public static void main(String arr[]){
            Scanner reader = new Scanner(System.in);
            Game m1= new Game();
            for(int i=0;i<MAX_NO_OF_THROWS;i++) {
                System.out.println("Enter the Numbers of pins knocked down");
                int n = reader.nextInt();
                m1.throwBall(n);

            }
        }



}
