package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.PlannedEvent;
import cs3500.pa05.model.PlannedItem;
import cs3500.pa05.model.PlannedTask;
import cs3500.pa05.model.WeekDay;

/**
 * A Json record representing a PlannedItem (either TASK or EVENT)
 *
 * @param name : Name of the item (String)
 * @param description : Description of the item (String)
 * @param day : Day of the item (WeekDay)
 * @param category : Category the item falls under (ItemCategory)
 * @param type : type of item (ItemType)
 * @param args : extra information according to ItemType (either EventJson or TaskJson)
 */
public record ItemJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("weekDay") WeekDay day,
    @JsonProperty("category") ItemCategory category,
    @JsonProperty("type") ItemType type,
    @JsonProperty("args") JsonNode args) {

  /**
   * Converts the given ItemJson to a real PlannedItem
   *
   * @return the PlannedItem with the record's information
   */
  public PlannedItem toItem() {
    if (this.type == ItemType.EVENT) {
      return PlannedEvent.fromJsonRecord(this);
    } else if (this.type == ItemType.TASK) {
      return PlannedTask.fromJsonRecord(this);
    } else {
      System.err.println("Invalid item type given. Ignoring");
      return null;
    }
  }
}
