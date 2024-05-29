package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Graph {

  private Map<Node, List<Node>> nodeMap;

  public Graph() {
    this.nodeMap = new HashMap<>();
  }

  public void addNode(Node country, List<Node> adjCountries) {
    nodeMap.putIfAbsent(country, adjCountries);
  }

  public Map<Node, List<Node>> getMap() {
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

    MessageCli.COUNTRY_INFO.printMessage(
        country.getName(), country.getContinent(), String.valueOf(country.getTax()));
  }

  public List<Node> findRoute(Node root, Node destination) {

    List<Node> visited = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();

    Map<Node, Node> mapPath = new HashMap<>();

    LinkedList<String> continentsVisited = new LinkedList<>();

    int tax = 0;

    queue.add(root);
    visited.add(root);

    mapPath.put(root, null);

    while (!queue.isEmpty()) {

      Node node = queue.poll();

      for (Node n : this.nodeMap.get(node)) {

        if (!visited.contains(n)) {

          visited.add(n);
          queue.add(n);

          mapPath.put(n, node);
        }

        if (n.equals(destination)) {
          // Path detected, reconstruct the path
          List<Node> thePath = new ArrayList<>();

          thePath.add(n);
          continentsVisited.add(n.getContinent());

          Node parentOf = mapPath.get(n);

          while (parentOf != null) {

            thePath.add(parentOf);

            tax += n.getTax();

            n = parentOf;

            if (!continentsVisited.contains(n.getContinent())) {
              continentsVisited.add(n.getContinent());
            }

            parentOf = mapPath.get(n);
          }

          List<Node> route = thePath;

          Collections.reverse(route);
          Collections.reverse(continentsVisited);

          MessageCli.ROUTE_INFO.printMessage(String.valueOf(route));
          MessageCli.CONTINENT_INFO.printMessage(String.valueOf(continentsVisited));
          MessageCli.TAX_INFO.printMessage(String.valueOf(tax));

          return thePath;
        }
      }
    }

    return visited;
  }
}
