package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */
 
import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class ScrollPaneForTreeMarkets extends Application {
 
    final ScrollPane sp = new ScrollPane();
    final VBox vb = new VBox(); 
   
    @Override
    public void start(Stage stage) {
        TreeMarkets tm = new TreeMarkets();
        tm.start(stage);
        TreeView treeView = tm.getTreeView();
       
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setPrefSize(200, 600);
        sp.setContent(treeView);
    }
 
    public ScrollPane getScrollPane(){
       return sp;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
