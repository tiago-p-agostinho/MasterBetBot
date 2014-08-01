package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CreateTabButton extends Application {
    final TabPane tabPane = new TabPane();
    
    @Override
    public void start(Stage primaryStage) {
        Tab tab1 = new Tab();
        tab1.setText("Red");
        tab1.setContent(new Rectangle(750, 575, Color.RED));
        tabPane.getTabs().add(tab1);
        
        Tab tab2 = new Tab();
        tab2.setText("Blue");
        tab2.setContent(new Rectangle(750, 575, Color.BLUE));
        tabPane.getTabs().add(tab2);
                
        final   Tab tab3 = new Tab();
        tab3.setText("Green");
        tab3.setContent(new Rectangle(750, 575, Color.GREEN));
        tabPane.getTabs().add(tab3);
        
        Button btn = new Button();
        btn.setText("Push Green");
        btn.setOnAction((ActionEvent event) -> {
        tabPane.getSelectionModel().select(tab3);
        });
    }

   public TabPane getTabPane(){
       return tabPane;
   }
}
