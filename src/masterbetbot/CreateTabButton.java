package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CreateTabButton extends Application {
    final TabPane tabPane = new TabPane();
    
    @Override
    public void start(Stage primaryStage) {
        Tab tab1 = new Tab();
        tab1.setText("Red");
        tab1.setContent(new Rectangle(600, 400, Color.RED));
        tabPane.getTabs().add(tab1);
        
        Tab tab2 = new Tab();
        tab2.setText("Blue");
        tab2.setContent(new Rectangle(600, 400, Color.BLUE));
        tabPane.getTabs().add(tab2);
                
        final   Tab tab3 = new Tab();
        tab3.setText("Green");
        tab3.setContent(new Rectangle(600, 400, Color.GREEN));
        tabPane.getTabs().add(tab3);
        
        Button btn = new Button();
        btn.setText("Push Green");
        btn.setOnAction((ActionEvent event) -> {
        tabPane.getSelectionModel().select(tab3);
        });

       // VBox root = new VBox(10);
        //root.getChildren().addAll(tabPane, btn);
        //Scene scene = new Scene(root, 300, 250);
        //primaryStage.setScene(scene);
        //primaryStage.show();
        //tabPane.getSelectionModel().select(tab1);
    }

   public TabPane getTabPane(){
       return tabPane;
   }
}
