package masterbetbot;

/**
 *
 * @author tiagoagostinho
 */

import demo.util.APIContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
 
public class MenuBarMBB{
 MenuBar menuBar = new MenuBar();
 private APIContext apiContext;
 
 public MenuBarMBB(APIContext apiContext){
     this.apiContext=apiContext;
 }
    public void start(Stage stage) {
       final VBox vbox = new VBox();
       // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
            exit.setOnAction((ActionEvent t) -> { 
                ExitApp sair = new ExitApp(apiContext);
                try {
                    sair.start(stage);
                }catch (Exception ex) {
                    Logger.getLogger(MasterBetBot.class.getName()).log(Level.SEVERE, null, ex);
                }
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
