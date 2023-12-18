package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.PlannedWeek;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the Json record for a PlannedWeek
 */
class WeekJsonTest {
  private WeekJson weekJson;

  /**
   * Create a WeekJson to test
   */
  @BeforeEach
  void setupJson() {
    this.weekJson = new WeekJson(10, 10, new ArrayList<ItemJson>(),
        "name", "password");
  }

  /**
   * Test the json property accessor methods
   */
  @Test
  void testWeekJson() {
    assertEquals(10, weekJson.maxEvents());
    assertEquals(10, weekJson.maxTasks());
    assertEquals(0, weekJson.items().size());
    assertEquals("name", weekJson.weekName());
    assertEquals("password", weekJson.password());
  }

  /**
   * Test creating a custom Week and converting to Json record
   */
  @Test
  void testToJsonRecord() {
    PlannedWeek custom = new PlannedWeek(new ArrayList<>(), 5, 5,
        "name", "password");
    WeekJson weekJson = custom.toJsonRecord();
    assertEquals(5, weekJson.maxEvents());
    assertEquals(5, weekJson.maxTasks());
    assertEquals(0, weekJson.items().size());
    assertEquals("name", weekJson.weekName());
    assertEquals("password", weekJson.password());
  }

  /**
   * Test converting a Json record into a PlannedWeek
   */
  @Test
  void testFromJsonRecord() {
    PlannedWeek week = PlannedWeek.fromJsonRecord(this.weekJson);
    assertEquals(10, week.getMaxEvents());
    assertEquals(10, week.getMaxTasks());
    assertEquals(0, week.getAggregatedList().size());
    assertEquals("name", week.getWeekName());
    assertEquals("password", week.getPassword());
  }
}
