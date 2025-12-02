/*
(1) Breath First Search (BFS)

a. Input: Undirected graph in edge list format kept in a plain text file where each line
contains two numbers (i.e., source and destination nodes) separated by a white
space. The submitted codes must accept command-line arguments for specifying
input file name and start node.

b. Output: At the end of each iteration, the codes must produce a list of nodes visited in
that iteration and the contents of parent node array. At program completion, a list of
nodes in order of breath first traversal (i.e., one node per line) must be printed out
on standard output.

*/
import java.io.File;  
import java.io.IOException; 
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;


public class BFS{
   Object[] targetQueueCurrent;
   //int[] parentNodes;
   Queue<Node> targetQueue;
   int targetID;
   int iterationLength = 0;
   Graph graph;
   
   public BFS(String filename, int targetID){
      targetQueue = new LinkedList<Node>();
      System.out.println("Analyzing Database");
      this.targetID = targetID;
      graph = new Graph(filename, targetID);// passing the txt and targetID from cmd line
      if(graph.getTargetNode() == null){
         System.out.println("Target node not contained in Database!");
         return;
      } 
      //parentNodes = new int[graph.getSize()];
      targetQueue.add(graph.getTargetNode());// first set is made into direct connection to the target node
      bfsAlgorithm();
   }
   
   private void bfsAlgorithm(){
      File output = new File("output.txt");
      int counterCurrent = 0;
      int counterNext=0;
      int iterationCounter = 0, iterationThresh= 0,counter =0;
      String logging = "Iteration 1: ", visited = "Visited Nodes:[", prevPArray = null, bfsTraversal = "";
      try(FileWriter writer = new FileWriter(output)){
         while(!targetQueue.isEmpty()){
            Node currentN = targetQueue.remove(); // pop the first in line
            //System.out.println(currentN.getID());
            writer.write((""+currentN.getID()+"\n"));
            bfsTraversal += ""+currentN.getID()+"\n";
            visited+= currentN.getID()+", ";
            //logging += (""+currentN.getID()+"\n");
            for(Node n : currentN.getConnections()){
               if(!n.visited){
                  targetQueue.add(n);
                  n.visit(currentN.getID());
                  //parentNodes[n.getID()]
               }
            }
            //System.out.println(counter+" | "+iterationThresh);
            if(counter == iterationThresh ){
               if(iterationCounter == 0 ){
                  System.out.println("Iteration "+iterationCounter+":");
               
                  System.out.println("Starting Node:"+currentN.getID()+"\n");
               
               }
               else{
                  visited = visited.substring(0, visited.length() - 2);
                  visited+="]";
                  System.out.println(logging);
                  System.out.println(visited);
                  System.out.println(prevPArray);
               }
               iterationCounter++;
               prevPArray = graph.getParentArray();
               iterationThresh = targetQueue.size();
               counter = 0;
               logging = "Iteration "+(iterationCounter)+":";
               visited = "Visited Nodes:[";
            }
            counter++;
         
         }
         System.out.print("BFS TRAVERSAL:\n"+bfsTraversal);
      }
      catch(IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }
   
   /*
   private void bfsAlgorithm(){
      File output = new File("output.txt");
      try{
         FileWriter writer = new FileWriter(output);
         String iterationLogging = ""+targetID+"\n";
         while(true){
            targetQueueNext = new LinkedList<>(); 
            for(Object n : targetQueueCurrent){
               if(n instanceof Node){
                  Node m = (Node)n;
                  if(m.visited)// if node has been seen before skip it
                     continue;
                  iterationLogging.concat(""+m.getID()+"\n");//add ID to output
               //writer.write(""+m.getID()+"\n"); // write ID to outputlog
                  m.visit(); // mark node as visited
                  for(Object o : n.getCollections()){// add node's connections to queue
                     if(o instanceof Node){
                        Node p = (Node)o;
                        targetQueueNext.add(p);
                     }
                  }
               }
            }
            System.out.println("Nodes visited:\n"+iterationLogging);
            writer.write(iterationLogging);
         }
      }
      catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }*/ 
}