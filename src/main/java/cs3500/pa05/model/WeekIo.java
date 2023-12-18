package cs3500.pa05.model;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.WeekJson;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

/**
 * Reads in a file as a list of strings for each line
 */
public class WeekIo {
  /**
   * Reads in and returns the PlannedWeek held in the given .bujo file
   *
   * @param path : Path to a .bujo file
   * @return PlannedWeek held in the .bujo file's json
   */
  public static PlannedWeek read(Path path) {
    try {
      if (path != null) {
        InputStream input = new FileInputStream(path.toFile());
        return JsonUtils.readWeek(input);
      } else {
        return new PlannedWeek();
      }
    } catch (FileNotFoundException e) {
      Controller.showError("File not found.");
      return new PlannedWeek();
    } catch (IllegalStateException e) {
      Controller.showError("Invalid .bujo formatting");
      return new PlannedWeek();
    }
  }

  /**
   * Writes the given week as Json to the given Path
   *
   * @param path : .bujo file to write the Week to
   * @param week : the PlannedWeek to write to the .bujo json file
   */
  public static void write(Path path, PlannedWeek week) {
    try {
      if (path != null) {
        OutputStream output = new FileOutputStream(path.toFile());
        WeekJson weekJson = week.toJsonRecord();
        output.write(JsonUtils.serializeRecord(weekJson).toString().getBytes());
      }
    } catch (IllegalArgumentException e) {
      Controller.showError("Unable to save .bujo file (invalid parsing)");
    } catch (IOException | UnsupportedOperationException e) {
      Controller.showError(e.getMessage());
    }
  }
}
