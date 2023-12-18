package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the PlannedWeek
 */
class PlannedWeekTest {
  private PlannedWeek custom;
  private PlannedWeek def;

  /**
   * Create a week with an event and a task in it
   * Create another week with nothing in it (default empty constructor)
   */
  @BeforeEach
  void testWeek() {
    PlannedItem event = new PlannedEvent("Bday Party", "August's birthday party",
        WeekDay.MONDAY, "12:30", 1.0);
    PlannedItem task = new PlannedTask("Finish OOD HW", "Gotta write some tests for PA05",
        WeekDay.SUNDAY);
    ArrayList<PlannedItem> list = new ArrayList<>();
    list.add(event);
    list.add(task);
    this.custom = new PlannedWeek(list, 5, 5,
        "test", "test");
    this.def = new PlannedWeek();
  }

  /**
   * Test the list saves the PlannedItems
   */
  @Test
  void testAggregatedList() {
    assertEquals(2, this.custom.getAggregatedList().size());
    assertEquals(0, this.def.getAggregatedList().size());
  }

  /**
   * Test the maxTask/Event getters and setters
   */
  @Test
  void testMax() {
    assertEquals(1000, this.def.getMaxEvents());
    assertEquals(1000, this.def.getMaxTasks());
    assertEquals(5, this.custom.getMaxTasks());
    assertEquals(5, this.custom.getMaxEvents());
    this.custom.setMaxEvents(6);
    this.custom.setMaxTasks(7);
    assertEquals(7, this.custom.getMaxTasks());
    assertEquals(6, this.custom.getMaxEvents());
  }

  /**
   * Test the name and password getters and setters
   */
  @Test
  void testName() {
    assertEquals("test", this.custom.getWeekName());
    assertEquals("test", this.custom.getPassword());
    assertEquals("", this.def.getPassword());
    assertEquals("Your New Week", this.def.getWeekName());
    this.custom.setWeekName("newName");
    this.custom.setPassword("password");
    assertEquals("newName", this.custom.getWeekName());
    assertEquals("password", this.custom.getPassword());
  }
}
