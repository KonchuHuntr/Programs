import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Set;
import java.util.Collections;
import java.util.Arrays;

public class Graph{
   private String filename;
   private TreeSet<Node>[] graph;
   private TreeSet<Node> masterGraph;
   private int targetID = 0, size = 0;
   private Node Target = null;
   
   public Graph(String filename, int targetID){
      this.filename = filename; // take in designated file
      this.targetID = targetID; // target for algorithm
      graph = new TreeSet[10]; // main array
      for (int i = 0; i < 10; i++) {// creation of 10 bucket set
         graph[i] = new TreeSet<>();
      }
      masterGraph = new TreeSet<Node>();
      graphCreation();
      
      
   }
   
   public TreeSet<Node> getMasterGraph(){
      return masterGraph;
   }
   
   public String getParentArray(){
      String pArray = "Parent Array:[";
      for(Node n : masterGraph){
         if(n.getParentID() <0)
            pArray+= "null, ";
         else
            pArray+=n.getParentID()+", ";
      }
      pArray = pArray.substring(0, pArray.length() - 2);
      pArray+="]\n";
      return pArray;
   }
   
   public int getSize(){
      return size;
   }
   
   public Node getTargetNode(){
      if(Target!= null)
         return Target;
      else
         return null;
   }
   /*
   private Node findNode(int ID){
      Node[] currentGraph = graph.toArray();
      Node foundN = null;
      int arrayLen = currentGraph.length;
      if(arrayLen<100){
         for(Node n : currentGraph){
            if(n.getID() = ID){
               foundN = n;
            }
         }
      }
      else{
         foundN = recursiveFind(currentGraph, ID);
      }
      return foundN;
   }*/
   
   private Node recursiveFind(Object[] graph, int ID, int left, int right) {
      //System.out.println(left +" | "+right);
      
      if(right-left == 1){
         if(graph[0] instanceof Node)
            return (Node)graph[0];
      }
      else if (right - left < 100) { 
         for (int i = left; i <= right; i++) {
            //System.out.println("Index: "+i);
            if(graph[i] instanceof Node){
               if (((Node)graph[i]).getID() == ID) {
                  return (Node)graph[i];
               }
            }
         }
         return null; 
      }
   
      int mid = (left + right) / 2;
      if(graph[mid] instanceof Node){
         if (((Node)graph[mid]).getID() < ID) {
            return recursiveFind(graph, ID, mid + 1, right);
         } else {
            return recursiveFind(graph, ID, left, mid);
         }
      }
      return null;
   }
   
   private void graphCreation(){
      try{
         File database = new File(filename);//file reader
         Scanner scan = new Scanner(database);
         String splitCondition = "[\\s]";
         boolean found = false;
         int n1ID = 0, n2ID = 0, n1IDLB = 0, n2IDLB = 0;
         while(scan.hasNextLine()){//run through each line
            String data = scan.nextLine();
            String[] edge = data.split(splitCondition);// split each line between each 
            n1ID = Integer.parseInt(edge[0]);
            n2ID = Integer.parseInt(edge[1]);
            n1IDLB = n1ID%10;
            n2IDLB = n2ID%10;
            
            Node node1 = new Node(n1ID);
            Node node2 = new Node(n2ID); 
            
            if(graph[n1IDLB].contains(node1)){
               node1 = recursiveFind(graph[n1IDLB].toArray(),node1.getID(),0,graph[n1IDLB].size());
            }
            else
               graph[n1IDLB].add(node1);
               
            if(graph[n2IDLB].contains(node2)){
               node2 = recursiveFind(graph[n2IDLB].toArray(),node1.getID(),0,graph[n2IDLB].size());
            }
            else
               graph[n2IDLB].add(node2);
                 
            node1.addConnection(node2);
            node2.addConnection(node1);
            
            if(!found){
               if(n1ID == targetID){
                  Target  = node1;
                  Target.visit(-1);
                  found = true;
               }
               else if(n2ID == targetID){
                  Target = node2;
                  Target.visit(-1);
                  found = true;
               }
            }         
         }
          // master graph creation
         for(int y =0; y<10; y++ ){
            size+=graph[y].size();
            for(Node n : graph[y]){
               masterGraph.add(n);
            }
         }
         
         
         System.out.println("Completed Graph!\n");
      }
      catch (FileNotFoundException e){
         System.out.println("File Reading Error");
         e.printStackTrace();
      }
   }

}