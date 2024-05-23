
package nz.ac.auckland.se281;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Graph {

  private Map<Node,List<Node>> nodeMap;
 
  public Graph() {
    this.nodeMap = new HashMap<>();
  }

  public void addNode(Node country, List<Node> adjCountries) {
    nodeMap.putIfAbsent(country, adjCountries);
  }

  public Map<Node,List<Node>> getMap() {
    return this.nodeMap;
  }
  
}