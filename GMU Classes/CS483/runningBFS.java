//Starting class made to run and time the algorithm
// use command java runningBFS [input txt] [starting nodeID]
import java.util.Scanner;
import java.io.*;

public class runningBFS{
   public static void main(String[] args){
      long startTime = System.nanoTime();
      Scanner input = new Scanner(System.in);
      //System.out.print("Enter filename: ");
      //String filename = input.nextLine();
      //System.out.print("Enter Target Node: ");
      //int nodeID = input.nextInt();
      //BFS search = new BFS(filename,nodeID);
      BFS search = new BFS(args[0],Integer.parseInt(args[1]));
      long endTime = System.nanoTime();
      long executionTimeNano= (endTime - startTime);
      double executionTimeSeconds = executionTimeNano / 1_000_000_000.0;
      System.out.println("\nThis Program Takes "+ executionTimeNano + "ms aka "+executionTimeSeconds+"s");
   }
}