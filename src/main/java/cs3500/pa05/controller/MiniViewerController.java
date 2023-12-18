package cs3500.pa05.controller;

import cs3500.pa05.model.PlannedItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Control the window for a error window
 */
public class MiniViewerController {

  @FXML
  public Button btnAccept;

  @FXML
  private Label lblEventTitle;
  @FXML
  private Label lblEventBody;

  /**
   * Initialize the dialog
   */
  @FXML
  public void initialize() { }

  /**
   * Change the message of the error
   */
  public void setItemRepresentation(PlannedItem item) {
    this.lblEventTitle.setText(item.getName());
    this.lblEventBody.setText(item.toString().substring(item.getName().length() + 1));
  }

}

