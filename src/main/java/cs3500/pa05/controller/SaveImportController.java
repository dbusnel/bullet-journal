package cs3500.pa05.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Imports or Saves a calendar from or to a .bujo file
 */
public class SaveImportController {

  @FXML
  private TextField pathInputField;
  @FXML
  private Button submitButton;
  @FXML
  private Label titleLabel;
  @FXML
  private TextField nameInput;
  private Path resultPath;
  private String inputName = "";
  @FXML
  private TextField passwordInput;
  private String receivedPassword = "";
  @FXML
  private Label passwordLabel;
  @FXML
  private Label importWarning;


  /**
   * Initializes the view to import or save
   *
   * @param type : either "IMPORT" or "SAVE"
   */
  @FXML
  public void initialize(String type) {
    if (type.equals("IMPORT")) {
      this.nameInput.setDisable(true);
      titleLabel.setText("Please input a full valid file path for a .bujo file to import from!!");
    } else if (type.equals("SAVE")) {
      titleLabel.setText("Please input a full valid file path for a .bujo file to save to!!");
      this.passwordInput.setDisable(true);
      this.passwordInput.setVisible(false);
      this.passwordLabel.setVisible(false);
      this.importWarning.setText("Please leave empty if you want to keep the previous name!");
    } else if (type.equals("TEMPLATE")) {
      titleLabel.setText("Please input a full valid file path for a .bujo file to import"
              + " the template from!!");
      this.passwordInput.setDisable(true);
      this.passwordInput.setVisible(false);
      this.passwordLabel.setVisible(false);
      this.importWarning.setVisible(false);
    }
    this.submitButton.setOnAction(e -> handleSubmitButton());
  }

  /**
   * Ensure the path ends in .bujo
   *
   * @param path The path to check
   * @return boolean
   */
  private static boolean validatePath(Path path) {
    return path.toString().endsWith(".bujo");

  }

  /**
   * Handle when the button in the view is pressed
   * Either save or input using the appropriate reader or writer
   */
  private void handleSubmitButton() {
    String targetPathString = pathInputField.getText();
    Path targetPath = Paths.get(targetPathString);
    inputName = nameInput.getText();
    if (passwordInput.getText() == null) {
      this.receivedPassword = "";
    } else {
      receivedPassword = passwordInput.getText();
    }
    if (validatePath(targetPath)) {
      this.resultPath = targetPath;
      Stage stage = (Stage) submitButton.getScene().getWindow();
      stage.close();
    } else {
      Controller.showError("Invalid path.");
    }
  }

  /**
   * Returns the path to read/write from
   *
   * @return the Path to the  .bujo file
   */
  public Path getPath() {
    return this.resultPath;
  }

  /**
   * Getter to obtain the input name that was set
   *
   * @return string value as input
   */
  public String getInputName() {
    return inputName;
  }

  /**
   * Getter to obtain the password that was set
   *
   * @return string value as input
   */
  public String getReceivedPassword() {
    return this.receivedPassword;
  }
}
