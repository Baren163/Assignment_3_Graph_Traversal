package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Main.Command.*;
import static nz.ac.auckland.se281.MessageCli.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
  MainTest.Task1.class, //
  MainTest.Task2.class
})
public class MainTest {

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class Task1 extends CliTest {

    public Task1() {
      super(Main.class);
    }

    @Test
    public void T1_01_info_india() throws Exception {
      runCommands(INFO_COUNTRY, "India");
      assertContains(COUNTRY_INFO.getMessage("India", "Asia", "3"));
    }

    @Test
    public void T1_02_info_wrong_input_brazil() throws Exception {
      runCommands(INFO_COUNTRY, "hello", "Brazil");
      assertContains(INVALID_COUNTRY.getMessage("Hello"));
      assertContains(COUNTRY_INFO.getMessage("Brazil", "South America", "2"));
    }

    @Test
    public void T1_03_info_wrong_input_irkutsk() throws Exception {
      runCommands(INFO_COUNTRY, "irkuTsK", "irkutsk");
      assertDoesNotContain(INVALID_COUNTRY.getMessage("irkuTsK"));
      assertContains(INVALID_COUNTRY.getMessage("IrkuTsK"));
      assertContains(COUNTRY_INFO.getMessage("Irkutsk", "Asia", "4"));
    }

    @Test
    public void T1_04_info_wrong_input_alaska() throws Exception {
      runCommands(INFO_COUNTRY, "h", "w", "j", "Alaska");
      assertContains(INVALID_COUNTRY.getMessage("H"));
      assertContains(INVALID_COUNTRY.getMessage("W"));
      assertContains(INVALID_COUNTRY.getMessage("J"));
      assertContains(COUNTRY_INFO.getMessage("Alaska", "North America", "1"));
    }

    @Test
    public void T1_05_info_Congo_Siam() throws Exception {
      runCommands(INFO_COUNTRY, "Congo", INFO_COUNTRY, "Siam");
      assertContains(COUNTRY_INFO.getMessage("Congo", "Africa", "1"));
      assertContains(COUNTRY_INFO.getMessage("Siam", "Asia", "9"));
    }

    @Test
    public void T1_06_info_Congo_Siam() throws Exception {
      runCommands(INFO_COUNTRY, "New Guinea", INFO_COUNTRY, "Siam", INFO_COUNTRY, "Madagascar");
      assertContains(COUNTRY_INFO.getMessage("New Guinea", "Australia", "2"));
      assertContains(COUNTRY_INFO.getMessage("Siam", "Asia", "9"));
      assertContains(COUNTRY_INFO.getMessage("Madagascar", "Africa", "4"));
    }
  }

  @FixMethodOrder(MethodSorters.NAME_ASCENDING)
  public static class Task2 extends CliTest {

    public Task2() {
      super(Main.class);
    }

    @Test
    public void T2_01_route_correct_input() throws Exception {
      runCommands(ROUTE, "India", "Siam");
      assertContains(ROUTE_INFO.getMessage("[India, Siam]"));
    }

    @Test
    public void T2_02_route_incorrect_input() throws Exception {
      runCommands(ROUTE, "inDiA", "India", "Siam");
      assertContains(INVALID_COUNTRY.getMessage("InDiA"));
      assertContains(ROUTE_INFO.getMessage("[India, Siam]"));
    }

    @Test
    public void T2_03_route_incorrect_both_input() throws Exception {
      runCommands(ROUTE, "inDiA", "India", "hello", "world", "Siam");
      assertContains(INVALID_COUNTRY.getMessage("InDiA"));
      assertContains(INVALID_COUNTRY.getMessage("Hello"));
      assertContains(INVALID_COUNTRY.getMessage("World"));
      assertContains(ROUTE_INFO.getMessage("[India, Siam]"));
    }

    @Test
    public void T2_04_route_lenght_3() throws Exception {
      runCommands(ROUTE, "Congo", "Egypt");
      assertContains(ROUTE_INFO.getMessage("[Congo, North Africa, Egypt]"));
    }

    @Test
    public void T2_05_route_lenght_4() throws Exception {
      runCommands(ROUTE, "argentina", "congo");
      assertContains(ROUTE_INFO.getMessage("[Argentina, Brazil, North Africa, Congo]"));
    }

    @Test
    public void T2_06_route_lenght_4_reverse() throws Exception {
      runCommands(ROUTE, "congo", "argentina");
      assertContains(ROUTE_INFO.getMessage("[Congo, North Africa, Brazil, Argentina]"));
    }

    @Test
    public void T2_07_route_very_long() throws Exception {
      runCommands(ROUTE, "Eastern Australia", "Great Britain");
      assertContains(
          ROUTE_INFO.getMessage(
              "[Eastern Australia, New Guinea, Indonesia, Siam, India, Middle East, Ukraine,"
                  + " Scandinavia, Great Britain]"));
    }

    @Test
    public void T2_08_info_continent() throws Exception {
      runCommands(ROUTE, "Great Britain", "Eastern Australia");
      assertContains(
          ROUTE_INFO.getMessage(
              "[Great Britain, Scandinavia, Ukraine, Ural, China, Siam, Indonesia, New Guinea,"
                  + " Eastern Australia]"));
      assertContains(CONTINENT_INFO.getMessage("[Europe, Asia, Australia]"));
    }

    @Test
    public void T2_09_info_continent() throws Exception {
      runCommands(ROUTE, "Ural", "Japan");
      assertContains(ROUTE_INFO.getMessage("[Ural, Siberia, Mongolia, Japan]"));
      assertContains(CONTINENT_INFO.getMessage("[Asia]"));
    }

    @Test
    public void T2_10_info_continent() throws Exception {
      runCommands(ROUTE, "North Africa", "Kamchatka");
      assertContains(
          ROUTE_INFO.getMessage(
              "[North Africa, Southern Europe, Ukraine, Ural, Siberia, Yakutsk, Kamchatka]"));
      assertContains(CONTINENT_INFO.getMessage("[Africa, Europe, Asia]"));
    }

    @Test
    public void T2_11_info_taxes() throws Exception {
      runCommands(ROUTE, "Japan", "Mongolia");
      assertContains(ROUTE_INFO.getMessage("[Japan, Mongolia]"));
      assertContains(TAX_INFO.getMessage("8"));
    }

    @Test
    public void T2_12_info_taxes() throws Exception {
      runCommands(ROUTE, "Ural", "Venezuela");
      assertContains(
          ROUTE_INFO.getMessage(
              "[Ural, Ukraine, Southern Europe, North Africa, Brazil, Venezuela]"));
      assertContains(CONTINENT_INFO.getMessage("[Asia, Europe, Africa, South America]"));
      assertContains(TAX_INFO.getMessage("21"));
    }

    @Test
    public void T2_13_no_crossborder() throws Exception {
      runCommands(ROUTE, "Alberta", "Alberta");
      assertContains(NO_CROSSBORDER_TRAVEL.getMessage());
      assertDoesNotContain("The fastest route is: ");
    }

    @Test
    public void T2_14_no_crossborder() throws Exception {
      runCommands(ROUTE, "Egypt", "Egypt");
      assertContains(NO_CROSSBORDER_TRAVEL.getMessage());
      assertDoesNotContain("You will spend this amount ");
    }
  }
}
