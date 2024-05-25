package nz.ac.auckland.se281;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


/** This class is the main entry point. */
public class MapEngine {

  Graph riskGraph = new Graph();

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

    Node country = null;

    while (country == null) {
    
      try {

      MessageCli.INSERT_COUNTRY.printMessage();

      String input = Utils.scanner.nextLine();

      input = Utils.capitalizeFirstLetterOfEachWord(input);

      for (Map.Entry<Node, List<Node>> entry : riskGraph.getMap().entrySet()) {

        if (entry.getKey().getName().equals(input)) {
          country = entry.getKey();
          break;
        }
        
      }

      if (country == null) {
        throw new InvalidCountryException(input);
      }

      
      } catch (InvalidCountryException e) {
        System.out.println(e.getMessage());
      }
    }

    MessageCli.COUNTRY_INFO.printMessage(country.getName(), country.getContinent(), String.valueOf(country.getTax()));

  }


  /** this method is invoked when the user run the command route. */
  public void showRoute() {

    List<Node> route;

    Node countrySource = null;

    while (countrySource == null) {
    
      try {

      MessageCli.INSERT_SOURCE.printMessage();

      String input = Utils.scanner.nextLine();

      input = Utils.capitalizeFirstLetterOfEachWord(input);

      for (Map.Entry<Node, List<Node>> entry : riskGraph.getMap().entrySet()) {

        if (entry.getKey().getName().equals(input)) {
          countrySource = entry.getKey();
          break;
        }
        
      }

      if (countrySource == null) {
        throw new InvalidCountryException(input);
      }

      
      } catch (InvalidCountryException e) {
        System.out.println(e.getMessage());
      }
    }


    Node countryDestination = null;

    while (countryDestination == null) {
    
      try {

      MessageCli.INSERT_DESTINATION.printMessage();

      String input = Utils.scanner.nextLine();

      input = Utils.capitalizeFirstLetterOfEachWord(input);

      for (Map.Entry<Node, List<Node>> entry : riskGraph.getMap().entrySet()) {

        if (entry.getKey().getName().equals(input)) {
          countryDestination = entry.getKey();
          break;
        }
        
      }

      if (countryDestination == null) {
        throw new InvalidCountryException(input);
      }

      
      } catch (InvalidCountryException e) {
        System.out.println(e.getMessage());
      }
    }

    if (countrySource.equals(countryDestination)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
    }


    route = riskGraph.breathFirstTraversal(countrySource, countryDestination);





  }
}