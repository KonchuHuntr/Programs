import java.util.Set;
import java.util.TreeSet;

//basic node being made with LL for connections
public class Node implements Comparable<Node>{
   private int NodeID, parentID = -1;
   private Set<Node> connection;
   public boolean visited = false;
   
   public int getParentID(){
      return parentID;
   }
   
   public Node(int NodeID){
      this.NodeID = NodeID;
      connection = new TreeSet<Node>();
   }
   
   public void addConnection(Node newNodeID){
      connection.add(newNodeID);
   }
   
   public Set<Node> getConnections(){
      return connection;
   }
   
   public int getID(){
      return NodeID;
   }
   
   public void visit(int pID){
      visited = true;
      parentID = pID;
   }
   
   public void reset(){
      visited = false;
   }
   
   public int compareTo(Node x){
      return this.NodeID-x.getID();
   }
}