import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RadixSort {

    public static void main(String[] args) throws IOException {
        ArrayList<Integer>Array= new ArrayList<>();

        try {
            File file = new File("input.txt");
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                Integer data=scanner.nextInt();
                Array.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.getStackTrace();
        }

        //Print unsorted Array
        printUnSort(Array);
        System.out.println();

        //Find largest data in array
        int largestNo=largestNum(Array);

        //Count the digit of the largest number
        int numberDigit=countDigit(largestNo);

        ArrayList<Integer>[]bucket=new ArrayList[10];

        //Create bucket
        for (int i = 0; i <bucket.length ; i++) {
            bucket[i]=new ArrayList<>();
        }

        //Exponent to jump between digit in the number
        int expo=1;
        //Loop the amount of time of digit
        while(numberDigit>0){
            for (int i = 0; i <Array.size() ; i++) {
                bucket[(Array.get(i)/expo)%10].add(Array.get(i));//Sort by digit then put into respective bucket
            }

            int j=0;
            for (int k = 0; k <bucket.length ; k++) {
                for (Integer i:bucket[k] ){  //Remove the number after putting into respective bucket
                    Array.set(j++, i);
                }
                bucket[k].clear();
            }
            numberDigit--;
            expo*=10; //jump to next digit
        }

        //Print
        printUnSort(Array);
        writeOutput(Array);
    }

    //Write to file method
    private static void writeOutput(ArrayList<Integer> unSortArray) throws IOException {
        FileWriter writer=new FileWriter("output.txt");
        for (int i = 0; i <unSortArray.size() ; i++) {
            writer.write(unSortArray.get(i)+"\n");
        }
        writer.close();
    }


    //Print method
    private static void printUnSort(ArrayList<Integer> myArray) {
        for(int i=0;i<myArray.size();i++){
            System.out.print(myArray.get(i)+" | ");
        }
    }

    //Find the number of digit in the largest number to loop that many times
    private static int countDigit(int largestNo) {
        int count=0;
        while(largestNo>0){
            largestNo=largestNo/10;
            count++;
        }
        return count;
    }

    //Find the largest Number in ArrayList
    static int largestNum(ArrayList myArray){
        int largestNo= (int) myArray.get(0);
        for(int i=1;i<myArray.size();i++){
            if(largestNo<(int)myArray.get(i)){
                largestNo=(int)myArray.get(i);
            }
        }
        return largestNo;
    }

}
