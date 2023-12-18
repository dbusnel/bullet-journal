package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;

/**
 * Represent a category of a PlannedItem
 */
public class ItemCategory {
  private String categoryName;

  /**
   * Construct a ItemCategory item
   *
   * @param categoryName : a String with the category's name
   */
  public ItemCategory(String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Get the category as a String representation with a header. Null means no category
   *
   * @param c : an ItemCategory to find the String interpretation of
   * @return the String representation of the category
   */
  public static String getCategoryHeaderString(ItemCategory c) {
    if (c == null) {
      return "";
    } else {
      return "Category: " + c.categoryName + System.lineSeparator();
    }
  }

  /**
   * Getter method for category name, serializes it
   *
   * @return string as category name
   */
  @JsonValue
  public String getCategoryName() {
    return categoryName;
  }
}
