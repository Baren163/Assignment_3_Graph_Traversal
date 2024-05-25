package nz.ac.auckland.se281;

public class Node {

  private String countryName;
  private int tax;
  private String continent;

  public Node(String countryName, String continent, int tax) {
    this.countryName = countryName;
    this.tax = tax;
    this.continent = continent;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || this.getClass() != other.getClass()) {
      return false;
    }

    Node otherNode = (Node) other;

    if (this.countryName.equals(otherNode.getName())) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    return this.getName();
  }
 
  public String getName() {
    return this.countryName;
  }
  
  public int getTax() {
    return this.tax;
  }

  public String getContinent() {
    return this.continent;
  }

}
