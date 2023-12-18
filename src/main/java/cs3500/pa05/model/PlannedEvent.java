
package cs3500.pa05.model;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.ItemJson;
import cs3500.pa05.model.json.ItemType;
import cs3500.pa05.model.json.JsonUtils;

/**
 * Represents a single planned event
 */
public class PlannedEvent extends PlannedItem {
  private String startTime;
  private double durationHours;

  /**
   * Create a model uncategorized.PlannedEvent object
   *
   * @param name The name of the event
   * @param description The description of the event
   * @param weekDay The day of the week the event occurs
   * @param startTime The time the event starts
   * @param durationHours The duration of the event in hours
   */
  public PlannedEvent(String name, String description, WeekDay weekDay,
                      String startTime, double durationHours) {
    super(name, description, weekDay);
    this.startTime = startTime;
    this.durationHours = durationHours;
  }

  /**
   * Create a model categorized.PlannedEvent object
   *
   * @param name The name of the event
   * @param description The description of the event
   * @param weekDay The day of the week the event occurs
   * @param startTime The time the event starts
   * @param durationHours The duration of the event in hours
   */
  public PlannedEvent(String name, String description, WeekDay weekDay,
                      String startTime, double durationHours, ItemCategory category) {
    super(name, description, weekDay, category);
    this.startTime = startTime;
    this.durationHours = durationHours;
  }

  /**
   * Return the start time of the event.
   *
   * @return String
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Return the duration of the event
   *
   * @return double
   */
  public double getDurationHours() {
    return this.durationHours;
  }

  /**
   * Get the event as a String
   */
  @Override
  public String toString() {
    return this.getName() + ":" + System.lineSeparator()
        + ItemCategory.getCategoryHeaderString(this.getCategory())
        + this.getTimeStamp() + System.lineSeparator()
        + this.getDescription();
  }

  /**
   * Get the timestamp of the event
   *
   */
  public String getTimeStamp() {
    return "At " + this.getStartTime() + "  (" + this.durationHours + " hours)";
  }

  /**
   * Converts the PlannedEvent into a ItemJson of type EVENT
   *
   * @return a Json record representing the PlannedEvent
   */
  @Override
  public ItemJson toJsonRecord() {
    EventJson eventJson = new EventJson(this.startTime, this.durationHours);
    JsonNode eventNode = JsonUtils.serializeRecord(eventJson);
    return new ItemJson(getName(), getDescription(), getWeekDay(), getCategory(),
        ItemType.EVENT, eventNode);
  }

  /**
   * Create a new Event from a Json record
   *
   * @param record : ItemJson of type EVENT
   * @return the PlannedEvent with the given info
   */
  public static PlannedEvent fromJsonRecord(ItemJson record) {
    EventJson eventJson = JsonUtils.mapper.convertValue(record.args(), EventJson.class);
    return new PlannedEvent(record.name(), record.description(), record.day(), eventJson.time(),
        eventJson.duration(), record.category());
  }
}
