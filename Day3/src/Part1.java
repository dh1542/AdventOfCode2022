import java.io.BufferedReader;
import java.io.FileReader;

public class Part1 {
    public static void main(String[] args) {
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader("/opt/AdventOfCode2022/Day3/src/realInput"));
            String line = reader.readLine();

            int sumOfPriorities = 0;
            while(line != null){
                // get both compartments
                String compartment1 = line.substring(0, line.length() / 2);
                String compartment2 = line.substring(line.length() / 2);
                for(int i = 0; i < compartment1.length(); i++){
                    char currentChar = compartment1.charAt(i);
                    // char appears in string
                    if(compartment2.indexOf(currentChar) != -1){

                        int asciiValue = currentChar;
                        if(Character.isLowerCase(currentChar)){
                            sumOfPriorities += asciiValue - 96;
                        }
                        else if(Character.isUpperCase(currentChar)){
                            sumOfPriorities += asciiValue - 38;
                        }
                        break;
                    }
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
