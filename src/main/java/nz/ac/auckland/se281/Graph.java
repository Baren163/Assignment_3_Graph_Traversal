
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

  public void showInfo() throws InvalidCountryException {
    
    Node country = null;

    while (country == null) {
      
    

    MessageCli.INSERT_COUNTRY.printMessage();

    String input = Utils.scanner.nextLine();

    // COUNTRY_INFO("%s => continent: %s, tax fees: %s")

    for (Map.Entry<Node, List<Node>> entry : this.getMap().entrySet()) {

      if (entry.getKey().getName().equals(input)) {
        country = entry.getKey();
        break;
      }
      
    }

    if (country == null) {
      throw new InvalidCountryException(input);
    }

    }
    
    MessageCli.COUNTRY_INFO.printMessage(country.getName(), country.getContinent(), String.valueOf(country.getTax()));

  }

}