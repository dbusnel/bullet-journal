package cs3500.pa05.controller;

import cs3500.pa05.model.ItemCategory;
import cs3500.pa05.model.PlannedItem;
import cs3500.pa05.model.PlannedTask;
import cs3500.pa05.model.PlannedWeek;
import cs3500.pa05.model.WeekDay;
import cs3500.pa05.model.WeekIo;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controls the overall calendar menu
 */
public class Controller {
  private PlannedWeek plannedWeek = new PlannedWeek();
  private int maxTasks = plannedWeek.getMaxTasks();
  private int maxEvents = plannedWeek.getMaxEvents();
  @FXML
  private MenuItem setPassMenu;

  @FXML
  private Menu fileMenu;

  @FXML
  private Label fridayLabel;
  private ObservableList<PlannedItem> fridayListHolder;


  @FXML
  private ListView<PlannedItem> fridayList = new ListView<>();


  @FXML
  private Pane fridayPane;

  @FXML
  private Menu helpMenu;

  @FXML
  private MenuBar menuBar;

  @FXML
  private Label mondayLabel;
  private ObservableList<PlannedItem> mondayListHolder;


  @FXML
  private ListView<PlannedItem> mondayList = new ListView<>();

  @FXML
  private Pane mondayPane;

  @FXML
  private Button newEventButton;

  @FXML
  private Button newTaskButton;

  @FXML
  private ProgressBar progressBar;

  @FXML
  private Label saturdayLabel;
  private ObservableList<PlannedItem> saturdayListHolder;


  @FXML
  private ListView<PlannedItem> saturdayList = new ListView<>();


  @FXML
  private Pane saturdayPane;

  @FXML
  private Label sundayLabel;
  private ObservableList<PlannedItem> sundayListHolder;

  @FXML
  private ListView<PlannedItem> sundayList = new ListView<>();

  @FXML
  private Pane sundayPane;

  @FXML
  private Label thursdayLabel;
  private ObservableList<PlannedItem> thursdayListHolder;

  @FXML
  private ListView<PlannedItem> thursdayList = new ListView<>();

  @FXML
  private Pane thursdayPane;

  @FXML
  private Label tuesdayLabel;

  private ObservableList<PlannedItem> tuesdayListHolder;

  @FXML
  private ListView<PlannedItem> tuesdayList = new ListView<>();

  @FXML
  private Pane tuesdayPane;

  @FXML
  private Label wednesdayLabel;

  private ObservableList<PlannedItem> wednesdayListHolder;

  @FXML
  private ListView<PlannedItem> wednesdayList = new ListView<>();

  @FXML
  private Pane wednesdayPane;
  private final List<PlannedItem> items = new ArrayList<>(plannedWeek.getAggregatedList());

  @FXML
  private MenuItem openMenu;

  @FXML
  private MenuItem openConfigs;

  @FXML
  private MenuItem saveMenu;
  @FXML
  private ListView<String> weekTaskView;
  @FXML
  private Label lblTaskCompletionPercent;
  @FXML
  private TextField txtTaskSearchBox;
  @FXML
  private MenuItem openTemplate;
  @FXML
  private Label nameOfTheWeek;
  private int numCompletedTasks = 0;
  private int totalTasks = 0;
  private int totalEvents = 0;
  private List<ItemCategory> categories;
  private String tempWeekName = "";
  private String tempPassword = "";


  public Controller() {
  }

  @FXML
  public void initialize() {
    loadMain();
  }

  /**
   * Initializes the calendar with all the buttons and handlers
   */
  public void loadMain() {
    fridayListHolder = FXCollections.observableArrayList();
    fridayList.setItems(fridayListHolder);
    mondayListHolder = FXCollections.observableArrayList();
    mondayList.setItems(mondayListHolder);
    tuesdayListHolder = FXCollections.observableArrayList();
    tuesdayList.setItems(tuesdayListHolder);
    wednesdayListHolder = FXCollections.observableArrayList();
    wednesdayList.setItems(wednesdayListHolder);
    thursdayListHolder = FXCollections.observableArrayList();
    thursdayList.setItems(thursdayListHolder);
    saturdayListHolder = FXCollections.observableArrayList();
    saturdayList.setItems(saturdayListHolder);
    sundayListHolder = FXCollections.observableArrayList();
    sundayList.setItems(sundayListHolder);
    newEventButton.setOnAction(e -> eventButtonHandler());
    newTaskButton.setOnAction(e -> taskButtonHandler());
    saveMenu.setOnAction(e -> saveItemHandler());
    openMenu.setOnAction(e -> importItemHandler());
    mondayList.setCellFactory(lv -> listCellHelper(mondayListHolder));
    tuesdayList.setCellFactory(lv -> listCellHelper(tuesdayListHolder));
    wednesdayList.setCellFactory(lv -> listCellHelper(wednesdayListHolder));
    thursdayList.setCellFactory(lv -> listCellHelper(thursdayListHolder));
    fridayList.setCellFactory(lv -> listCellHelper(fridayListHolder));
    saturdayList.setCellFactory(lv -> listCellHelper(saturdayListHolder));
    sundayList.setCellFactory(lv -> listCellHelper(sundayListHolder));
    openTemplate.setOnAction(e -> openTemplateHelper());
    txtTaskSearchBox.setOnKeyTyped(e -> this.update());
    this.openConfigs.setOnAction(e -> this.openConfigsDialog());
    this.nameOfTheWeek.setText(this.plannedWeek.getWeekName());
    this.setPassMenu.setOnAction(e -> changePassword());
    this.update();
  }


  /**
   * Creates the new event popup
   */
  private void eventButtonHandler() {
    if (this.totalEvents + 1 > this.maxEvents) {
      Controller.showError("CAUTION: New event exceeds set maximum (update in configuration)");
    }
    addPlannedItem("NEW EVENT");
  }

  /**
   * Creates the new task popup
   */
  private void taskButtonHandler() {
    if (this.totalTasks + 1 > this.maxTasks) {
      Controller.showError("CAUTION: New task exceeds set maximum (update in configuration)");
    }
    addPlannedItem("NEW TASK");
  }

  /**
   * Creates the dialog to add a new Item
   *
   * @param title : The title of the window (EVENT/TASK)
   */
  private void addPlannedItem(String title) {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(getClass().getResource("/dialog.fxml"));
      pane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("controller ::: addPlannedItem");
    }
    AddDialogController controller = loader.getController();
    controller.setupDialog(title);
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    dialogStage.showAndWait();
    PlannedItem newItem = controller.getResultItem();
    if (newItem != null) {
      items.add(newItem);
      assignToDay(newItem);
      plannedWeek.getAggregatedList().add(newItem);
      //System.out.println(items);
      //add the item to master list and update
      this.update();
    }
  }

  /**
   * Maps the input to the correct weekday
   *
   * @param item : the Inputted item
   */
  private void assignToDay(PlannedItem item) {
    if (item.getWeekDay().equals(WeekDay.MONDAY)) {
      mondayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.TUESDAY)) {
      tuesdayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.WEDNESDAY)) {
      wednesdayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.THURSDAY)) {
      thursdayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.FRIDAY)) {
      fridayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.SATURDAY)) {
      saturdayListHolder.add(item);
    } else if (item.getWeekDay().equals(WeekDay.SUNDAY)) {
      sundayListHolder.add(item);
    }
  }

  /**
   * Return all the PlannedItems held
   *
   * @return the PlannedItems in the calendar
   */
  public List<PlannedItem> getItems() {
    return this.items;
  }

  /**
   * Handles saving a calendar to a file
   */
  private void saveItemHandler() {
    Path targetPath = this.saveImportHandler("SAVE");
    if (!this.tempWeekName.equals("")) {
      this.plannedWeek.setWeekName(this.tempWeekName);
    }
    WeekIo.write(targetPath, this.plannedWeek);

  }

  /**
   * Handles importing a calendar from a file
   */
  private void importItemHandler() {
    Path targetPath = this.saveImportHandler("IMPORT");
    PlannedWeek initialWeek = WeekIo.read(targetPath);
    if (this.tempPassword.equals(initialWeek.getPassword())) {
      this.changeWeek(initialWeek);
    } else {
      Controller.showError("Incorrect password detected!! Initializing new week");
      this.changeWeek(new PlannedWeek());
    }
  }

  private void openTemplateHelper() {
    Path targetPath = this.saveImportHandler("TEMPLATE");
    PlannedWeek initialWeek = WeekIo.read(targetPath);
    PlannedWeek week = new PlannedWeek(new ArrayList<>(), initialWeek.getMaxEvents(),
            initialWeek.getMaxTasks(), "Your New Week", "");
    week.setWeekName(this.tempWeekName);
    this.changeWeek(week);
  }

  /**
   * Resets all relevant lists to accommodate a new week
   *
   * @param arg takes a planned week as an argument
   */
  private void changeWeek(PlannedWeek arg) {
    if (arg == null) {
      arg = new PlannedWeek();
    }
    this.plannedWeek = arg;
    this.items.clear();
    this.clearAllListHolders();
    List<PlannedItem> tempList;
    if (plannedWeek.getAggregatedList() != null) {
      tempList = new ArrayList<>(plannedWeek.getAggregatedList());
    } else {
      tempList = new ArrayList<>();
    }
    this.items.addAll(tempList);
    for (PlannedItem item : this.items) {
      assignToDay(item);
    }
    this.maxTasks = arg.getMaxTasks();
    this.maxEvents = arg.getMaxEvents();
    this.totalTasks = 0;
    this.numCompletedTasks = 0;
    this.nameOfTheWeek.setText(plannedWeek.getWeekName());
    this.update();
  }


  /**
   * Opens a Save/Import handling window
   *
   * @param type String of either "SAVE"/"IMPORT"
   */
  private Path saveImportHandler(String type) {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(getClass().getResource("/saveImport.fxml"));
      pane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("controller ::: saveImportHandler");
    }
    SaveImportController controller = loader.getController();
    controller.initialize(type);
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    dialogStage.showAndWait();
    this.tempWeekName = controller.getInputName();
    this.tempPassword = controller.getReceivedPassword();
    return controller.getPath();
  }

  /**
   * Clear all the holders for each day
   */
  private void clearAllListHolders() {
    this.mondayListHolder.clear();
    this.tuesdayListHolder.clear();
    this.wednesdayListHolder.clear();
    this.thursdayListHolder.clear();
    this.fridayListHolder.clear();
    this.saturdayListHolder.clear();
    this.sundayListHolder.clear();

  }

  /**
   * Iterates through the list of items and maintains a list (class field) of all unique categories
   * This should be called whenever a new item is added to ensure the list is updated correctly.
   */
  public void getAllCategories() {
    this.categories = new ArrayList<>();
    for (PlannedItem i : this.items) {
      if (!this.categories.contains(i.getCategory())) {
        categories.add(i.getCategory());
      }
    }
  }

  /**
   * Adds all the weekly tasks to be displayed to the sidebar.
   * This should also be called after each event is added.
   */
  private void updateWeeklySidebar() {
    this.weekTaskView.getItems().clear();
    ArrayList<PlannedItem> allTasks = new ArrayList<>();
    for (PlannedItem item : this.items) {
      if (item instanceof PlannedTask) {
        allTasks.add(item);
      }
    }

    allTasks = PlannedTask.searchForItemsWithText(allTasks, txtTaskSearchBox.getText());
    for (PlannedItem item : allTasks) {
      this.weekTaskView.getItems().add(item.toString());
    }
  }

  /**
   * Updates the counts for tasks
   */
  private void updateTaskEventsCount() {
    this.totalEvents = 0;
    this.totalTasks = 0;
    this.numCompletedTasks = 0;
    for (PlannedItem item : this.items) {
      if (item instanceof PlannedTask) {
        this.totalTasks++;
        //add if completed
        if (((PlannedTask) item).isCompleted()) {
          this.numCompletedTasks++;
        }
      }
    }
    this.totalEvents = items.size() - this.totalTasks;
  }

  /**
   * Get the percentage of completed tasks
   */
  private double getPercentCompleteTasks() {
    if (this.totalTasks > 0) {
      return ((double) this.numCompletedTasks) / this.totalTasks;
    } else {
      return 1;
    }
  }

  /**
   * Update the progress bar
   */
  private void updateProgressBar() {
    double percentage = (int) (this.getPercentCompleteTasks() * 1000) / 10.0;
    this.progressBar.setProgress(percentage / 100.0);
    this.lblTaskCompletionPercent.setText(percentage + "% (" + this.numCompletedTasks + "/"
            + this.totalTasks + ")");
  }

  /**
   * Invokes all the methods that update the state of the controller
   */
  private void update() {
    this.updateWeeklySidebar();
    this.getAllCategories();
    this.updateTaskEventsCount();
    this.updateProgressBar();
  }

  /**
   * Method that handles the activation even for list cells
   *
   * @param holderList the relevant holder list to set the action for
   * @return list cells
   */
  private ListCell<PlannedItem> listCellHelper(ObservableList<PlannedItem> holderList) {
    final ListCell<PlannedItem> listCell = new ListCell<PlannedItem>() {
      @Override
      protected void updateItem(PlannedItem item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
          setGraphic(null);
        } else {
          setText(item.toString());
          if (item instanceof PlannedTask) {
            if (((PlannedTask) item).isCompleted()) {
              getStyleClass().add("completed-task");
            } else {
              getStyleClass().removeAll("completed-task");
            }
          }
        }
      }
    };

    final ContextMenu contextMenu = new ContextMenu();
    MenuItem delete = new MenuItem();
    MenuItem markComplete = new MenuItem();
    MenuItem edit = new MenuItem();
    delete.setText("Delete");
    markComplete.setText("Mark Finished");
    edit.setText("Edit");
    delete.setOnAction(e -> deletePopupHelper(listCell, holderList));
    markComplete.setOnAction(e -> markCompleteHelper(listCell, holderList));
    edit.setOnAction(e -> editHelper(listCell, holderList));
    contextMenu.getItems().addAll(delete, markComplete, edit);

    listCell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
      if (isNowEmpty) {
        listCell.setContextMenu(null);
      } else {
        listCell.setContextMenu(contextMenu);
      }
    });
    listCell.setOnMouseClicked(new EventHandler<>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
          Controller.showMiniViewer(listCell.getItem());
        }
      }
    });


    return listCell;
  }


  /**
   * Works for the delete option when it popups upon the right click
   *
   * @param cell       to click on
   * @param holderList the list that call belongs to
   */
  private void deletePopupHelper(ListCell<PlannedItem> cell,
                                 ObservableList<PlannedItem> holderList) {
    PlannedItem item = cell.getItem();
    holderList.remove(item);
    items.remove(item);
    if (item instanceof PlannedTask) {
      if (((PlannedTask) item).isCompleted()) {
        this.numCompletedTasks--;
      }
      this.totalTasks--;
    }
    this.update();
  }

  /**
   * Works for the mark complete option when it popups upon the right click
   *
   * @param cell       the cell in focus
   * @param holderList the list this cell belongs to
   */
  private void markCompleteHelper(ListCell<PlannedItem> cell,
                                  ObservableList<PlannedItem> holderList) {
    PlannedItem item = cell.getItem();
    if (item instanceof PlannedTask) {
      ((PlannedTask) item).setCompleted(true);
      cell.setText("**Completed**");
    }
    this.update();
  }

  private void editHelper(ListCell<PlannedItem> cell,
                          ObservableList<PlannedItem> holderList) {
    PlannedItem item = cell.getItem();
    String title;
    if (item instanceof PlannedTask) {
      title = "EDIT TASK";
    } else {
      title = "EDIT EVENT";
    }

    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(getClass().getResource("/dialog.fxml"));
      pane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("controller ::: editHelper");
    }
    AddDialogController controller = loader.getController();
    controller.setupEditDialog(title, item);
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    dialogStage.showAndWait();

    PlannedItem newItem = controller.getResultItem();
    if (newItem != null) {
      holderList.remove(item);
      this.items.remove(item);
      this.items.add(newItem);
      this.assignToDay(newItem);
      this.update();
    }
  }

  /**
   * Show an error dialog with the provided message
   *
   * @param message The error message to display
   */
  public static void showError(String message) {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(Controller.class.getResource("/errorDialog.fxml"));
      loader.setRoot(new AnchorPane());
      pane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("controller ::: ErrorDialog");
    }
    ErrorDialogController controller = loader.getController();
    controller.setErrorMessage(message);

    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    controller.btnAccept.setOnMouseClicked(e -> dialogStage.close());
    dialogStage.showAndWait();
  }

  private void openConfigsDialog() {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(getClass().getResource("/configDialog.fxml"));
      pane = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("controller ::: openConfigsDialog");
    }
    ConfigsController controller = loader.getController();
    controller.setUpDialog();
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    dialogStage.showAndWait();
    this.maxEvents = controller.getMaxEventNum();
    this.plannedWeek.setMaxEvents(maxEvents);
    this.maxTasks = controller.getMaxTaskNum();
    this.plannedWeek.setMaxTasks(maxTasks);
  }

  private static void showMiniViewer(PlannedItem item) {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(Controller.class.getResource("/miniViewer.fxml"));
      loader.setRoot(new AnchorPane());
      pane = loader.load();
    } catch (IOException e) {
      Controller.showError(e.getMessage());
    }
    MiniViewerController controller = loader.getController();
    controller.setItemRepresentation(item);

    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    controller.btnAccept.setOnMouseClicked(e -> dialogStage.close());
    dialogStage.showAndWait();
  }

  private void changePassword() {
    FXMLLoader loader = null;
    Pane pane = null;
    try {
      loader = new FXMLLoader(Controller.class.getResource("/setPass.fxml"));
      pane = loader.load();
    } catch (IOException e) {
      Controller.showError(e.getMessage());
    }
    SetPassController controller = loader.getController();
    Stage dialogStage = new Stage();
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.setScene(new Scene(pane));
    dialogStage.showAndWait();
    String toChange = controller.getResultPassword();
    if (toChange.equals("")) {
      Controller.showError("Invalid password attempted!");
    } else {
      plannedWeek.setPassword(toChange);
    }
  }

}
