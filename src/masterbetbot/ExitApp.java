package masterbetbot;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import masterbetbot.Login.Response;

public class ExitApp{   
    public void start(Stage primaryStage) throws Exception {
        showConfirmDialog(primaryStage, "MasterBetBot" );
     }
    
static class Dialog extends Stage {
    public Dialog( String title, Stage owner, Scene scene) {
        setTitle( title );
        initStyle( StageStyle.UNDECORATED );
        initModality( Modality.APPLICATION_MODAL );
        initOwner( owner );
        setResizable( false );
        setScene( scene );
    }
    public void showDialog() {
        sizeToScene();
        centerOnScreen();
        showAndWait();
    }
}

public static Response showConfirmDialog( Stage owner, String title) {
    VBox vb = new VBox();
    Scene scene = new Scene( vb, 300, 100 );
     //scene.getStylesheets().add(MasterBetBot.class.getResource("projectcss.css").toExternalForm());
     final Dialog dial = new Dialog( title, owner, scene);
    
    BorderPane bp = new BorderPane();
    
    GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
       
         Button OKButton = new Button("Yes");
        OKButton.setOnAction((ActionEvent event)->{
           System.exit(0);
        });
        
        Button CancelButton = new Button("No");
        CancelButton.setOnAction((ActionEvent event)->{
           dial.hide();
           owner.show();
        });
        
       grid.add(new Label("Do you really exit?"),0,1);
       grid.add(OKButton, 0,2);
       grid.add(CancelButton, 1,2);

    bp.setCenter( grid );
    HBox msg = new HBox();
    vb.getChildren().addAll( msg, bp );
    dial.showDialog();
    return null;
}
}
