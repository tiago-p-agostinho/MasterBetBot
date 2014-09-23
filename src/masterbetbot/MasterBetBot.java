package masterbetbot;

import demo.handler.ExchangeAPI;
import demo.handler.GlobalAPI;
import demo.util.APIContext;
import demo.util.Display;
import generated.exchange.BFExchangeServiceStub;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox; 
import javafx.scene.paint.Color; 
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import masterbetbot.threads.BalanceThread;

public class MasterBetBot extends Application {
 private TreeView treeView;
 private ListView listView;
 private GridPane grid;
 private TabPane tabPane;
 private  Label balanceField;
         
 private static ExchangeAPI.Exchange exchange;
 private static APIContext apiContext;
 private BFExchangeServiceStub.GetAccountFundsResp funds;

 public MasterBetBot(){
     apiContext = new APIContext();
     exchange = ExchangeAPI.Exchange.UK;
     
 }
 
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
      
      primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
            public void handle(WindowEvent event) {
                ExitApp exit = new ExitApp(apiContext);
                try {
                    exit.start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(MasterBetBot.class.getName()).log(Level.SEVERE, null, ex);
               }
             event.consume();
            }
        });   
      width=primaryStage.getX();
      height=primaryStage.getY();
    
      BorderPane bp = new BorderPane();
      grid = new GridPane();
      grid.setHgap(5);
      grid.setVgap(5);
      grid.setPadding(new Insets(0, 10, 0, 10));
      
      MenuBarMBB menuBarMBB = new MenuBarMBB(apiContext);
      menuBarMBB.start(primaryStage);
      MenuBar menuBar = menuBarMBB.getMenuBar();
      GridPane.setConstraints(menuBar,0,0,51,1);
        
      String workingDir = System.getProperty("user.dir");
      Image img = new Image("file:\\" + workingDir + "\\src\\masterbetbot\\logo_png.png");
      
      ImageView imgView = new ImageView(img);
      imgView.setFitWidth(225);
      imgView.setFitHeight(70);
      GridPane.setConstraints(imgView, 1, 1, 8, 3);
      grid.getChildren().add(imgView);
      
      TreeMarkets tm = new TreeMarkets(this);
      tm.start();
      treeView = tm.getTreeView();
      GridPane.setConstraints(treeView, 1, 7, 8, 115);
    
      final Separator sepVertLeftImage = new Separator();
      sepVertLeftImage.setOrientation(Orientation.VERTICAL);
      sepVertLeftImage.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertLeftImage, 0, 1, 1, 5);
      GridPane.setRowSpan(sepVertLeftImage, 5);
      grid.getChildren().add(sepVertLeftImage);
      
      final Separator sepVertRightTreeView = new Separator();
      sepVertRightTreeView.setOrientation(Orientation.VERTICAL);
      sepVertRightTreeView.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertRightTreeView, 9, 5, 1, 1);
      GridPane.setRowSpan(sepVertRightTreeView, 118);
      grid.getChildren().add(sepVertRightTreeView);
      
      final Separator sepVertRightImage = new Separator();
      sepVertRightImage.setOrientation(Orientation.VERTICAL);
      sepVertRightImage.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertRightImage, 9, 1, 1, 1);
      GridPane.setRowSpan(sepVertRightImage, 5);
      grid.getChildren().add(sepVertRightImage);
      
      SearchBar searchBar = new SearchBar(this);
      searchBar.start();
      TextField textField = searchBar.getTextField();
      textField.setPrefWidth(225);
      GridPane.setConstraints(textField,10,1,6,1);
            
      final Separator sepVertLeftSearcBar= new Separator();
      sepVertLeftSearcBar.setOrientation(Orientation.VERTICAL);
      sepVertLeftSearcBar.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertLeftSearcBar, 16, 1, 1, 1);
      GridPane.setRowSpan(sepVertLeftSearcBar, 5);
      grid.getChildren().add(sepVertLeftSearcBar);
      
      Label stakeLabel = new Label("Stake:");
      GridPane.setConstraints(stakeLabel, 17, 1);
      
      DoubleTextField euroText = new DoubleTextField("2.00");
      euroText.setPrefWidth(75);
      GridPane.setConstraints(euroText, 18, 1, 1, 1);
      
      Label eurosLabel = new Label("€");
      GridPane.setConstraints(eurosLabel, 19, 1, 1, 1);
      
      Button stakeButton = new Button("Back");
      GridPane.setConstraints(stakeButton, 20, 1, 1, 1); 
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
      
      final Separator sepVertAfterButton = new Separator();
      sepVertAfterButton.setOrientation(Orientation.VERTICAL);
      sepVertAfterButton.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertAfterButton, 21, 1, 1, 1);
      GridPane.setRowSpan(sepVertAfterButton, 5);
      grid.getChildren().add(sepVertAfterButton);
      
      Label userNameLabel = new Label("UserName:");
      GridPane.setConstraints(userNameLabel, 22, 1);
      Label userNameField = new Label("Unknown");
      userNameField.setPrefWidth(75);
      GridPane.setConstraints(userNameField, 23, 1);  
      
      final Separator sepVertLeftUserName = new Separator();
      sepVertLeftUserName.setOrientation(Orientation.VERTICAL);
      sepVertLeftUserName.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertLeftUserName, 24, 1, 1, 1);
      GridPane.setRowSpan(sepVertLeftUserName, 5);
      grid.getChildren().add(sepVertLeftUserName);
        
      Label balanceLabel = new Label("Balance:");
      GridPane.setConstraints(balanceLabel, 22, 3);
      balanceField = new Label("NaN"+" €");
      balanceField.setAlignment(Pos.CENTER_LEFT);
      balanceField.setPrefWidth(50);
      GridPane.setConstraints(balanceField, 23, 3);
       
      final Separator sepHorUnderDataUser = new Separator();
      sepHorUnderDataUser.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepHorUnderDataUser, 0, 5, 51, 1);
      GridPane.setColumnSpan(sepHorUnderDataUser, 51);
      grid.getChildren().add(sepHorUnderDataUser);
    
      listView = searchBar.getList();
      GridPane.setConstraints(listView, 1, 7, 8, 115);
      
      final Separator sepHorUnderTreeView = new Separator();
      sepHorUnderTreeView.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepHorUnderTreeView, 0, 122, 51, 1);
      GridPane.setColumnSpan(sepHorUnderTreeView, 51);
      grid.getChildren().add(sepHorUnderTreeView);
      
      final Separator sepVertLeftTreeView = new Separator();
      sepVertLeftTreeView.setOrientation(Orientation.VERTICAL);
      sepVertLeftTreeView.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertLeftTreeView, 0, 5, 1, 13);
      GridPane.setRowSpan(sepVertLeftTreeView, 118);
      grid.getChildren().add(sepVertLeftTreeView);
      
      CreateTabButton ctb = new CreateTabButton();
      ctb.start(primaryStage);
      tabPane = ctb.getTabPane();
      tabPane.setPrefWidth(525);
      tabPane.setPrefHeight(300);
      GridPane.setConstraints(tabPane, 10, 6, 28, 116);
      
      final Separator sepVertRightTabPane = new Separator();
      sepVertRightTabPane.setOrientation(Orientation.VERTICAL);
      sepVertRightTabPane.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertRightTabPane, 39, 5, 1, 1);
      GridPane.setRowSpan(sepVertRightTabPane, 118);
      grid.getChildren().add(sepVertRightTabPane);
      
      ScrollPaneForAccordionPane spap = new ScrollPaneForAccordionPane();
      spap.start();
      ScrollPane scroll = spap.getScrollPane();
      scroll.setPrefWidth(250);
      GridPane.setConstraints(scroll, 40, 6, 10, 116);
      
      final Separator sepVertRightAccordion = new Separator();
      sepVertRightAccordion.setOrientation(Orientation.VERTICAL);
      sepVertRightAccordion.setValignment(VPos.CENTER);
      GridPane.setConstraints(sepVertRightAccordion, 50, 5, 1, 1);
      GridPane.setRowSpan(sepVertRightAccordion, 118);
      grid.getChildren().add(sepVertRightAccordion);
     
      grid.getChildren().addAll(menuBar,stakeLabel,stakeButton,userNameLabel,userNameField, balanceLabel,balanceField,
              treeView,tabPane, scroll, textField, eurosLabel, euroText);
      bp.setCenter(grid);
        
      HBox root = new HBox();
      root.setStyle("-fx-base: rgb(50, 50, 50);\n" +
                    "-fx-background: rgb(50, 50, 50);");
      root.getChildren().addAll(grid);
      Scene scene = new Scene(root, width, height, Color.BLACK);
      primaryStage.setScene(scene);
   
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
      
      primaryStage.show();
      Login login = new Login(apiContext, funds, exchange);
      login.start(primaryStage);
     
      Platform.runLater(new Runnable() {
        @Override
        public void run() {
             while(true){
         System.out.println("ola1");   
        double money = funds.getAvailBalance();
        String moneyString = Double.toString(money);
        System.out.println(Double.toString(money));
        System.out.println("ola2");
        balanceField.setText(moneyString);
        
        System.out.println("ola3");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BalanceThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            }
        });
      
      
     //Thread balance = new Thread(new BalanceThread(apiContext, balanceField));
     //balance.start(); 
     // balanceField.setText("dsdas");
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

  public TabPane getTabPane(){
      return tabPane;
  }
} 
 

