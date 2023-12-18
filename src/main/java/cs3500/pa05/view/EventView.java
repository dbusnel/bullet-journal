package cs3500.pa05.view;

import cs3500.pa05.model.PlannedEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Strategies to visualize events
 */
public class EventView {

  VBox eventContainer;

  /**
   * Create a JavaFX visualization of a single event
   *
   * @param event The event to visualize
   */
  public EventView(PlannedEvent event) {
    eventContainer = new VBox();
    eventContainer.getChildren().add(new Label(event.getName()));
    eventContainer.getChildren().add(new Label(event.getTimeStamp()));
    eventContainer.getChildren().add(new Label(event.getDescription()));
  }

  public VBox visualize() {
    return this.eventContainer;
  }

}
