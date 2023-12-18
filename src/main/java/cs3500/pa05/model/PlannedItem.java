
package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cs3500.pa05.model.json.ItemJson;
import java.util.ArrayList;

/**
 * Represents a planned item on the week's calendar.
 */
public abstract class PlannedItem {
  private String name;
  private WeekDay weekDay;
  private String description;
  private ItemCategory category;

  /**
   * Construct an uncategorized model.PlannedItem
   *
   * @param name Name of the event
   * @param description Description of the event
   * @param weekDay Day of the week the event occurs
   */
  public PlannedItem(String name, String description, WeekDay weekDay) {
    this.name = name;
    this.description = description;
    this.weekDay = weekDay;
    this.checkShortHandCategoryInput();
  }

  /**
   * Construct an uncategorized model.PlannedItem
   *
   * @param name Name of the event
   * @param description Description of the event
   * @param weekDay Day of the week the event occurs
   * @param category Category of the item
   */
  public PlannedItem(String name, String description, WeekDay weekDay, ItemCategory category) {
    this.name = name;
    this.description = description;
    this.weekDay = weekDay;
    this.category = category;
    this.checkShortHandCategoryInput();
  }

  /**
   * Get the name of the item
   *
   * @return String
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the description of the item
   *
   * @return String
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get the category of the item as a String
   *
   * @return ItemCategory
   */
  public ItemCategory getCategory() {
    return this.category;
  }

  /**
   * Get the weekday of the item as a String
   *
   * @return String
   */
  @JsonIgnore
  public String dayOfWeekAsString() {
    return WeekDay.getDayAsString(this.weekDay);
  }
  /**
   * Get day of the week as weekday enum
   *
   * @return weekday enum
   */

  public WeekDay getWeekDay() {
    return this.weekDay;
  }

  /**
   * Check for shorthand category input (if input starts with #)
   */
  public void checkShortHandCategoryInput() {
    if (this.name.charAt(0) == '#') {
      String[] splitFromCategory = this.name.split(" ");
      if (splitFromCategory.length > 1) {

        if (splitFromCategory[0].length() > 1) {
          this.category = new ItemCategory(splitFromCategory[0].substring(1));
        }

        StringBuilder rebuild = new StringBuilder();
        for (int i = 1; i < splitFromCategory.length; i++) {
          rebuild.append(splitFromCategory[i]).append(' ');
        }
        rebuild.setLength(rebuild.length() - 1);
        this.name = rebuild.toString();
      }
    }
  }

  /**
   * Do a search for events which contain the provided String in the title
   *
   * @param toSearch the ArrayList of Items to search if they contain the text
   * @param text the text to search for
   * @return an ArrayList of only the items that contain the text
   */
  public static ArrayList<PlannedItem> searchForItemsWithText(ArrayList<PlannedItem> toSearch,
                                                              String text) {
    ArrayList<PlannedItem> output = new ArrayList<>();
    for (PlannedItem item : toSearch) {
      if (item.getName().contains(text)
          || item.getDescription().contains(text)
          || (item.getCategory() != null && item.getCategory().getCategoryName().contains(text))) {
        output.add(item);
      }
    }
    return output;
  }

  /**
   * Convert the Item into Json
   *
   * @return a ItemJson representing the Item
   */
  public abstract ItemJson toJsonRecord();
}
