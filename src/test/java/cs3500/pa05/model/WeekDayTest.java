package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test the WeekDay enum
 */
class WeekDayTest {
  /**
   * Test converting to String
   */
  @Test
  void testGetDayAs() {
    assertEquals("Sunday", WeekDay.getDayAsString(WeekDay.SUNDAY));
    assertEquals("Monday", WeekDay.getDayAsString(WeekDay.MONDAY));
    assertEquals("Tuesday", WeekDay.getDayAsString(WeekDay.TUESDAY));
    assertEquals("Wednesday", WeekDay.getDayAsString(WeekDay.WEDNESDAY));
    assertEquals("Thursday", WeekDay.getDayAsString(WeekDay.THURSDAY));
    assertEquals("Friday", WeekDay.getDayAsString(WeekDay.FRIDAY));
    assertEquals("Saturday", WeekDay.getDayAsString(WeekDay.SATURDAY));
  }

  /**
   * Test reading from string and throwing error on invalid parameter
   */
  @Test
  void testGetDayFrom() {
    assertEquals(WeekDay.SUNDAY, WeekDay.getDayFromString("Sunday"));
    assertEquals(WeekDay.MONDAY, WeekDay.getDayFromString("monday"));
    assertEquals(WeekDay.TUESDAY, WeekDay.getDayFromString("TUESDAY"));
    assertEquals(WeekDay.WEDNESDAY, WeekDay.getDayFromString("wEdnesday"));
    assertEquals(WeekDay.THURSDAY, WeekDay.getDayFromString("thurSDAY"));
    assertEquals(WeekDay.FRIDAY, WeekDay.getDayFromString("fRiDaY"));
    assertEquals(WeekDay.SATURDAY, WeekDay.getDayFromString("sATURDAy"));
    assertThrows(IllegalArgumentException.class,
        () -> WeekDay.getDayFromString("test"));
  }
}
