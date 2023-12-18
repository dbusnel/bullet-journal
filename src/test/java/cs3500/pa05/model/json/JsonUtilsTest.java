package cs3500.pa05.model.json;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.Test;

/**
 * Test errors when serializing or parsing JSON
 */
class JsonUtilsTest {
  /**
   * A mock record with no Json to throw an error
   *
   * @param banana doesn't matter
   */
  record MockRecord(int banana) {}

  /**
   * An invalid record cannot be converted to a JsonNode and therefore throws an error
   */
  @Test
  void testSerializingInvalidRecord() {
    assertThrows(IllegalArgumentException.class,
            () -> JsonUtils.serializeRecord(new MockRecord(1)));
  }

  /**
   * An invalid input stream (badly formatted) throws an IllegalStateException
   */
  @Test
  void testInvalidInput() {
    InputStream stream = new ByteArrayInputStream("test".getBytes());
    assertThrows(IllegalStateException.class,
            () -> JsonUtils.readWeek(stream));
  }
}
