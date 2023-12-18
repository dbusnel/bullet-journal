package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a single planned week
 *
 * @param maxEvents The maximum number of events for the week
 * @param maxTasks The maximum number of tasks for the week
 * @param items The items planned for the week
 * @param weekName The name provided for the week
 * @param password The password to enter the week
 */
public record WeekJson(
    @JsonProperty("maxEvents") int maxEvents,
    @JsonProperty("maxTasks") int maxTasks,
    @JsonProperty("items") List<ItemJson> items,
    @JsonProperty("weekName") String weekName,
    @JsonProperty("password") String password
) {
}
