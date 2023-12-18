package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.WeekDay;

/**
 * Json translation record for a task
 *
 * @param status the status as a boolean
 */
public record TaskJson(@JsonProperty("completed") boolean status) {

}
