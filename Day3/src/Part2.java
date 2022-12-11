import java.io.BufferedReader;
import java.io.FileReader;

public class Part2 {
    public static void main(String[] args) {
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader("/opt/AdventOfCode2022/Day3/src/realInput"));
            String line = reader.readLine();

            int sumOfPriorities = 0;

            int groupCounter = 0;
            String rucksack1 = "";
            String rucksack2 = "";
            String rucksack3 = "";
            while(line != null){


                switch (groupCounter){
                    case 0:
                        rucksack1 = line; break;
                    case 1:
                        rucksack2 = line; break;
                    case 2:
                        rucksack3 = line; break;
                }

                if(groupCounter == 2){
                    for(int i = 0; i < rucksack1.length(); i++){
                        char currentItemType = rucksack1.charAt(i);
                        if(rucksack2.indexOf(currentItemType) != -1 && rucksack3.indexOf(currentItemType) != -1){
                            int asciiValue = currentItemType;
                            if(Character.isLowerCase(currentItemType)){
                                sumOfPriorities += asciiValue - 96;
                            }
                            else if(Character.isUpperCase(currentItemType)){
                                sumOfPriorities += asciiValue - 38;
                            }
                            break;
                        }
                    }

                    groupCounter = 0;

                }
                else{
                    groupCounter++;
                }



                line = reader.readLine();
            }



            System.out.println("Sum of priorities: " + sumOfPriorities);



        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
