package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/** This class is the main entry point. */
public class MapEngine {

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    Set<Node> countryNodes = new HashSet<>();

    Queue<Node> nodeQueue = new LinkedList<>();

    // add code here to create your data structures
    Graph riskGraph = new Graph();

    Node nodeToAdd;

    for (int i = 0; i < countries.size(); i++) {
   
      String[] countryParts = countries.get(i).split(",");

      nodeToAdd = new Node(countryParts[0], countryParts[1], Integer.valueOf(countryParts[2]));
      countryNodes.add(nodeToAdd);
      nodeQueue.add(nodeToAdd);
    }

    for (int i = 0; i < adjacencies.size(); i++) {

      LinkedList<Node> tempList = new LinkedList<>();

      String[] adjacentCountries = adjacencies.get(i).split(",");

      for (int j = 0; j < adjacentCountries.length; j++) {
        
        for (Node node : countryNodes) {
          if (node.getName().equals(adjacentCountries[j])) {
            tempList.add(node);
            break;
          }
        }

      }

      riskGraph.addNode(nodeQueue.remove(), tempList);
    
    }



  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}