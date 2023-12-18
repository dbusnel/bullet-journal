package cs3500.pa05.model.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.PlannedWeek;
import java.io.IOException;
import java.io.InputStream;

/**
 * Simple utils class used to hold static methods that help with serializing and deserializing JSON.
 */
public class JsonUtils {
  public static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * Reads in the week and returns a PlannedWeek from the input stream
   *
   * @param input : InputStream to read from
   * @return PlannedWeek from the file's json
   * @throws IllegalStateException if the file has invalid formatting
   */
  public static PlannedWeek readWeek(InputStream input) throws IllegalStateException {
    try {
      JsonParser parser = mapper.getFactory().createParser(input);
      WeekJson week = parser.readValueAs(WeekJson.class);
      return PlannedWeek.fromJsonRecord(week);
    } catch (IOException e) {
      System.err.println("Error reading the .bujo file");
    }
    throw new IllegalStateException("The file's formatting was invalid");
  }
}
