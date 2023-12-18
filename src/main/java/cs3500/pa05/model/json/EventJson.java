package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.WeekDay;

/**
 * Record to handle an event translation as a Json
 *
 * @param time        the time as string
 * @param duration    duration in hours as a double
 */
public record EventJson(@JsonProperty("start-time") String time,
                        @JsonProperty("duration-in-hours") double duration) {
}
