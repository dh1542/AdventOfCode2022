import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class main {
    public static void main(String args[]){
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader("/opt/AdventOfCode2022/Day1/src/input"));
            String line = reader.readLine();


            int currentReindeer = 1;
            int reindeerLeader = 0;
            long currentSum = 0;
            long highestSum = 0;

            PriorityQueue<Long> mostCalories = new PriorityQueue<>((x,y) -> Long.compare(y, x));


            while(line != null){
                if(line.length() != 0){

                    currentSum += Long.parseLong(line);

                }
                else{
//                    System.out.println("current [" + currentReindeer + "]" + ": " + currentSum);
//                    System.out.println("highest [" + reindeerLeader + "]" + ": "+ highestSum);

                    if(currentSum > highestSum){
                        highestSum = currentSum;

                        reindeerLeader = currentReindeer;

                    }

                    mostCalories.add(currentSum);
                    currentSum = 0;
                    currentReindeer++;

                }

                line = reader.readLine();
            }

            System.out.println(reindeerLeader + " with sum " + highestSum);

            System.out.println(mostCalories);
            long top3Combined = 0;
            // get top 3
            for(int i = 0; i < 3; i++){
                top3Combined += mostCalories.remove();
            }
            System.out.println("top 3 combined: " + top3Combined);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
