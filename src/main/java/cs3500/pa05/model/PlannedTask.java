package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.ItemJson;
import cs3500.pa05.model.json.ItemType;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.TaskJson;

/**
 * Represent a task that can be completed.
 */
public class PlannedTask extends PlannedItem {
  private boolean completed;

  /**
   * Create a cs3500.pa05.model.PlannedTask object
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param weekDay Day of week the task occurs
   */
  public PlannedTask(String name, String description, WeekDay weekDay) {
    super(name, description, weekDay);
    this.completed = false;
  }

  /**
   * Create a cs3500.pa05.model.PlannedTask object
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param weekDay Day of week the task occurs
   */
  public PlannedTask(String name, String description, WeekDay weekDay, ItemCategory category) {
    super(name, description, weekDay, category);
    this.completed = false;
  }

  /**
   * Create a cs3500.pa05.model.PlannedTask object
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param weekDay Day of week the task occurs
   */
  public PlannedTask(String name, String description, WeekDay weekDay, ItemCategory category,
                     boolean completed) {
    super(name, description, weekDay, category);
    this.completed = completed;
  }

  /**
   * Create a cs3500.pa05.model.PlannedTask object
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param weekDay Day of week the task occurs
   */
  public PlannedTask(String name, String description, WeekDay weekDay, boolean completed) {
    super(name, description, weekDay);
    this.completed = completed;
  }

  /**
   * Is the task complete?
   *
   * @return boolean
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Set completion of task
   *
   * @param completion the state of task completion
   */
  public void setCompleted(boolean completion) {
    this.completed = completion;
  }

  /**
   * Get String representation of task
   */
  @Override
  public String toString() {
    return this.getName() + ":" + System.lineSeparator()
            + ItemCategory.getCategoryHeaderString(this.getCategory())
            // + this.dayOfWeekAsString() + System.lineSeparator()
            + this.getDescription() + System.lineSeparator()
            + (this.isCompleted() ? "Complete" : "Incomplete");
  }

  /**
   * Converts the PlannedTask into a ItemJson of type TASK
   *
   * @return a Json record representing the PlannedTask
   */
  @Override
  public ItemJson toJsonRecord() {
    TaskJson taskJson = new TaskJson(this.completed);
    JsonNode taskNode = JsonUtils.serializeRecord(taskJson);
    return new ItemJson(getName(), getDescription(), getWeekDay(), getCategory(),
            ItemType.TASK, taskNode);
  }

  /**
   * Create a new Task from a Json record
   *
   * @param record : ItemJson of type TASK
   * @return the PlannedTask with the given info
   */
  public static PlannedTask fromJsonRecord(ItemJson record) {
    TaskJson taskJson = JsonUtils.mapper.convertValue(record.args(), TaskJson.class);
    return new PlannedTask(record.name(), record.description(), record.day(), record.category(),
            taskJson.status());
  }
}
