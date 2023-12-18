package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Entry point for the application
 */
public class Driver extends Application {

  @Override
  public void start(Stage stage) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/journalMain.fxml"));
      Pane rootPane = loader.load();

      Controller controller = loader.getController();
      controller.initialize();

      stage.setScene(new Scene(rootPane));
      stage.setTitle("My Weekly Journal!!");
      stage.show();

    } catch (Exception exception) {
      System.err.println(exception);
    }
  }

  /**
   * Run the application
   *
   * @param args Arguments passed through run configuration
   */
  public static void main(String[] args) {
    launch(args);
  }

}
