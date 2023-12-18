package cs3500.pa05.model;

import cs3500.pa05.model.json.ItemJson;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single planned week
 */
public class PlannedWeek {
  private ArrayList<PlannedItem> allPlannedItems;
  private int maxEvents;
  private int maxTasks;
  private String weekName;
  private String password;

  /**
   * Create the Week
   *
   * @param items : ArrayList of PlannedItems (all items in the calendar)
   * @param maxEvents : Top limit of PlannedEvents before a warning
   * @param maxTasks : Top limit of PlannedTasks before a warning
   * @param weekName : String representing the name of the week
   * @param password : String representing the week's password
   */
  public PlannedWeek(ArrayList<PlannedItem> items, int maxEvents, int maxTasks,
                     String weekName, String password) {
    this.allPlannedItems = items;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.weekName = weekName;
    this.password = password;
  }

  /**
   * Creates a new empty week with default name and empty password
   */
  public PlannedWeek() {
    this.allPlannedItems = new ArrayList<>();
    this.maxEvents = 1000;
    this.maxTasks = 1000;
    this.weekName = "Your New Week";
    this.password = "";
  }

  //TODO: Put a completed number of tasks field
  //TODO: Put a field for a name
  //TODO: Put a field for an integer password
  public List<PlannedItem> getAggregatedList() {
    return this.allPlannedItems;
  }

  /**
   * Getter for max amount of events
   *
   * @return the int representing the maximum amount of events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Getter for max amount of tasks
   *
   * @return the int representing the maximum amount of tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Sets the max amount of events to the given value
   *
   * @param events : int representing new max amount
   */
  public void setMaxEvents(int events) {
    this.maxEvents = events;
  }

  /**
   * Sets the max amount of tasks to the given value
   *
   * @param tasks : int representing the max amount
   */
  public void setMaxTasks(int tasks) {
    this.maxTasks = tasks;
  }

  /**
   * Converts the PlannedWeek into a Json containing the max's and all Items
   *
   * @return WeekJson record representing the week
   */
  public WeekJson toJsonRecord() {
    List<ItemJson> items = new ArrayList<>();
    for (PlannedItem item : this.allPlannedItems) {
      items.add(item.toJsonRecord());
    }
    return new WeekJson(this.maxEvents, this.maxTasks, items, this.weekName, this.password);
  }

  /**
   * Simple getter for the week's name
   *
   * @return String weekName
   */
  public String getWeekName() {
    return this.weekName;
  }

  /**
   * Simple setter for the week's name
   *
   * @param weekName : String new name for the week
   */
  public void setWeekName(String weekName) {
    this.weekName = weekName;
  }

  /**
   * Simple getter for the week's password
   *
   * @return String password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Simple setter for the week's password
   *
   * @param password : String password for the week
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Create a new PlannedWeek from a Json record
   *
   * @param record : WeekJson representing the week in Json
   * @return a new PlannedWeek with the information from the supplied record
   */
  public static PlannedWeek fromJsonRecord(WeekJson record) {
    List<ItemJson> items = record.items();
    ArrayList<PlannedItem> plannedItems = new ArrayList<>();
    for (ItemJson item : items) {
      PlannedItem plannedItem = item.toItem();
      if (plannedItem != null) {
        plannedItems.add(plannedItem);
      }
    }
    return new PlannedWeek(plannedItems, record.maxEvents(), record.maxTasks(),
        record.weekName(), record.password());
  }
}

