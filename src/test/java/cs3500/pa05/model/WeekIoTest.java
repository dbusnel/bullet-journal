package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Test the WeekIO to read and write .bujo (JSON)
 */
class WeekIoTest {
  private final Path testPath = Path.of("src/test/testFiles/test.bujo");
  private final Path outputPath = Path.of("src/test/testFiles/output.bujo");

  /**
   * Read in a week, test its data, then write it back out
   * Also check that the outputted data is identical to the input
   */
  @Test
  void testValidBujo() {
    PlannedWeek week = WeekIo.read(this.testPath);
    assertEquals("Test Week", week.getWeekName());
    assertEquals("password", week.getPassword());
    assertEquals(10, week.getMaxTasks());
    assertEquals(10, week.getMaxEvents());
    assertEquals(3, week.getAggregatedList().size());
    WeekIo.write(this.outputPath, week);
    Scanner expected;
    Scanner actual;
    try {
      expected = new Scanner(this.testPath);
      actual = new Scanner(this.outputPath);
      while (expected.hasNext() && actual.hasNext()) {
        assertEquals(expected.next(), actual.next());
      }
      assertFalse(expected.hasNext());
      assertFalse(actual.hasNext());
      expected.close();
      actual.close();
    } catch (IOException e) {
      System.err.println("Unable to open test scanners. Testing may be limited");
    }
  }
}
