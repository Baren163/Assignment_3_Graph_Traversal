package nz.ac.auckland.se281;

/** You cannot modify this class!. */
public enum MessageCli {
  COMMAND_NOT_FOUND(
      "Error! Command not found! (run 'help' for the list of available commands): \"%s\""),
  WRONG_ARGUMENT_COUNT(
      "Error! Incorrect number of arguments provided. Expected %s argument%s for the \"%s\""
          + " command"),
  NO_COMMAND("Error! You did not give any command :)"),
  END("You closed the terminal. Goodbye."),

  // TASK 1
  INSERT_COUNTRY("Insert the name of the country: "),
  INVALID_COUNTRY("ERROR! This country was not found: %s, try again!"),
  COUNTRY_INFO("%s => continent: %s, tax fees: %s"),
  // TASK 2

  INSERT_SOURCE("Insert the name of the country where you start the journey: "),
  INSERT_DESTINATION("Insert the name of the country of destination: "),
  NO_CROSSBORDER_TRAVEL("No cross-border travel is required!"),
  ROUTE_INFO("The fastest route is: %s"),
  CONTINENT_INFO("You will visit the following countries: %s"),
  TAX_INFO("You will spend this amount %s for cross-border taxes");

  private final String msg;

  private MessageCli(final String msg) {
    this.msg = msg;
  }

  /**
   * Generates a formatted message using the provided arguments.
   *
   * @param args The arguments to replace placeholders in the message.
   * @return The formatted message.
   */
  public String getMessage(final String... args) {
    String tmpMessage = msg;

    for (final String arg : args) {
      tmpMessage = tmpMessage.replaceFirst("%s", arg);
    }

    return tmpMessage;
  }

  public void printMessage(final String... args) {
    System.out.println(getMessage(args));
  }

  @Override
  public String toString() {
    return msg;
  }
}
