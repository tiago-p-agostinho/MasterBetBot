package masterbetbot;
 
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox; 
import javafx.scene.paint.Color; 
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MasterBetBot extends Application {
 private TreeView treeView;
 private ListView listView;
 private GridPane grid;
 public static void main(String[] args) {
      launch(args);
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception {
      double width;
      double height;
      primaryStage.setTitle("MasterBetBot");
      Screen screen = Screen.getPrimary();
      Rectangle2D bounds = screen.getVisualBounds();    
     
      primaryStage.setX(bounds.getMinX());
      primaryStage.setY(bounds.getMinY());
      primaryStage.setWidth(bounds.getWidth());
      primaryStage.setHeight(bounds.getHeight());
      
      width=primaryStage.getX();
      height=primaryStage.getY();
    
      BorderPane bp = new BorderPane();
      grid = new GridPane();
      grid.setHgap(5);
      grid.setVgap(5);
      grid.setPadding(new Insets(0, 10, 0, 10));
      
      MenuBarMBB menuBarMBB = new MenuBarMBB();
      menuBarMBB.start(primaryStage);
      MenuBar menuBar = menuBarMBB.getMenuBar();
      GridPane.setConstraints(menuBar,0,0,30,1);
        
      TreeMarkets tm = new TreeMarkets();
      tm.start();
      treeView = tm.getTreeView();
      GridPane.setConstraints(treeView, 1, 3, 6, 10);
      
      final Separator sepVert1 = new Separator();
      sepVert1.setOrientation(Orientation.VERTICAL);
      sepVert1.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVert1, 0, 1, 1, 1);
      GridPane.setRowSpan(sepVert1, 1);
      grid.getChildren().add(sepVert1);
      
      SearchBar searchBar = new SearchBar(this);
      searchBar.start();
      TextField textField = searchBar.getTextField();
      GridPane.setConstraints(textField,1,1,5,1);
            
      final Separator sepVert2 = new Separator();
      sepVert2.setOrientation(Orientation.VERTICAL);
      sepVert2.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVert2, 6, 1, 1, 1);
      GridPane.setRowSpan(sepVert2, 1);
      grid.getChildren().add(sepVert2);
      
      Label stakeLabel = new Label("Stake:");
      GridPane.setConstraints(stakeLabel, 7, 1);
      
      DoubleTextField euroText = new DoubleTextField("2.00");
      GridPane.setConstraints(euroText, 8, 1, 2, 1);
       
      Label eurosLabel = new Label("€");
      GridPane.setConstraints(eurosLabel, 10, 1, 1, 1);
      
      Button stakeButton = new Button("Back");
      GridPane.setConstraints(stakeButton, 11, 1, 1, 1); 
      stakeButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
      stakeButton.setPrefWidth(80);
      stakeButton.setOnAction((ActionEvent event)->{
           if((stakeButton.getText()).equals("Back")){
              stakeButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
              stakeButton.setText("Lay");
           }
           else{
              stakeButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
              stakeButton.setText("Back"); 
           }
        });
      
      final Separator sepVert3 = new Separator();
      sepVert3.setOrientation(Orientation.VERTICAL);
      sepVert3.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVert3, 12, 1, 1, 1);
      GridPane.setRowSpan(sepVert3, 1);
      grid.getChildren().add(sepVert3);
      
      Label userNameLabel = new Label("UserName:");
      GridPane.setConstraints(userNameLabel, 20, 1);
      Label userNameField = new Label("MRtinho");
      GridPane.setConstraints(userNameField, 21, 1);  
        
      Label balanceLabel = new Label("Balance:");
      GridPane.setConstraints(balanceLabel, 28, 1);
      Label balanceField = new Label("NaN");
      GridPane.setConstraints(balanceField, 33, 1);
      
      final Separator sepHor1 = new Separator();
      sepHor1.setValignment(VPos.CENTER);
     // sepHor1.setPrefWidth(100);
      GridPane.setConstraints(sepHor1, 0, 2, 30, 1);
      GridPane.setColumnSpan(sepHor1, 40);
      grid.getChildren().add(sepHor1);
    
      listView = searchBar.getList();
      GridPane.setConstraints(listView, 1, 3, 6, 10);
      
      final Separator sepHor2 = new Separator();
      sepHor2.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepHor2, 0, 14, 8, 1);
      GridPane.setColumnSpan(sepHor2, 9);
      grid.getChildren().add(sepHor2);
      
      final Separator sepVert4 = new Separator();
      sepVert4.setOrientation(Orientation.VERTICAL);
      sepVert4.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVert4, 0, 3, 1, 10);
      GridPane.setRowSpan(sepVert4, 10);
      grid.getChildren().add(sepVert4);
      

      CreateTabButton ctb = new CreateTabButton();
      ctb.start(primaryStage);
      TabPane tabPane = ctb.getTabPane();
      GridPane.setConstraints(tabPane, 9, 3, 20, 10);
      
      ScrollPaneForAccordionPane spap = new ScrollPaneForAccordionPane();
      spap.start();
      ScrollPane scroll = spap.getScrollPane();
      GridPane.setConstraints(scroll, 36, 3, 11, 10);
     
      grid.getChildren().addAll(menuBar,stakeLabel,stakeButton,
              textField,userNameLabel,userNameField, balanceLabel,balanceField,
              treeView,tabPane,scroll, eurosLabel, euroText);
      bp.setCenter(grid);
        
        VBox root = new VBox();
        root.getChildren().addAll(grid);
        Scene scene = new Scene(root, width, height, Color.BLACK);
        //String css = this.getClass().getResource("projectcss.css").toExternalForm(); 
        //scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
      /*
      TabPane tabPane = new TabPane();
      BorderPane mainPane = new BorderPane();
     
    
    //Create Tabs
      Tab tabA = new Tab();
      tabA.setText("Tab A");
      
      //Add something in Tab
      Button tabA_button = new Button("Button@Tab A");
      tabA.setContent(tabA_button);
      tabPane.getTabs().add(tabA);
     
      Tab tabB = new Tab();
      tabB.setText("Tab B");
      //Add something in Tab
      StackPane tabB_stack = new StackPane();
      tabB_stack.setAlignment(Pos.CENTER);
      tabB_stack.getChildren().add(new Label("Label@Tab B"));
      tabB.setContent(tabB_stack);
      tabPane.getTabs().add(tabB);
     
      Tab tabC = new Tab();
      tabC.setText("Tab C");
      //Add something in Tab
      VBox tabC_vBox = new VBox();
      tabC_vBox.getChildren().addAll(
              new Button("Button 1@     "),
              new Button("Button 2@Tab C"),
              new Button("Button 3@Tab C"),
              new Button("Button 4@Tab C"));
      tabC.setContent(tabC_vBox);
      tabPane.getTabs().add(tabC);
    
      mainPane.setCenter(tabPane);
     
      mainPane.prefHeightProperty().bind(scene.heightProperty());
      mainPane.prefWidthProperty().bind(scene.widthProperty());
     
      root.getChildren().add(mainPane);
      primaryStage.setScene(scene); */
      
      //primaryStage.show();
      //Login login = new Login();
      //login.start(primaryStage);
  }

  public TreeView getTreeView(){
      return treeView;
  }

  public ListView getListView(){
      return listView;
  }

  public GridPane getGridPane(){
      return grid;
  }
} 
  /*
  treeView.setOnMouseClicked(new EventHandler<MouseEvent>()
{
    @Override
    public void handle(MouseEvent mouseEvent)
    {            
        if(mouseEvent.getClickCount() == 2)
        {
            TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
            System.out.println("Selected Text : " + item.getValue());

            // Create New Tab
            Tab tabdata = new Tab();
            Label tabALabel = new Label("Test");
            tabdata.setGraphic(tabALabel);

            DataStage.addNewTab(tabdata);
        }
    }
}); evento quando clicar numa treeview e abrir tab */

/* verificar quando sair
 primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

      public void handle(WindowEvent event) {
        final Stage dialog = new Stage();
        Label label =new Label("Really exit?");
        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            dialog.setVisible(false);
          }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            primaryStage.setVisible(true);
            dialog.setVisible(false);
          }
        });
        FlowPane pane = new FlowPane(10, 10);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(okButton, cancelButton);
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, pane);
        Scene scene1 = new Scene(vBox);
        dialog.setScene(scene1);
        dialog.setVisible(true);
      }
    }); */