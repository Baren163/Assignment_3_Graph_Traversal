package nz.ac.auckland.se281;

public class InvalidCountryException extends Exception {

  public InvalidCountryException(String input) {
    super(MessageCli.INVALID_COUNTRY.getMessage(input));
  }

  @Override
  public String toString() {
    return getMessage();
  }


}