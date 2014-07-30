package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class ScrollPaneForTreeMarkets extends Application {
 
    final ScrollPane sp = new ScrollPane();
    final VBox vb = new VBox(); 
    //final Label fileName = new Label();
 
    @Override
    public void start(Stage stage) {
       
        //VBox box = new VBox();
        //Scene scene = new Scene(box, 180, 180);
        //stage.setScene(scene);
        //stage.setTitle("Scroll Pane");
        //box.getChildren().addAll(sp, fileName);
        //VBox.setVgrow(sp, Priority.ALWAYS);
 
        //fileName.setLayoutX(100);
        //fileName.setLayoutY(100);

        TreeMarkets tm = new TreeMarkets();
        tm.start(stage);
        TreeView treeView = tm.getTreeView();
       // vb.getChildren().add(treeView);
        
        //sp.setVmax(400);
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setPrefSize(200, 600);
        sp.setContent(treeView);
        //stage.show();
        
       
    }
 
    public ScrollPane getScrollPane(){
       return sp;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
