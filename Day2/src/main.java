import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.PriorityQueue;

public class main {
    public static void main(String args[]){
        BufferedReader reader;

        int[][] scoringTable = {
                {3, 1, 2},
                {4, 5, 6},
                {8, 9, 7}
        };


        try{
            reader = new BufferedReader(new FileReader("/opt/AdventOfCode2022/Day2/src/input"));
            String line = reader.readLine();



            int totalScore = 0;
            while(line != null){
                char enemyTurn = line.charAt(0);
                char myTurn = line.charAt(2);

                System.out.println(line);

                int enemyTurnNumber = 99;
                switch (enemyTurn){
                    case 'A': enemyTurnNumber = 0; break;
                    case 'B': enemyTurnNumber = 1; break;
                    case 'C': enemyTurnNumber = 2; break;
                }
                int myTurnNumber = 99;
                switch (myTurn){
                    case 'X': myTurnNumber = 0; break;
                    case 'Y': myTurnNumber = 1; break;
                    case 'Z': myTurnNumber = 2; break;
                }



                totalScore += scoringTable[myTurnNumber][enemyTurnNumber];
                System.out.println(scoringTable[myTurnNumber][enemyTurnNumber]);

               line = reader.readLine();
            }

            System.out.println("total score: " + totalScore);


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
