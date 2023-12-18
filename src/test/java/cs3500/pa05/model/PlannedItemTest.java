package cs3500.pa05.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests PlannedItems (both tasks and events)
 */
class PlannedItemTest {
  PlannedEvent event;
  PlannedTask task;

  /**
   * Creates a PlannedEvent and a PlannedTask
   */
  @BeforeEach
  void createItems() {
    this.event = new PlannedEvent("Bday Party", "August's birthday party test",
        WeekDay.MONDAY, "12:30", 1.0);
    this.task = new PlannedTask("Finish OOD HW", "Gotta write some tests for PA05 test",
        WeekDay.SUNDAY);
  }

  /**
   * Check the name getter
   */
  @Test
  void testName() {
    assertEquals("Bday Party", this.event.getName());
    assertEquals("Finish OOD HW", this.task.getName());
  }

  /**
   * Check the description getter
   */
  @Test
  void testDescription() {
    assertEquals("August's birthday party test", this.event.getDescription());
    assertEquals("Gotta write some tests for PA05 test", this.task.getDescription());
  }

  /**
   * Check the day of week getters (string and enum getters)
   */
  @Test
  void testDayOfWeek() {
    assertEquals(WeekDay.getDayAsString(WeekDay.MONDAY), this.event.dayOfWeekAsString());
    assertEquals(WeekDay.getDayAsString(WeekDay.SUNDAY), this.task.dayOfWeekAsString());
    assertEquals(WeekDay.MONDAY, this.event.getWeekDay());
    assertEquals(WeekDay.SUNDAY, this.task.getWeekDay());
  }

  /**
   * Test the event specific getters
   */
  @Test
  void testEvent() {
    assertEquals("12:30", this.event.getStartTime());
    assertEquals(1.0, this.event.getDurationHours());
  }

  /**
   * Test the task specific getters/setters
   */
  @Test
  void testTask() {
    assertFalse(task.isCompleted());
    task.setCompleted(true);
    assertTrue(task.isCompleted());
  }

  /**
   * Tests toString for both
   */
  @Test
  void testToString() {
    assertEquals("Bday Party" + ":" + System.lineSeparator()
        + "At 12:30  (1.0 hours)" + System.lineSeparator()
        + "August's birthday party test", event.toString());
    assertEquals("Finish OOD HW" + ":" + System.lineSeparator()
        + "Gotta write some tests for PA05 test" + System.lineSeparator()
        + "Incomplete", this.task.toString());
  }

  /**
   * Tests creating an item with a manual category
   */
  @Test
  void testManualCategory() {
    PlannedItem item = new PlannedTask("test", "test2", WeekDay.SUNDAY,
        new ItemCategory("test3"));
    ArrayList<PlannedItem> list = new ArrayList<>();
    list.add(item);
    assertEquals("test3", item.getCategory().getCategoryName());
    assertEquals(1, PlannedItem.searchForItemsWithText(list, "test3").size());
  }

  /**
   * Tests an event's timestamp
   */
  @Test
  void testTimeStamp() {
    assertEquals("At" + " " + "12:30"
        + "  (" + 1.0 + " hours)", event.getTimeStamp());
  }

  /**
   * Tests automatically assigning a category in the title with #
   */
  @Test
  void testCategoryShortHand() {
    PlannedItem item = new PlannedTask("#test banana", "banana", WeekDay.SUNDAY);
    assertEquals("test", item.getCategory().getCategoryName());
    assertEquals("banana", item.getName());
  }

  /**
   * Tests searching a list of items with just text
   */
  @Test
  void testSearching() {
    ArrayList<PlannedItem> list = new ArrayList<>();
    list.add(this.event);
    list.add(this.task);
    assertEquals(1, PlannedItem.searchForItemsWithText(list, "Party").size());
    assertEquals(1, PlannedItem.searchForItemsWithText(list, "Gotta").size());
    assertEquals(2, PlannedItem.searchForItemsWithText(list, "test").size());
    assertEquals(0, PlannedItem.searchForItemsWithText(list, "unicorn").size());
  }

  /**
   * Tests an overloaded constructor for a PlannedTask
   */
  @Test
  void testOverloadedTask() {
    PlannedItem task = new PlannedTask("test", "description", WeekDay.SUNDAY, true);
    assertEquals("test" + ":" + System.lineSeparator()
            + "description" + System.lineSeparator()
            + "Complete", task.toString());
    assertNull(task.getCategory());
  }
}
