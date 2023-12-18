package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for configuration menu
 */
public class ConfigsController {

  @FXML
  private Label configsTitle;

  @FXML
  private Label maxEvent;

  @FXML
  private TextField maxEventInput;

  @FXML
  private Label maxTask;

  @FXML
  private TextField maxTaskInput;
  @FXML
  private Button submitButton;
  int maxTaskNum;
  int maxEventNum;


  public void setUpDialog() {
    this.submitButton.setOnAction(e -> submitButtonHanlder());
  }

  private void submitButtonHanlder() {
    try {
      maxTaskNum = Integer.parseInt(maxTaskInput.getText());
      maxEventNum = Integer.parseInt(maxEventInput.getText());
    } catch (NumberFormatException e) {
      Controller.showError("Inputs should be valid integers");
      maxTaskNum = Integer.MAX_VALUE;
      maxEventNum = Integer.MAX_VALUE;
      return;
    }
    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }

  public int getMaxTaskNum() {
    return this.maxTaskNum;
  }

  public int getMaxEventNum() {
    return this.maxEventNum;
  }
}