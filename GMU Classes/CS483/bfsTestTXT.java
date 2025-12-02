import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class bfsTestTXT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user inputs
        System.out.print("Enter total number of nodes: ");
        int totalNodes = scanner.nextInt();

        System.out.print("Enter maximum number of child nodes per parent: ");
        int maxChildren = scanner.nextInt();

        // Name of the output file
        String fileName = "graph.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            int currentNode = 1; // Start from node 1

            for (int parent = 1; parent <= totalNodes; parent++) {
                for (int c = 0; c < maxChildren; c++) {
                    currentNode++;
                    if (currentNode > totalNodes) {
                        break;
                    }
                    writer.write(parent + " " + currentNode + "\n");
                }
            }

            System.out.println("Graph file '" + fileName + "' created successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }

        scanner.close();
    }
}
