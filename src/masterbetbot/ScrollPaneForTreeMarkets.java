package masterbetbot;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeView;

public class ScrollPaneForTreeMarkets {
 final ScrollPane sp = new ScrollPane();
    
 public void start() {
    TreeMarkets tm = new TreeMarkets();
    tm.start();
    TreeView treeView = tm.getTreeView();
       
    sp.setFitToHeight(true);
    sp.setFitToWidth(true);
    sp.setPrefSize(200, 600);
    sp.setContent(treeView);
    }
 
public ScrollPane getScrollPane(){
    return sp;
    }
}
