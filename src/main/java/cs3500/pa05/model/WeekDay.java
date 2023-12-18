
package cs3500.pa05.model;

/**
 * Represents each day of the week
 */
public enum WeekDay {
  SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

  /**
   * Returns the Weekday in the correct case
   *
   * @param day : a WeekDay
   * @return the String representation with first letter capitalized
   */
  public static String getDayAsString(WeekDay day) {
    return switch (day) {
      case SUNDAY -> "Sunday";
      case MONDAY -> "Monday";
      case TUESDAY -> "Tuesday";
      case WEDNESDAY -> "Wednesday";
      case THURSDAY -> "Thursday";
      case FRIDAY -> "Friday";
      case SATURDAY -> "Saturday";
    };
  }

  /**
   * Parses the WeekDay from the given String
   *
   * @param given : String to parse WeekDay from
   * @return the WeekDay the String represents
   */
  public static WeekDay getDayFromString(String given) {
    return switch (given.toUpperCase()) {
      case "SUNDAY" -> SUNDAY;
      case "MONDAY" -> MONDAY;
      case "TUESDAY" -> TUESDAY;
      case "WEDNESDAY" -> WEDNESDAY;
      case "THURSDAY" -> THURSDAY;
      case "FRIDAY" -> FRIDAY;
      case "SATURDAY" -> SATURDAY;
      default -> throw new IllegalArgumentException("Invalid day of week provided.");
    };
  }

}
