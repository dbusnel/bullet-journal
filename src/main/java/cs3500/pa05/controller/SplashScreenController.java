package cs3500.pa05.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller for a splash screen
 */
public class SplashScreenController {
  private Runnable action;

  @FXML
  private Label loadingLabel;
  @FXML
  private Button continueButton;

  /**
   * Initializes the screen
   */
  @FXML
  public void initialize() {
    loadingLabel.setVisible(true);
    continueButton.setOnAction(e -> closeScreen());
  }

  public void setAction(Runnable action) {
    this.action = action;
  }

  private void closeScreen() {
    Stage stage = (Stage) continueButton.getScene().getWindow();
    stage.close();
    if (action != null) {
      action.run();
    }
  }
}
