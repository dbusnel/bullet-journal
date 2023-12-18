package cs3500.pa05.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Control the window for a error window
 */
public class ErrorDialogController {

  @FXML
  public Button btnAccept;

  @FXML
  private Label lblErrorText;

  /**
   * Initialize the dialog
   */
  @FXML
  public void initialize() { }

  /**
   * Change the message of the error
   */
  public void setErrorMessage(String message) {
    this.lblErrorText.setText(message);
  }

}

