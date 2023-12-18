package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.PlannedEvent;
import cs3500.pa05.model.PlannedItem;
import cs3500.pa05.model.PlannedTask;
import cs3500.pa05.model.WeekDay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the Json record for an Item
 */
class ItemJsonTest {
  private ItemJson eventItemJson;
  private ItemJson taskItemJson;

  /**
   * Create two ItemJsons, one of type EVENT and one of type TASK
   */
  @BeforeEach
  void createItems() {
    EventJson eventJson = new EventJson("12:30", 1.0);
    JsonNode eventNode = JsonUtils.serializeRecord(eventJson);
    this.eventItemJson = new ItemJson("event", "description", WeekDay.SUNDAY,
        new ItemCategory("category"), ItemType.EVENT, eventNode);
    TaskJson taskJson = new TaskJson(false);
    JsonNode taskNode = JsonUtils.serializeRecord(taskJson);
    this.taskItemJson = new ItemJson("task", "description", WeekDay.MONDAY,
        new ItemCategory("category"), ItemType.TASK, taskNode);
  }

  /**
   * Test that the data is transferred when converting to a PlannedItem
   */
  @Test
  void testItemsJson() {
    PlannedItem event = this.eventItemJson.toItem();
    PlannedItem task = this.taskItemJson.toItem();
    assertEquals("event" + ":" + System.lineSeparator()
        + "Category: category" + System.lineSeparator()
        + "At 12:30  (1.0 hours)" + System.lineSeparator()
        + "description", event.toString());
    assertEquals("task" + ":" + System.lineSeparator()
        + "Category: category" + System.lineSeparator()
        + "description" + System.lineSeparator()
        + "Incomplete", task.toString());
  }

  /**
   * Test an invalid event being created and that the item it produces is null
   */
  @Test
  void testInvalidEvent() {
    ItemJson invalid = new ItemJson("", "", WeekDay.SUNDAY,
        new ItemCategory(""), null, this.eventItemJson.args());
    assertNull(invalid.toItem());
  }

  /**
   * Test converting a PlannedEvent and a PlannedTask to Json
   * Both create a ItemJson with different type and args
   */
  @Test
  void testToJsonRecord() {
    PlannedItem event = new PlannedEvent("Bday Party", "August's birthday party test",
        WeekDay.MONDAY, "12:30", 1.0);
    PlannedItem task = new PlannedTask("Finish OOD HW", "Gotta write some tests for PA05 test",
        WeekDay.SUNDAY);
    ItemJson eventJson = event.toJsonRecord();
    final ItemJson taskJson = task.toJsonRecord();
    assertEquals("Bday Party", eventJson.name());
    assertEquals("August's birthday party test", eventJson.description());
    assertEquals(WeekDay.MONDAY, eventJson.day());
    assertEquals("Finish OOD HW", taskJson.name());
    assertEquals("Gotta write some tests for PA05 test", taskJson.description());
    assertEquals(WeekDay.SUNDAY, taskJson.day());
  }
}
