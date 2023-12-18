package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the dialog to set password
 */
public class SetPassController {
  @FXML
  private TextField setPassField;
  @FXML
  private Button submitButton;
  private String resultPassword = "";

  /**
   * Initializes the controller
   */
  @FXML
  public void initialize() {
    this.submitButton.setOnAction(e -> obtainPassword());
  }

  /**
   * Sets the password to result
   */
  private void obtainPassword() {
    if (setPassField.getText() == null) {
      this.resultPassword = "";
    } else {
      this.resultPassword = setPassField.getText();
    }
    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }

  /**
   * getter for the result password
   *
   * @return string as pass
   */
  public String getResultPassword() {
    return this.resultPassword;
  }

}