package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
 
public class MenuBarMBB extends Application {
 MenuBar menuBar = new MenuBar();
 
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        
 
        final VBox vbox = new VBox();
       // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
            exit.setOnAction((ActionEvent t) -> {
                System.exit(0);
        });      
        exit.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
            
        menuFile.getItems().addAll(exit);
 
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        
        // --- Menu View
        Menu menuView = new Menu("View");
        
         Menu menuHelp = new Menu("Help");
         MenuItem about = new MenuItem("About");
         menuHelp.getItems().addAll(about);
         
         menuBar.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
    }
    
    public MenuBar getMenuBar(){
        return menuBar;
    }

} 
