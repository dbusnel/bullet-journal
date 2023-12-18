package cs3500.pa05.controller;

import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.PlannedEvent;
import cs3500.pa05.model.PlannedItem;
import cs3500.pa05.model.PlannedTask;
import cs3500.pa05.model.WeekDay;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The popup for adding a PlannedItem to the calendar
 */
public class AddDialogController {

  @FXML
  private Label dayLabel;

  @FXML
  private TextField descriptionInput;

  @FXML
  private Label descriptionLabel;

  @FXML
  private TextField durationInput;

  @FXML
  private Label durationLabel;

  @FXML
  private TextField nameInput;

  @FXML
  private Label nameLabel;

  @FXML
  private Label startLabel;

  @FXML
  private TextField startTimeInput;

  @FXML
  private Button submitButton;

  @FXML
  private Label title;

  @FXML
  private TextField weekInput;

  @FXML
  private TextField categoryInput;
  private PlannedItem resultItem = null;
  private String titleType;

  /**
   * Set up the dialog for the given task/event
   *
   * @param title String either "NEW TASK"/"NEW EVENT"
   */
  public void setupDialog(String title) {
    titleType = title;
    this.title.setText(title);
    if (title.equals("NEW TASK")) {
      durationInput.setDisable(true);
      startTimeInput.setDisable(true);
    }
    submitButton.setOnAction(e -> handleSubmitButton());
  }

  /**
   * Create the dialog popup for editing an event:
   *
   * @param title Title of the dialog
   * @param item The item to edit
   */
  public void setupEditDialog(String title, PlannedItem item) {
    titleType = title;
    this.title.setText(title);
    this.nameInput.setText(item.getName());
    this.descriptionInput.setText(item.getDescription());
    this.weekInput.setText(item.dayOfWeekAsString());
    this.categoryInput.setText(item.getCategory().getCategoryName());
    if (title.equals("EDIT TASK")) {
      durationInput.setDisable(true);
      startTimeInput.setDisable(true);
    } else {
      PlannedEvent event = (PlannedEvent) item;
      durationInput.setText(String.valueOf(event.getDurationHours()));
      startTimeInput.setText(event.getStartTime());
    }
    submitButton.setOnAction(e -> handleSubmitButton());
  }

  /**
   * Handles if the user is attempting to submit the item
   */
  @FXML
  private void handleSubmitButton() {
    String name;
    String description;
    WeekDay weekDay;
    String startTime;
    double duration;
    ItemCategory category;

    name = nameInput.getText();
    description = descriptionInput.getText();

    if (name.equals("")) {
      Controller.showError("Please enter a title");
      return;
    }

    try {
      weekDay = WeekDay.getDayFromString(weekInput.getText());
      category = !categoryInput.getText().equals("") ? new ItemCategory(categoryInput.getText())
          : null;
    } catch (IllegalArgumentException e) {
      Controller.showError("Please enter a valid day of the week (Ex: Monday)");
      return;
    }

    if (titleType.contains("TASK")) {
      this.resultItem = new PlannedTask(name, description, weekDay, category);
    } else {
      startTime = startTimeInput.getText();

      if (startTime.equals("")) {
        Controller.showError("Please enter a start time");
        return;
      }

      try {
        duration = Double.parseDouble(durationInput.getText());
      } catch (NumberFormatException e) {
        Controller.showError("Please enter a numeric duration in hours.");
        return;
      }
      this.resultItem = new PlannedEvent(name, description, weekDay, startTime, duration, category);
    }

    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }

  /**
   * Returns the ResultItem from the popup
   *
   * @return the new ResultItem from the user
   */
  public PlannedItem getResultItem() {
    return resultItem;
  }
}
