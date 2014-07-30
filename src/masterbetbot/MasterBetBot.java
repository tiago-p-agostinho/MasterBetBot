package masterbetbot;
 
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color; 
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MasterBetBot extends Application {
 
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
     // Scene scene = new Scene(root, width, height, Color.BLACK);
     // scene.getStylesheets().add(MasterBetBot.class.getResource("projectcss.css").toExternalForm());
     
      BorderPane bp = new BorderPane();
    
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
      
        ScrollPaneForTreeMarkets spftm = new ScrollPaneForTreeMarkets();
        spftm.start(primaryStage);
        ScrollPane sp = spftm.getScrollPane();
        GridPane.setConstraints(sp, 0, 4, 1, 50);

        CreateTabButton ctb = new CreateTabButton();
        ctb.start(primaryStage);
        TabPane tabPane = ctb.getTabPane();
        GridPane.setConstraints(tabPane, 1, 4, 1, 40);

        grid.getChildren().addAll(sp,tabPane);
        //grid.add(sp,1,0);
        //grid.add(tabPane,2,0);
        bp.setCenter(grid);
        
        VBox root = new VBox();
        root.getChildren().addAll(grid);
        Scene scene = new Scene(root, width, height, Color.BLACK);
        
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